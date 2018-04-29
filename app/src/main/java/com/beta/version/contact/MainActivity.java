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
import java.util.List;

public class MainActivity extends Activity implements OnItemClickListener {

    private DataBaseContacts dataBaseContacts;

    List<Contact> lists;
    ListView listview;
    ContactController contactController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactController = new ContactController(this);
        contactController.open();
            lists = contactController.selectAllFromTable();

            if(lists == null) {
                Toast.makeText(this, "list des Contacts est vide ..!", Toast.LENGTH_LONG).show();
            } else {
                listview = (ListView) findViewById(R.id.list);

                ContactAdapter adapter = new ContactAdapter(this, lists);
                listview.setAdapter(adapter);
                listview.setOnItemClickListener(this);
            }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        String fullname = lists.get(position).getFullname();
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

                    }else
                        Toast.makeText(MainActivity.this, "error 404", Toast.LENGTH_LONG).show();
                }
            });
        Toast.makeText(getApplicationContext(), "" + fullname + " ID : " + lists.get(position).getId() ,
                Toast.LENGTH_SHORT).show();
    }

}
