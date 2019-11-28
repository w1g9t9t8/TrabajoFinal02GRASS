package edu.pe.udaff.ist.trabajofinal02;

import android.content.Context;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import edu.pe.udaff.ist.trabajofinal02.Utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        db.execSQL(Utilidades.CREAR_TABLA_GRASS);
        db.execSQL(Utilidades.CREAR_TABLA_RESERVA);
        db.execSQL(Utilidades.CREAR_TABLA_CANCELADOS);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS grass");
        db.execSQL("DROP TABLE IF EXISTS reserva");
        db.execSQL("DROP TABLE IF EXISTS cancelados");
        onCreate(db);
    }



}
