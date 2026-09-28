package com.andresflorezbustamante.notasuniversitarias.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.andresflorezbustamante.notasuniversitarias.entidades.Alumno;
import com.andresflorezbustamante.notasuniversitarias.entidades.Programa;

public class DAOAlumno {

    private Context context;
    private ConexionBasedatos conexionBasedatos;
    private DAOPrograma daoPrograma;

    public DAOAlumno(Context context) {
        this.context = context;
        this.conexionBasedatos = new ConexionBasedatos(context);
        this.daoPrograma = new DAOPrograma(context);
    }

    public void agregarAlumno(Alumno alumno) {
        ContentValues valores = new ContentValues();
        valores.put("codigo", alumno.getCodigo());
        valores.put("nombre", alumno.getNombre());
        valores.put("apellidos", alumno.getApellidos());
        valores.put("email", alumno.getEmail());
        valores.put("telefono", alumno.getTelefono());
        valores.put("programas_id", alumno.getCarrera().getId());
        conexionBasedatos.insertar("Alumnos", valores);
    }

    public void eliminarAlumno(String codigo) {
        String where = "codigo=?";
        String[] valorWhere = new String[]{codigo};
        conexionBasedatos.eliminar("Alumnos", where, valorWhere);
    }

    public void modificarAlumno(Alumno alumno) {
        ContentValues valores = new ContentValues();
        valores.put("nombre", alumno.getNombre());
        valores.put("apellidos", alumno.getApellidos());
        valores.put("email", alumno.getEmail());
        valores.put("telefono", alumno.getTelefono());
        valores.put("programas_id", alumno.getCarrera().getId());
        String where = "codigo=?";
        String[] valorWhere = new String[]{alumno.getCodigo()};
        conexionBasedatos.actualizar("Alumnos", valores, where, valorWhere);
    }

    public int totalAlumnos() {
        Cursor cursor = conexionBasedatos.consultar("SELECT COUNT(*) FROM Alumnos", null);
        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        return total;
    }

    public Alumno buscarAlumno(String codigo) {
        String sql = "SELECT * FROM Alumnos WHERE codigo=?";
        String[] valorWhere = new String[]{codigo};
        Cursor cursor = conexionBasedatos.consultar(sql, valorWhere);
        if (cursor.moveToFirst()) {
            Programa programa = daoPrograma.buscarPrograma(cursor.getInt(5));
            Alumno alumno = new Alumno(
                    cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), programa);
            cursor.close();
            return alumno;
        }
        cursor.close();
        throw new RuntimeException("No se encontró el Alumno con codigo=" + codigo);
    }

    public List<Alumno> listarTodosLosAlumnos() {
        List<Alumno> lista = new ArrayList<>();
        Cursor cursor = conexionBasedatos.consultar("SELECT * FROM Alumnos", null);
        if (cursor.moveToFirst()) {
            do {
                Programa programa = daoPrograma.buscarPrograma(cursor.getInt(5));
                lista.add(new Alumno(
                        cursor.getString(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), programa));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}