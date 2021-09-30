package com.example.javasqlitecrud.model;

public class Docente {
    private Integer _iddoc;
    private String codigo;
    private String nombre;
    private String dni;
    private String telefono;
    private String correo;

    public Docente() {

    }

    public Docente(Integer _iddoc, String codigo, String nombre, String dni, String telefono, String correo) {
        this._iddoc = _iddoc;
        this.codigo = codigo;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Integer get_iddoc() {
        return _iddoc;
    }

    public void set_iddoc(Integer _iddoc) {
        this._iddoc = _iddoc;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
