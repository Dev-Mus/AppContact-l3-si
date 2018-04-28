package com.beta.version.contact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TextView txtcontact;
    private DataBaseContacts dataBaseContacts;
    private  static  final  String DB_TABLE = "Save_table";
    private  static  final  String col[] = {"id", "fullname", "type", "num_tel", "created_at"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtcontact = (TextView) findViewById( R.id.txtcontact );

        txtcontact.append("new   : ");

        dataBaseContacts = new DataBaseContacts(this);
            Contact contact = new Contact(0,"said", "mobile", "05565",new java.sql.Date(2010,05,20));
            txtcontact.append(contact.getStringContactComplete()+"\n");
        dataBaseContacts.close();
        txtcontact.append("fin   : ");
    }
}

        /*dataBaseContacts.insertInToTable(contact);

        List<Contact> lists = dataBaseContacts.selectAllFromTable();
        for ( Contact contacte : lists) {
            txtcontact.append(contacte.getStringContact()+ "\n\n");
        }*/
