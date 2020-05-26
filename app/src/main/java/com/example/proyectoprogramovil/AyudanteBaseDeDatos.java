package com.example.proyectoprogramovil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AyudanteBaseDeDatos extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DE_DATOS = "universidad",
            NOMBRE_TABLA_USUARIOS = "usuarios";
    private static final int VERSION_BASE_DE_DATOS =1;

    public AyudanteBaseDeDatos(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format("create table if not exists %s(id integer primary key autoincrement, nombre text,email text,telefono text,direccion text,usuario text not null,contrasenia text not null);",NOMBRE_TABLA_USUARIOS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
