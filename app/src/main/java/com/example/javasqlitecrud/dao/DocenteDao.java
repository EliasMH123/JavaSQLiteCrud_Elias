package com.example.javasqlitecrud.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.javasqlitecrud.config.DBHelper;
import com.example.javasqlitecrud.model.Docente;

import java.util.ArrayList;
import java.util.List;

public class DocenteDao {
    private DBHelper helper;
    private SQLiteDatabase database;
    public DocenteDao(Context context) {helper = new DBHelper(context);}
    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = helper.getWritableDatabase();
        }
        return database;
    }
    private Docente crearDocente(Cursor cursor){
        @SuppressLint("Range") Docente docente = new Docente(
                cursor.getInt(cursor.getColumnIndex(DBHelper.Docentes._IDDOC)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Docentes.CODIGO)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Docentes.NOMBRE)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Docentes.DNI)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Docentes.TELEFONO)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Docentes.CORREO))
        );
        return docente;
    }
    public List<Docente> listarDocentes(){
        Cursor cursor =getDatabase().query(DBHelper.Docentes.TABLE,DBHelper.Docentes.COLUMNAS,null, null, null, null, null);
        List<Docente> listadoc = new ArrayList<Docente>();
        while (cursor.moveToNext()){
            Docente modelo = crearDocente(cursor);
            listadoc.add(modelo);
        }
        return listadoc;
    }
    public long modificarDocente(Docente docente){
        ContentValues values = new ContentValues();
        values.put(DBHelper.Docentes.CODIGO, docente.getCodigo());
        values.put(DBHelper.Docentes.NOMBRE, docente.getNombre());
        values.put(DBHelper.Docentes.DNI, docente.getDni());
        values.put(DBHelper.Docentes.TELEFONO, docente.getTelefono());
        values.put(DBHelper.Docentes.CORREO, docente.getCorreo());
        if(docente.get_iddoc() != null){
            return database.update(DBHelper.Docentes.TABLE, values,
                    "_iddoc = ?", new String[]{docente.get_iddoc().toString()});
        }
        return getDatabase().insert(DBHelper.Docentes.TABLE,null,values);
    }
    public boolean eliminarDocente(int id){
        return getDatabase().delete(DBHelper.Docentes.TABLE,"_iddoc = ?", new String[]{Integer.toString(id)}) > 0;
    }
    public Docente buscarDocentePorID(int id){
        Cursor cursor = getDatabase().query(DBHelper.Docentes.TABLE,
                DBHelper.Docentes.COLUMNAS, "_iddoc = ?", new String[]{ Integer.toString(id)}, null, null, null);
        if(cursor.moveToNext()){
            Docente model = crearDocente(cursor);
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
