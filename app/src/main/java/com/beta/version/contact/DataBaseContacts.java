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
    private  static  final  int DB_VERSION = 1;
    private  static  final  String DB_TABLE = "Save_table";
    private  static  final  String DB_TAG = "DB_BASE";

    private  static  final  String COL_id = "id";
    private  static  final  String COL_fullname = "fullname";
    private  static  final  String COL_type = "type";
    private  static  final  String COL_num_tel = "num_tel";
    private  static  final  String COL_favoris = "favoris";

    private  static  final  String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" +
                                                        COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                                                        COL_fullname + " VARCHAR(100) NULL , " +
                                                        COL_type + " VARCHAR(20) NULL , " +
                                                        COL_num_tel + " VARCHAR(10) NULL , " +
                                                        COL_favoris + " VARCHAR(10)  NULL " +
                                                        ") ;";

    public DataBaseContacts(Context context, String DB_DATABASE, SQLiteDatabase.CursorFactory factory, int DB_VERSION) {

        super(context, DB_DATABASE, factory, DB_VERSION);
        Log.i(DB_TAG, "creation de bd "+DB_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_TABLE);
        Log.i("DB_BASE", "onUpdate " + DB_DATABASE +" and " + DB_TABLE+ "  execSQL 110%");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + DB_TABLE + ";");
        onCreate(db);
        Log.i("DB_BASE", "onUpdate " + DB_DATABASE +" and " + DB_TABLE+ "  execSQL 110%");
    }
}
