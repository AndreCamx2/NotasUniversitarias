package com.andresflorezbustamante.notasuniversitarias.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.andresflorezbustamante.notasuniversitarias.entidades.Alumno;
import com.andresflorezbustamante.notasuniversitarias.entidades.Asignatura;
import com.andresflorezbustamante.notasuniversitarias.entidades.Calificacion;

public class DAOCalificacion {

    private Context context;
    private ConexionBasedatos conexionBasedatos;
    private DAOAlumno daoAlumno;
    private DAOAsignatura daoAsignatura;

    public DAOCalificacion(Context context) {
        this.context = context;
        this.conexionBasedatos = new ConexionBasedatos(context);
        this.daoAlumno = new DAOAlumno(context);
        this.daoAsignatura = new DAOAsignatura(context);
    }

    // Si ya existe una calificación para ese alumno+asignatura, la actualiza; si no, la crea.
    public void agregarCalificacion(Calificacion calificacion) {
        String where = "alumnos_id=? AND asignaturas_id=?";
        String[] valorWhere = new String[]{
                calificacion.getEstudiante().getCodigo(),
                calificacion.getMateria().getCodigo()};

        Cursor cursor = conexionBasedatos.consultar(
                "SELECT * FROM Calificaciones WHERE " + where, valorWhere);
        boolean existe = cursor.moveToFirst();
        cursor.close();

        ContentValues valores = new ContentValues();
        valores.put("alumnos_id", calificacion.getEstudiante().getCodigo());
        valores.put("asignaturas_id", calificacion.getMateria().getCodigo());
        valores.put("nota1", calificacion.getNota1());
        valores.put("peso1", calificacion.getPeso1());
        valores.put("nota2", calificacion.getNota2());
        valores.put("peso2", calificacion.getPeso2());
        valores.put("nota3", calificacion.getNota3());
        valores.put("peso3", calificacion.getPeso3());

        if (existe) {
            conexionBasedatos.actualizar("Calificaciones", valores, where, valorWhere);
        } else {
            conexionBasedatos.insertar("Calificaciones", valores);
        }
    }

    public void eliminarCalificacion(Alumno estudiante, Asignatura materia) {
        String where = "alumnos_id=? AND asignaturas_id=?";
        String[] valorWhere = new String[]{estudiante.getCodigo(), materia.getCodigo()};
        conexionBasedatos.eliminar("Calificaciones", where, valorWhere);
    }

    public Calificacion buscarCalificacion(Alumno estudiante, Asignatura materia) {
        String sql = "SELECT * FROM Calificaciones WHERE alumnos_id=? AND asignaturas_id=?";
        String[] valorWhere = new String[]{estudiante.getCodigo(), materia.getCodigo()};
        Cursor cursor = conexionBasedatos.consultar(sql, valorWhere);
        if (cursor.moveToFirst()) {
            Calificacion calificacion = new Calificacion(
                    estudiante, materia,
                    cursor.getFloat(2), cursor.getFloat(3),
                    cursor.getFloat(4), cursor.getFloat(5),
                    cursor.getFloat(6), cursor.getFloat(7));
            cursor.close();
            return calificacion;
        }
        cursor.close();
        throw new RuntimeException("No se encontró Calificación para ese Alumno y Asignatura");
    }

    public List<Calificacion> listarTodasLasCalificaciones() {
        List<Calificacion> lista = new ArrayList<>();
        Cursor cursor = conexionBasedatos.consultar("SELECT * FROM Calificaciones", null);
        if (cursor.moveToFirst()) {
            do {
                Alumno estudiante = daoAlumno.buscarAlumno(cursor.getString(0));
                Asignatura materia = daoAsignatura.buscarAsignatura(cursor.getString(1));
                lista.add(new Calificacion(
                        estudiante, materia,
                        cursor.getFloat(2), cursor.getFloat(3),
                        cursor.getFloat(4), cursor.getFloat(5),
                        cursor.getFloat(6), cursor.getFloat(7)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}