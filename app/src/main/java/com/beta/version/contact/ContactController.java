package com.beta.version.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

/**
 * Created by aa on 28/04/2018.
 */

public class ContactController {

    private  static  final  int DB_VERSION = 1;
    private  static  final  String DB_DATABASE = "Contacts.bd";
    private  static  final  String DB_TABLE = "Save_table";

    private  static  final  String COL_id = "id";
    private  static  final  String COL_fullname = "fullname";
    private  static  final  String COL_type = "type";
    private  static  final  String COL_num_tel = "num_tel";
    private  static  final  String COL_created_at = "created_at";

    public ContactController(Context context){
        //On crée la BDD et sa table
        dataBaseContacts = new DataBaseContacts(context, DB_DATABASE, null, DB_VERSION);
    }


    private SQLiteDatabase bdd;

    private DataBaseContacts dataBaseContacts;

    public void open(){
        //on ouvre la BDD en écriture
        bdd = dataBaseContacts.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        // return bdd
        return bdd;
    }

    public long insertContact(Contact contact){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_fullname, contact.getFullname());
        values.put(COL_type, contact.getType());
        values.put(COL_num_tel, contact.getNum_tel());
       // values.put(COL_created_at, contact.getCreated_at().toString());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(DB_TABLE, null, values);
    }

    public int updateContact(int id, Contact contact){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_fullname, contact.getFullname());
        values.put(COL_type, contact.getType());
        values.put(COL_num_tel, contact.getNum_tel());
        //values.put(COL_created_at, contact.getCreated_at().toString());
        return bdd.update(DB_TABLE, values, COL_id + " = " + id, null);
    }

    public int removeContactWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(DB_TABLE, COL_id + " = " + id, null);
    }

    public Contact getContactWithFullname(String fullname){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(DB_TABLE, new String[] {COL_id, COL_fullname, COL_type, COL_num_tel}, COL_fullname + " LIKE \"" + fullname +"\"", null, null, null, null);
        return cursorToContact(c);
    }

    public Contact getContactWithId(int id){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(DB_TABLE, new String[] {COL_id, COL_fullname, COL_type, COL_num_tel}, COL_id + " ==  " + id , null, null, null, null);
        return cursorToContact(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Contact cursorToContact(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        Contact contact = new Contact(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
        //On ferme le cursor
        c.close();
        //On retourne le livre
        return contact;
    }
}