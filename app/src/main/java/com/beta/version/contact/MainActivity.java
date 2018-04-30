package com.beta.version.contact;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.List;

public class MainActivity extends Activity implements OnItemClickListener {

    private DataBaseContacts dataBaseContacts;

    private List<Contact> lists;
    private ListView listview;
    private ContactController contactController;
    private ContactAdapter adapter;

    private BoomMenuButton bmb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmb = findViewById(R.id.boom);
        int tab[]= {R.drawable.plus, R.drawable.favorites, R.drawable.user};
        String tabs[]= {"ajouter contact", "favorite", "contact list"};
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(tab[i])
                    .normalText(tabs[i])
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            if (index == 0)
                                newContact();
                            Toast.makeText(MainActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                        }
                    });
            bmb.addBuilder(builder);
        }

        contactController = new ContactController(this);
        contactController.open();

            lists = contactController.selectAllFromTable();

            if(lists == null) {
                Toast.makeText(this, "list des Contacts est vide ..!", Toast.LENGTH_LONG).show();
            } else {
                listview = (ListView) findViewById(R.id.list);

                adapter = new ContactAdapter(this, lists);
                listview.setAdapter(adapter);
                listview.setOnItemClickListener(this);
            }

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    public  void  newContact(){
        AlertDialog.Builder mbuilder = new AlertDialog.Builder( MainActivity.this);
        final View mview = getLayoutInflater().inflate(R.layout.dialog_newcontact, null);
        final EditText Efullname = (EditText) mview.findViewById(R.id.Efullname);
        final EditText Etype = (EditText) mview.findViewById(R.id.Etype);
        final EditText Enum_tel = (EditText) mview.findViewById(R.id.Enum_tel);
        Button Bajoute = (Button) mview.findViewById(R.id.Bajoute);

        mbuilder.setView(mview);
        final AlertDialog dialog = mbuilder.create();
        dialog.show();

        Bajoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Efullname.getText().toString().isEmpty() && !Etype.getText().toString().isEmpty() && !Enum_tel.getText().toString().isEmpty()){
                    Contact contact = new Contact(Efullname.getText().toString(), Etype.getText().toString(), Enum_tel.getText().toString());
                    contactController.insertContact(contact);
                    Toast.makeText(MainActivity.this, "bien ajoute", Toast.LENGTH_LONG).show();

                    dialog.hide();
                }else
                    Toast.makeText(MainActivity.this, "error 404", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        String fullname = lists.get(position).getFullname();
        newContact();
        Toast.makeText(getApplicationContext(), "" + fullname + " ID : " + lists.get(position).getId() ,
                Toast.LENGTH_SHORT).show();
    }

}
