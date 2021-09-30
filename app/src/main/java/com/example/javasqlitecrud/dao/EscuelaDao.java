package com.example.javasqlitecrud.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.javasqlitecrud.config.DBHelper;
import com.example.javasqlitecrud.model.Escuela;

import java.util.ArrayList;
import java.util.List;

public class EscuelaDao {
    private DBHelper helper;
    private SQLiteDatabase database;
    public EscuelaDao(Context context){
        helper = new DBHelper(context);
    }
    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = helper.getWritableDatabase();
        }
        return database;
    }
    private Escuela crearEscuela(Cursor cursor){
        @SuppressLint("Range") Escuela escuela = new Escuela(
                cursor.getInt(cursor.getColumnIndex(DBHelper.Escuelas._IDESC)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Escuelas.NOMBRE)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Escuelas.FACULTAD))
        );
        return escuela;
    }
    public List<Escuela> listarEscuelas(){
        Cursor cursor = getDatabase().query(DBHelper.Escuelas.TABLE,DBHelper.Escuelas.COLUMNAS, null, null, null, null, null);
        List<Escuela> listaesc = new ArrayList<Escuela>();
        while(cursor.moveToNext()){
            Escuela modelo = crearEscuela(cursor);
            listaesc.add(modelo);
        }
        return listaesc;
    }
    public long modificarEscuela(Escuela escuela){
        ContentValues values = new ContentValues();
        values.put(DBHelper.Escuelas.NOMBRE, escuela.getNombre());
        values.put(DBHelper.Escuelas.FACULTAD, escuela.getFacultad());
        if(escuela.get_idesc() != null){
            return database.update(DBHelper.Escuelas.TABLE, values,
                    "_idesc = ?", new String[]{escuela.get_idesc().toString()});
        }
        return getDatabase().insert(DBHelper.Escuelas.TABLE,null,values);
    }
    public boolean eliminarEscuela(int id){
        return getDatabase().delete(DBHelper.Escuelas.TABLE,"_idesc = ?", new String[]{Integer.toString(id)}) > 0;
    }
    public Escuela buscarEscuelaPorID(int id){
        Cursor cursor = getDatabase().query(DBHelper.Escuelas.TABLE,
                DBHelper.Escuelas.COLUMNAS, "_idesc = ?", new String[]{ Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Escuela model = crearEscuela(cursor);
            cursor.close();
            return model;
        }
        return null;
    }
    public void cerrarDB(){
        helper.close();
        database = null;
    }
}
