package com.andresflorezbustamante.notasuniversitarias.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionBasedatos extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "notasuniversitarias.db";
    private static final int VERSION_BD = 1;

    private SQLiteDatabase conexion;

    public ConexionBasedatos(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    // Se invoca automáticamente la primera vez que se crea la BD
    @Override
    public void onCreate(SQLiteDatabase bd) {
        // sqlBackup trae varias sentencias separadas por ";", las ejecutamos una por una
        String[] sentencias = CodigoSQLBackup.sqlBackup.split(";");
        for (String sentencia : sentencias) {
            if (!sentencia.trim().isEmpty()) {
                bd.execSQL(sentencia);
            }
        }
    }

    // Se invoca automáticamente si cambias VERSION_BD (para migraciones futuras)
    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionVieja, int versionNueva) {
        bd.execSQL("DROP TABLE IF EXISTS Calificaciones");
        bd.execSQL("DROP TABLE IF EXISTS Asignaturas");
        bd.execSQL("DROP TABLE IF EXISTS Alumnos");
        bd.execSQL("DROP TABLE IF EXISTS Programas");
        bd.execSQL("DROP TABLE IF EXISTS Universidades");
        onCreate(bd);
    }

    // Ejecuta
    public void ejecutarSQL(String sqlComando) {
        try {
            conexion = getWritableDatabase();
            conexion.execSQL(sqlComando);
        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando SQL: " + e.getMessage());
        }
    }

    // INSERT
    public long insertar(String tabla, ContentValues columnasValor) {
        conexion = getWritableDatabase();
        return conexion.insert(tabla, null, columnasValor);
    }

    // UPDATE
    public int actualizar(String tabla, ContentValues columnasValor,
                          String whereColumnasIgualValor, String[] valorWhere) {
        conexion = getWritableDatabase();
        return conexion.update(tabla, columnasValor, whereColumnasIgualValor, valorWhere);
    }

    // DELETE
    public int eliminar(String tabla, String whereColumnasIgualValor, String[] valorWhere) {
        conexion = getWritableDatabase();
        return conexion.delete(tabla, whereColumnasIgualValor, valorWhere);
    }

    // SELECT
    public Cursor consultar(String consultaSQL, String[] valorWhere) {
        conexion = getReadableDatabase();
        return conexion.rawQuery(consultaSQL, valorWhere);
    }

    // SELECT
    public Cursor consultar(String tablas, String[] soloColumnas,
                            String whereColumnasIgualValor, String[] valorWhere,
                            String groupBy, String having, String orderBy, String limite) {
        conexion = getReadableDatabase();
        return conexion.query(tablas, soloColumnas, whereColumnasIgualValor, valorWhere,
                groupBy, having, orderBy, limite);
    }

    // Cerrar la conexión
    public void cerrarConexion() {
        if (conexion != null && conexion.isOpen()) {
            conexion.close();
        }
    }
}