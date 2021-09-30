package com.example.javasqlitecrud.model;

public class Escuela {
    private Integer _idesc;
    private String nombre;
    private String facultad;

    public Escuela() {

    }

    public Escuela(Integer _idesc, String nombre, String facultad) {
        this._idesc = _idesc;
        this.nombre = nombre;
        this.facultad = facultad;
    }

    public Integer get_idesc() {
        return _idesc;
    }

    public void set_idesc(Integer _idesc) {
        this._idesc = _idesc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
}
