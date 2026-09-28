package com.andresflorezbustamante.notasuniversitarias.entidades;

public class Alumno {

    private String codigo;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private Programa carrera;

    public Alumno(String codigo, String nombre, String apellidos, String email, String telefono, Programa carrera) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.carrera = carrera;
    }

    public Alumno() {
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Programa getCarrera() {
        return carrera;
    }

    public void setCarrera(Programa carrera) {
        this.carrera = carrera;
    }
}