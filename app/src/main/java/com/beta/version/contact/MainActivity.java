package com.beta.version.contact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
//            contactController.insertContact(contact);


           // Contact nv = contactController.getContactWithFullname("abcd");
           // txtcontact.append(nv.toString());

            contactController.deleteAllFromTable();
        ArrayList<Contact> lists = contactController.selectAllFromTable();
        if(lists != null) {
            for (Contact var : lists) {
                txtcontact.append(var.toString()+"\n\n");
            }
        } else
            Toast.makeText(this, "list des Contacts est vide ..!", Toast.LENGTH_LONG).show();





/*
        if(nv != null){
            //On affiche les infos du livre dans un Toast
            Toast.makeText(this, nv.toString(), Toast.LENGTH_LONG).show();
            //On modifie le titre du livre
            nv.setFullname("livre");
            //Puis on met à jour la BDD
            contactController.updateContact(nv.getId(), nv);
        }

        //On extrait le livre de la BDD grâce au nouveau titre
        nv = contactController.getContactWithFullname("livre");
        //S'il existe un livre possédant ce titre dans la BDD
        if(nv != null){
            //On affiche les nouvelles informations du livre pour vérifier que le titre du livre a bien été mis à jour
            Toast.makeText(this, nv.toString(), Toast.LENGTH_LONG).show();
            //on supprime le livre de la BDD grâce à son ID
            contactController.removeContactWithID(nv.getId());
        }

        //On essaye d'extraire de nouveau le livre de la BDD toujours grâce à son nouveau titre
        nv = contactController.getContactWithFullname("livre");
        //Si aucun livre n'est retourné
        if(nv == null){
            //On affiche un message indiquant que le livre n'existe pas dans la BDD
            Toast.makeText(this, "Ce Contact n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
        }
        //Si le livre existe (mais normalement il ne devrait pas)
        else{
            //on affiche un message indiquant que le livre existe dans la BDD
            Toast.makeText(this, "Ce Contact existe dans la BDD", Toast.LENGTH_LONG).show();
        }














*/
        contactController.close();

        txtcontact.append("fin   : ");
    }
}
