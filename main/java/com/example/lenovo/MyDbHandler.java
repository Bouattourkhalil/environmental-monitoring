package com.example.lenovo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class MyDbHandler extends SQLiteOpenHelper {

    public static final String DATABASSE_NAME= "test.db";
    public static final String TABLE_NAME= "users";
    public static final String COL1 = "ID";
    public static final String COL2= "name";
    public static final String COL3= "identification";
    public static final String COL4= "password";
    private static EditText username;
    public MyDbHandler(Context context)
    {

        super(context, DATABASSE_NAME, null, 1);

      /*  SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.execSQL("delete From "+ TABLE_NAME);
        db.close();*/

    }

    public Cursor getData() {

        // new String = inisialization
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT Password, user, Name FROM `test` WHERE user =? \n" , new String[]{Global.getInstance().getData()});
        res.moveToFirst();

        return res;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}