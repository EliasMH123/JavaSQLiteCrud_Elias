package com.example.javasqlitecrud.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "DB2001";
    private static final int DB_VERSION = 1;
    public DBHelper(Context context){ super(context,DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(_id integer primary key autoincrement, "
                +"nombre text not null, login not null, clave text not null)");
        db.execSQL("insert into usuarios(nombre, login, clave) values('Elias Mamani', 'elias2021', '123456')");
        db.execSQL("create table escuelas(_idesc integer primary key autoincrement, "
                +"" +
                " text not null, "+" facultad text not null)");
        /*db.execSQL("insert into escuelas(nombre, login, clave) values('Ingenieria de Sistemas', 'Ingenieria y Arquitectura')");*/
        db.execSQL("create table docentes(_iddoc integer primary key autoincrement, "
                +"codigo text not null,"+"nombre text not null,"+"dni text not null,"+"telefono text not null,"+"correo text not null)");
        /*db.execSQL("insert into docentes(nombre, login, clave) values('123456789', 'Jose Armando Casas', '87654321', '986321456', 'joseac@upeu.edu.pe')");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static class Usuarios{
        public static final String TABLE = "usuarios";
        public static final String _ID = "_id";
        public static final String NOMBRE = "nombre";
        public static final String LOGIN = "login";
        public static final String CLAVE = "clave";
        public static final String[] COLUMNAS = new String[]{_ID, NOMBRE, LOGIN, CLAVE};
    }

    public static class  Escuelas{
        public static final String TABLE = "escuelas";
        public static final String _IDESC = "_idesc";
        public static final String NOMBRE = "nombre";
        public static final String FACULTAD = "facultad";
        public static final String[] COLUMNAS = new String[]{_IDESC, NOMBRE, FACULTAD};
    }

    public static class Docentes{
        public static final String TABLE = "docentes";
        public static final String _IDDOC = "_iddoc";
        public static final String CODIGO = "codigo";
        public static final String NOMBRE = "nombre";
        public static final String DNI = "dni";
        public static final String TELEFONO = "telefono";
        public static final String CORREO = "correo";
        public static final String[] COLUMNAS = new String[]{_IDDOC, CODIGO, NOMBRE, DNI, TELEFONO, CORREO};
    }
}
