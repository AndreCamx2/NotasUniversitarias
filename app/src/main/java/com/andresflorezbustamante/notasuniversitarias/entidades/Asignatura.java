package com.andresflorezbustamante.notasuniversitarias.entidades;

public class Asignatura {

    private String codigo;
    private String nombre;
    private Programa carrera;

    public Asignatura(String codigo, String nombre, Programa carrera) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.carrera = carrera;
    }

    public Asignatura() {
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

    public Programa getCarrera() {
        return carrera;
    }

    public void setCarrera(Programa carrera) {
        this.carrera = carrera;
    }
}