package com.beta.version.contact;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aa on 28/04/2018.
 */

public class DataBaseContacts extends SQLiteOpenHelper {

    private  static  final  String DB_DATABASE = "Contacts.bd";
    private  static  final  String DB_TAG = "DB_BASE";
    private  static  final  int DB_VERSION = 1;
    private  static  final  String DB_TABLE = "Save_table";
    private  static  final  String col[] = {"id", "fullname", "type", "num_tel", "created_at"};


    public DataBaseContacts (Context context) {

        super( context, DB_DATABASE, null, DB_VERSION);
        onCreate(this.onCreate(););
        Log.i(DB_TAG, "creation de bd "+DB_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {

        Log.i("DB_BASE", "onCreate " + DB_DATABASE +" and " + DB_TABLE+ "  execSQL 110%");
        String str = "create table " + DB_TABLE + " (" +
                col[0] + " int autoincrement primary key , " +
                col[1] + " varchar(100) null , " +
                col[2] + " varchar(20) null , " +
                col[3] + " varchar(10) , " +
                col[4] + " Date null " +
                ")";
        bd.execSQL(str);
        Log.i("DB_BASE", "onCreate " + DB_DATABASE +" and " + DB_TABLE+ "  execSQL 110%");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int a, int b) {
        Log.i("DB_BASE", "onUpdate " + DB_DATABASE +" and " + DB_TABLE+ "  execSQL 110%");
    }


    /*   public  void insertInToTable ( Contact contact) {

        /*String str = "INSERT INTO " + DB_TABLE +" (" + col[1] + ", " + col[2] + ", " + col[3] +
                ") VALUES ('"+ contact.getFullname() + "', '" + contact.getType() + "', '" + contact.getNum_tel() + "')";


        String str = "insert into"+ DB_TABLE +" (" + col[1] + ", " + col[2] + ", " + col[3] + ", " + col[4] + ") " +
                "Values('" + contact.getFullname() + "', '" + contact.getType() + "', '" + contact.getNum_tel() + "', " + new Date().getTime() + ")";

    */
   /*     String str = "INSERT INTO " + DB_TABLE +" (" + col[1] + ", " + col[2] + ", " + col[3] + ", " + col[4] +
                ") VALUES ('"+contact.getFullname()+"', '"+contact.getType()+"', '"+contact.getNum_tel()+"', Date('"+contact.getCreated_at().toString()+"'))";

        Log.i(DB_TAG, "  avent execSQL 110%");
        this.getWritableDatabase().execSQL(str);
        Log.i(DB_TAG, "  apres execSQL 110%");
    }

    public List<Contact> selectAllFromTable () {
        String str = "select * from  "+ DB_TABLE+";";
        List<Contact>  lists = new ArrayList<>();

        Cursor cursor = this.getReadableDatabase().rawQuery(str , null);
        cursor.moveToFirst();
        if(!cursor.isFirst())
            while (!cursor.isAfterLast()) {
                //Contact var = new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), (java.sql.Date) new Date(cursor.getInt(4)));
                Contact var = new Contact(cursor.getString(1), cursor.getString(2), cursor.getString(3));
                lists.add(var);
                cursor.moveToNext();
            }
        Log.i(DB_TAG, "fin sde recuperation ...");
        cursor.close();
        return lists;
    }

    public  Contact selectElementFromTable (int id){

        return  new Contact();
    }

*/
}
