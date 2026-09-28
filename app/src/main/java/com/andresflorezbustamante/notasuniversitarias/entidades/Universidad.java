package com.andresflorezbustamante.notasuniversitarias.entidades;

public class Universidad {

    private String id;
    private String nombre;
    private String www;

    public Universidad(String id, String nombre, String www) {
        this.id = id;
        this.nombre = nombre;
        this.www = www;
    }

    public Universidad() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }
}