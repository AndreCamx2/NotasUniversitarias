package com.andresflorezbustamante.notasuniversitarias.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.andresflorezbustamante.notasuniversitarias.entidades.Programa;
import com.andresflorezbustamante.notasuniversitarias.entidades.Universidad;

public class DAOPrograma {

    private Context context;
    private ConexionBasedatos conexionBasedatos;
    private DAOUniversidad daoUniversidad;

    public DAOPrograma(Context context) {
        this.context = context;
        this.conexionBasedatos = new ConexionBasedatos(context);
        this.daoUniversidad = new DAOUniversidad(context);
    }

    public void agregarPrograma(Programa programa) {
        ContentValues valores = new ContentValues();
        valores.put("nombre", programa.getNombre());
        valores.put("director", programa.getDirector());
        valores.put("email", programa.getEmail());
        valores.put("universidades_id", programa.getInstitucion().getId());
        conexionBasedatos.insertar("Programas", valores);
    }

    public void eliminarPrograma(int id) {
        String where = "id=?";
        String[] valorWhere = new String[]{String.valueOf(id)};
        conexionBasedatos.eliminar("Programas", where, valorWhere);
    }

    public void modificarPrograma(Programa programa) {
        ContentValues valores = new ContentValues();
        valores.put("nombre", programa.getNombre());
        valores.put("director", programa.getDirector());
        valores.put("email", programa.getEmail());
        valores.put("universidades_id", programa.getInstitucion().getId());
        String where = "id=?";
        String[] valorWhere = new String[]{programa.getId()};
        conexionBasedatos.actualizar("Programas", valores, where, valorWhere);
    }

    public int totalProgramas() {
        Cursor cursor = conexionBasedatos.consultar("SELECT COUNT(*) FROM Programas", null);
        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        return total;
    }

    public Programa buscarPrograma(int id) {
        String sql = "SELECT * FROM Programas WHERE id=?";
        String[] valorWhere = new String[]{String.valueOf(id)};
        Cursor cursor = conexionBasedatos.consultar(sql, valorWhere);
        if (cursor.moveToFirst()) {
            Universidad universidad = daoUniversidad.buscarUniversidad(cursor.getInt(4));
            Programa programa = new Programa(
                    cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), universidad);
            cursor.close();
            return programa;
        }
        cursor.close();
        throw new RuntimeException("No se encontró el Programa con id=" + id);
    }

    public List<Programa> listarTodosLosProgramas() {
        List<Programa> lista = new ArrayList<>();
        Cursor cursor = conexionBasedatos.consultar("SELECT * FROM Programas", null);
        if (cursor.moveToFirst()) {
            do {
                Universidad universidad = daoUniversidad.buscarUniversidad(cursor.getInt(4));
                lista.add(new Programa(
                        cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), universidad));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}