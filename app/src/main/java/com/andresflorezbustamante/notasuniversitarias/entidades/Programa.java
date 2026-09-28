package com.andresflorezbustamante.notasuniversitarias.entidades;

public class Programa {

    private String id;
    private String nombre;
    private String director;
    private String email;
    private Universidad institucion;

    public Programa(String id, String nombre, String director, String email, Universidad institucion) {
        this.id = id;
        this.nombre = nombre;
        this.director = director;
        this.email = email;
        this.institucion = institucion;
    }

    public Programa() {
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Universidad getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Universidad institucion) {
        this.institucion = institucion;
    }
}