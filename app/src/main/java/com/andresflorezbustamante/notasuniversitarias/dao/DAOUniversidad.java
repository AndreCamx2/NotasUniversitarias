package com.andresflorezbustamante.notasuniversitarias.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.andresflorezbustamante.notasuniversitarias.entidades.Universidad;

public class DAOUniversidad {

    private Context context;
    private ConexionBasedatos conexionBasedatos;

    public DAOUniversidad(Context context) {
        this.context = context;
        this.conexionBasedatos = new ConexionBasedatos(context);
    }

    public void agregarUniversidad(Universidad universidad) {
        ContentValues valores = new ContentValues();
        valores.put("nombre", universidad.getNombre());
        valores.put("www", universidad.getWww());
        conexionBasedatos.insertar("Universidades", valores);
    }

    public void eliminarUniversidad(int id) {
        String where = "id=?";
        String[] valorWhere = new String[]{String.valueOf(id)};
        conexionBasedatos.eliminar("Universidades", where, valorWhere);
    }

    public void modificarUniversidad(Universidad universidad) {
        ContentValues valores = new ContentValues();
        valores.put("nombre", universidad.getNombre());
        valores.put("www", universidad.getWww());
        String where = "id=?";
        String[] valorWhere = new String[]{universidad.getId()};
        conexionBasedatos.actualizar("Universidades", valores, where, valorWhere);
    }

    public int totalUniversidades() {
        Cursor cursor = conexionBasedatos.consultar("SELECT COUNT(*) FROM Universidades", null);
        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        return total;
    }

    public Universidad buscarUniversidad(int id) {
        String sql = "SELECT * FROM Universidades WHERE id=?";
        String[] valorWhere = new String[]{String.valueOf(id)};
        Cursor cursor = conexionBasedatos.consultar(sql, valorWhere);
        if (cursor.moveToFirst()) {
            Universidad universidad = new Universidad(
                    cursor.getString(0), cursor.getString(1), cursor.getString(2));
            cursor.close();
            return universidad;
        }
        cursor.close();
        throw new RuntimeException("No se encontró la Universidad con id=" + id);
    }

    public List<Universidad> listarTodasLasUniversidades() {
        List<Universidad> lista = new ArrayList<>();
        Cursor cursor = conexionBasedatos.consultar("SELECT * FROM Universidades", null);
        if (cursor.moveToFirst()) {
            do {
                lista.add(new Universidad(
                        cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}