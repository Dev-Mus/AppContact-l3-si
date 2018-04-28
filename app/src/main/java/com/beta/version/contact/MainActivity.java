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
    private  static  final  String COL_id = "id";
    private  static  final  String COL_fullname = "fullname";
    private  static  final  String COL_type = "type";
    private  static  final  String COL_num_tel = "num_tel";
    private  static  final  String COL_created_at = "created_at";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtcontact = (TextView) findViewById( R.id.txtcontact );

        txtcontact.append("new   : ");

        ContactController contactController = new ContactController(this);

        //Création d'un contact
        Contact contact = new Contact("abcd", "Programme", "05565");

        //On ouvre la base de données pour écrire dedans
        contactController.open();
        //On insère le livre que l'on vient de créer
            contactController.insertContact(contact);
        txtcontact.append(contactController.getContactWithFullname("abcd").toString());
        contactController.close();

        txtcontact.append("fin   : ");
    }
}
