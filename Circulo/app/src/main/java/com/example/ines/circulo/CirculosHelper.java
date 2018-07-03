package com.example.ines.circulo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Asus on 19/01/2018.
 */

/**
 * Table structure:
 * type             | evangelio | comentario    | norma | charla    | noticias
 * ----------------------------------------------------------------------------
 * estudiantes      |           |               |       |           |
 * supernumerarias  |           |               |       |           |
 * numerarias       |           |               |       |           |
 * cooperadoras     |           |               |       |           |
 *
 * Each space contains the path to the document where info is stored
 */

//TODO: Automatic creation of primary keys?

public class CirculosHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "circulos";
    public static final String TABLE = "circulos";

    public CirculosHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " (" +
                "type TEXT PRIMARY KEY UNIQUE, " +
                "evangelio TEXT, " +
                "comentario TEXT, " +
                "norma TEXT, " +
                "charla TEXT, " +
                "noticias TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getCirculo (String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"evangelio", "comentario", "norma", "charla", "noticias"};
        String[] where = {type};
        return db.query(TABLE, columns, "type=?",where, null, null, null);
    }

    public Cursor getParameter (String type, String param) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {param};
        String[] where = {type};
        return db.query(TABLE, columns, "type=?",where, null, null, null);
    }

    //Uprade any param with text
    public void upgradeParameter (String type, String param, String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE + " SET '" + param + "' = '" + text + "' WHERE type = '" + type + "';");
    }
}
