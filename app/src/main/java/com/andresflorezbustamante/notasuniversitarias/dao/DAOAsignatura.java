package com.andresflorezbustamante.notasuniversitarias.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.andresflorezbustamante.notasuniversitarias.entidades.Asignatura;
import com.andresflorezbustamante.notasuniversitarias.entidades.Programa;

public class DAOAsignatura {

    private Context context;
    private ConexionBasedatos conexionBasedatos;
    private DAOPrograma daoPrograma;

    public DAOAsignatura(Context context) {
        this.context = context;
        this.conexionBasedatos = new ConexionBasedatos(context);
        this.daoPrograma = new DAOPrograma(context);
    }

    public void agregarAsignatura(Asignatura asignatura) {
        ContentValues valores = new ContentValues();
        valores.put("codigo", asignatura.getCodigo());
        valores.put("nombre", asignatura.getNombre());
        valores.put("programas_id", asignatura.getCarrera().getId());
        conexionBasedatos.insertar("Asignaturas", valores);
    }

    public void eliminarAsignatura(String codigo) {
        String where = "codigo=?";
        String[] valorWhere = new String[]{codigo};
        conexionBasedatos.eliminar("Asignaturas", where, valorWhere);
    }

    public void modificarAsignatura(Asignatura asignatura) {
        ContentValues valores = new ContentValues();
        valores.put("nombre", asignatura.getNombre());
        valores.put("programas_id", asignatura.getCarrera().getId());
        String where = "codigo=?";
        String[] valorWhere = new String[]{asignatura.getCodigo()};
        conexionBasedatos.actualizar("Asignaturas", valores, where, valorWhere);
    }

    public int totalAsignaturas() {
        Cursor cursor = conexionBasedatos.consultar("SELECT COUNT(*) FROM Asignaturas", null);
        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        return total;
    }

    public Asignatura buscarAsignatura(String codigo) {
        String sql = "SELECT * FROM Asignaturas WHERE codigo=?";
        String[] valorWhere = new String[]{codigo};
        Cursor cursor = conexionBasedatos.consultar(sql, valorWhere);
        if (cursor.moveToFirst()) {
            Programa programa = daoPrograma.buscarPrograma(cursor.getInt(2));
            Asignatura asignatura = new Asignatura(
                    cursor.getString(0), cursor.getString(1), programa);
            cursor.close();
            return asignatura;
        }
        cursor.close();
        throw new RuntimeException("No se encontró la Asignatura con codigo=" + codigo);
    }

    public List<Asignatura> listarTodasLasAsignaturas() {
        List<Asignatura> lista = new ArrayList<>();
        Cursor cursor = conexionBasedatos.consultar("SELECT * FROM Asignaturas", null);
        if (cursor.moveToFirst()) {
            do {
                Programa programa = daoPrograma.buscarPrograma(cursor.getInt(2));
                lista.add(new Asignatura(
                        cursor.getString(0), cursor.getString(1), programa));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}