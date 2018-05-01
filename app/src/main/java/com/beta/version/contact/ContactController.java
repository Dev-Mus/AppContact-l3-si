package com.beta.version.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private  static  final  String COL_favoris = "favoris";

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

        values.put(COL_fullname, contact.getFullname());
        values.put(COL_type, contact.getType());
        values.put(COL_num_tel, contact.getNum_tel());
        values.put(COL_favoris, contact.getfavoris());
        return bdd.insert(DB_TABLE, null, values);
    }

    public int updateContact(String id, Contact contact){

        ContentValues values = new ContentValues();
        values.put(COL_fullname, contact.getFullname());
        values.put(COL_type, contact.getType());
        values.put(COL_num_tel, contact.getNum_tel());
        values.put(COL_favoris, contact.getfavoris());
        return bdd.update(DB_TABLE, values, COL_id + " = " + id, null);
    }

    public int removeContactWithID(String id){
        return bdd.delete(DB_TABLE, COL_id + " = " + id , null);
    }

    public Contact getContactWithFullname(String fullname){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(DB_TABLE, new String[] {COL_id, COL_fullname, COL_type, COL_num_tel}, COL_fullname + " LIKE \"" + fullname +"\"", null, null, null, null);
        return cursorToContact(c);
    }

    public Contact getContactWithId(String id){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(DB_TABLE, new String[] {COL_id, COL_fullname, COL_type, COL_num_tel}, COL_id + " ==  " + id , null, null, null, null);
        return cursorToContact(c);
    }

    private Contact cursorToContact(Cursor c){
        if (c.getCount() == 0)
            return null;
        c.moveToFirst();
        Contact contact = new Contact(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4));
        c.close();
        return contact;
    }

    public void deleteAllFromTable() {
        bdd.delete(DB_TABLE, null, null);
    }

    public ArrayList<Contact> selectAllFromTable(){
        ArrayList<Contact> lists = new ArrayList<>();
        Cursor c = bdd.query(DB_TABLE, new String[] {COL_id, COL_fullname, COL_type, COL_num_tel, COL_favoris}, null , null, null, null, COL_fullname);
       if(c.getCount() == 0)
            return  null;

       c.moveToFirst();
        while (!c.isAfterLast()){
            lists.add(new Contact(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
            c.moveToNext();
        }
        c.close();
        return lists;
    }

    public List<Contact> selectAllFromTableWhere(String cond, String valeur){
        List<Contact> lists = new ArrayList<>();
        Cursor c = bdd.query(DB_TABLE, new String[] {COL_id, COL_fullname, COL_type, COL_num_tel, COL_favoris}, cond +" = '"+ valeur +"'" , null, null, null, COL_fullname);
        if(c.getCount() == 0)
            return  null;
        c.moveToFirst();
        while (!c.isAfterLast()){
            lists.add(new Contact(c.getString(0), c.getString(1), c.getString(2), c.getString(3),c.getString(4)));
            c.moveToNext();
        }
        c.close();
        return lists;
    }


}