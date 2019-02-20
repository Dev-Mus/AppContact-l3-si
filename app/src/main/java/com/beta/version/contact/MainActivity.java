package com.beta.version.contact;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.security.auth.login.LoginException;

public class MainActivity extends Activity {

    private DataBaseContacts dataBaseContacts;
    private List<Contact> lists;
    private ListView listview;
    private ContactController contactController;
    private ContactAdapter adapter;
    private BoomMenuButton bmb;
    private Button listM1, listM2, listM3, listM4, listM5, listM6;
    private View view;
    private AlertDialog dialoglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.list);
        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view = getLayoutInflater().inflate(R.layout.list, null);
                listM1 = (Button) view.findViewById(R.id.listM1);
                listM2 = (Button) view.findViewById(R.id.listM2);
                listM3 = (Button) view.findViewById(R.id.listM3);
                listM4 = (Button) view.findViewById(R.id.listM4);
                listM5 = (Button) view.findViewById(R.id.listM5);
                listM6 = (Button) view.findViewById(R.id.listM6);

                if (lists.get(position).getfavoris().equals("1"))
                    listM3.setText("supprimier du favoris");
                else
                    listM3.setText("ajoute au favoris");
                listFunction(lists.get(position));
                AlertDialog.Builder builderlist = new AlertDialog.Builder(MainActivity.this);
                builderlist.setView(view);
                dialoglist = builderlist.create();
                dialoglist.show();
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                affichage(lists.get(position));
                Log.i("TEST", "onClick : ID : " + lists.get(position).getId());
                return true;
            }
        });

        contactController = new ContactController(this);
        contactController.open();

        lists = contactController.selectAllFromTable();
        if (lists == null) {
            Toast.makeText(this, "list des Contacts est vide ..!", Toast.LENGTH_LONG).show();
        } else {
            adapter = new ContactAdapter(this, lists);
            listview.setAdapter(adapter);
        }

        bmb = findViewById(R.id.boom);
        int tab[] = {R.drawable.plus, R.drawable.favorites, R.drawable.user};
        String tabs[] = {"ajouter contact", "list favorite", "contact list"};

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {

            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(tab[i])
                    .normalText(tabs[i])
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            switch (index) {
                                case 0:
                                    newContact(true, null);
                                    break;
                                case 1:
                                    lists = contactController.selectAllFromTableWhere("favoris", "1");
                                    if (lists != null) {
                                        adapter = new ContactAdapter(MainActivity.this, lists);
                                        listview.setAdapter(adapter);
                                        break;
                                    } else
                                        Toast.makeText(MainActivity.this, "list des Contacts favoris est vide ..!", Toast.LENGTH_LONG).show();
                                case 2:
                                    lists = contactController.selectAllFromTable();
                                    if (lists == null)
                                        Toast.makeText(MainActivity.this, "list des Contacts est vide ..!", Toast.LENGTH_LONG).show();
                                    else {
                                        adapter = new ContactAdapter(MainActivity.this, lists);
                                        listview.setAdapter(adapter);
                                    }
                                    break;
                                default:
                                    break;
                            }
                            Log.i("TEST", "Clicked " + index);
                        }
                    });
            bmb.addBuilder(builder);
        }

    }

    public void newContact(final boolean var, final Contact CONTACT) {

        final View mview = getLayoutInflater().inflate(R.layout.dialog_newcontact, null);
        TextView titre = (TextView) mview.findViewById(R.id.titre);

        final EditText Efullname = (EditText) mview.findViewById(R.id.Efullname);

        final EditText Enum_tel = (EditText) mview.findViewById(R.id.Enum_tel);
        Button Bajoute = (Button) mview.findViewById(R.id.Bajoute);

        final Spinner Etype = (Spinner) mview.findViewById(R.id.Etype);
        ArrayAdapter<CharSequence> spinner = ArrayAdapter.createFromResource(MainActivity.this, R.array.type, android.R.layout.simple_spinner_item);
        spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final String[] type = new String[1];
        Etype.setAdapter(spinner);
        Etype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        AlertDialog.Builder mbuilder = new AlertDialog.Builder(MainActivity.this);
        mbuilder.setView(mview);
        final AlertDialog dialog = mbuilder.create();
        dialog.show();
        titre.setText("Ajoute Contact");
        if (!var) {
            titre.setText("Modifie Contact");
            Efullname.setText(CONTACT.getFullname());
            Enum_tel.setText(CONTACT.getNum_tel());
            String[] tab = {"Mobile", "Portable", "Fax", "Bureau", "Principale", "Domicile", "Auther"};
            for (int i = 0; i < tab.length; i++)
                if (tab[i].equals(CONTACT.getType())) {
                    Etype.setSelection(i);
                    break;
                }
        }
        Bajoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Efullname.getText().toString().isEmpty() && !Enum_tel.getText().toString().isEmpty()) {
                    if (var) {
                        Contact contact = new Contact(Efullname.getText().toString(), type[0], Enum_tel.getText().toString());
                        contactController.insertContact(contact);
                        lists = contactController.selectAllFromTable();
                        if (lists == null) {
                            Toast.makeText(MainActivity.this, "list des Contacts est vide ..!", Toast.LENGTH_LONG).show();
                        } else {
                            adapter = new ContactAdapter(MainActivity.this, lists);
                            listview.setAdapter(adapter);
                            Toast.makeText(MainActivity.this, "bien ajoute", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        CONTACT.setFullname(Efullname.getText().toString());
                        CONTACT.setType(type[0]);
                        CONTACT.setNum_tel(Enum_tel.getText().toString());
                        contactController.updateContact(CONTACT.getId(), CONTACT);
                        adapter = new ContactAdapter(MainActivity.this, lists);
                        listview.setAdapter(adapter);
                        Toast.makeText(MainActivity.this, "bien modifier", Toast.LENGTH_LONG).show();
                    }
                    dialog.hide();
                } else
                    Toast.makeText(MainActivity.this, "error 404 remplier les champs ..", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void listFunction(final Contact CONTACT) {
        listM1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                dialoglist.hide();
                Uri uri = Uri.parse("tel:" + CONTACT.getNum_tel());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    String[] permissions = {Manifest.permission.CALL_PHONE};
                    requestPermissions(permissions, 1000);
                    return;
                }
                startActivity(intent);
                Log.i("TEST", "onClick : bien listM1" + CONTACT.toString());
            }
        });
        listM2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialoglist.hide();
                Uri uri = Uri.parse("sms:" + CONTACT.getNum_tel());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    String[] permissions = {Manifest.permission.SEND_SMS};
                    requestPermissions(permissions, 1000);
                    return;
                }
                startActivity(intent);
                Log.i("TEST", "onClick : bien listM2" + CONTACT.toString());
            }
        });
        listM3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TEST", "onClick : bien listM3" + CONTACT.toString());
                Log.i("TEST", "onClick 0: " + CONTACT.toString());
                if (CONTACT.getfavoris().equals("1"))
                    CONTACT.setfavoris("0");
                else if (CONTACT.getfavoris().equals("0"))
                    CONTACT.setfavoris("1");
                contactController.updateContact(CONTACT.getId(), CONTACT);
                adapter = new ContactAdapter(MainActivity.this, lists);
                listview.setAdapter(adapter);
                Log.i("TEST", "onClick 4: " + CONTACT.toString());
                dialoglist.hide();
            }
        });
        listM4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TEST", "onClick : bien listM4" + CONTACT.toString());
                dialoglist.hide();
                newContact(false, CONTACT);
                Toast.makeText(MainActivity.this, "bien modifier", Toast.LENGTH_LONG).show();
            }
        });
        listM5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TEST", "onClick : bien listM5" + CONTACT.toString());
                remove(CONTACT);
                dialoglist.hide();
            }
        });
        listM6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TEST", "onClick : bien listM6" + CONTACT.toString());
                dialoglist.hide();
                affichage(CONTACT);
            }
        });
    }

    public void affichage(final Contact CONTACT) {
        final View viewA = getLayoutInflater().inflate(R.layout.affich, null);
        Button modifie = (Button) viewA.findViewById(R.id.btn);
        TextView AfullnameT = (TextView) viewA.findViewById(R.id.AfullnameT);
        AfullnameT.setText(CONTACT.getFullname());
        TextView AtypeT = (TextView) viewA.findViewById(R.id.AtypeT);
        AtypeT.setText(CONTACT.getType());
        TextView Anum_telT = (TextView) viewA.findViewById(R.id.Anum_telT);
        Anum_telT.setText(CONTACT.getNum_tel());

        AlertDialog.Builder builderlist = new AlertDialog.Builder(MainActivity.this);
        builderlist.setView(viewA);
        final AlertDialog dialog = builderlist.create();
        dialog.show();
        modifie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
                newContact(false, CONTACT);
            }
        });
    }

    public void remove(Contact contact) {
        contactController.removeContactWithID(contact.getId());
        lists = contactController.selectAllFromTable();
        if (lists == null) {
            adapter.clearlists();
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "list des Contacts est vide ..! ", Toast.LENGTH_LONG).show();
        } else {
            adapter = new ContactAdapter(MainActivity.this, lists);
            listview.setAdapter(adapter);
            Toast.makeText(MainActivity.this, "bien supprimier", Toast.LENGTH_LONG).show();
        }
    }


}
