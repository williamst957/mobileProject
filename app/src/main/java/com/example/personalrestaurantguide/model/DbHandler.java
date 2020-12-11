package com.example.personalrestaurantguide.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "testdb.db";

   /* private static final String CRE_TBL_USER;

    static {
        CRE_TBL_USER = "CREATE TABLE tblUser " +
                "( uid INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " name TEXT ," +
                " email TEXT ," +
                " mobile TEXT ," +
                " password TEXT ," +
                " active INTEGER) ";
    }

    private static final String DROP_TBL_USER;

    static {
        DROP_TBL_USER = "DROP TABLE IF EXISTS tblUser";
    }*/

    private static final String CRE_TBL_RESTAURANT;
    static {
        CRE_TBL_RESTAURANT ="CREATE TABLE tblRestaurant " +
                "( restid INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "name TEXT ," +
                "tag TEXT ," +
                "address TEXT ," +
                "description TEXT ,"+
                "city TEXT ," +
                "state TEXT ," +
                "zip TEXT ," +
                "country TEXT ," +
                "phone TEXT ," +
                "active INTEGER ," +
                "rate FLOAT )";
    }

    private static final String DROP_TBL_RESTAURANT;

    static {
        DROP_TBL_RESTAURANT = "DROP TABLE IF EXISTS tblRestaurant";
    }

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // db.execSQL(CRE_TBL_USER);
        db.execSQL(CRE_TBL_RESTAURANT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      //  db.execSQL(DROP_TBL_USER);
        db.execSQL(DROP_TBL_RESTAURANT);
        onCreate(db);
    }
}
