package com.andresflorezbustamante.notasuniversitarias.dao;

public class CodigoSQLBackup {

    public static final String sqlBackup =
            "CREATE TABLE Universidades (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                    "nombre VARCHAR NOT NULL," +
                    "www VARCHAR);" +

                    "CREATE TABLE Programas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                    "nombre VARCHAR NOT NULL," +
                    "director VARCHAR DEFAULT 'John Carlos Arrieta Arrieta'," +
                    "email VARCHAR," +
                    "universidades_id INTEGER NOT NULL," +
                    "FOREIGN KEY (universidades_id) REFERENCES Universidades(id));" +

                    "CREATE TABLE Alumnos (" +
                    "codigo VARCHAR PRIMARY KEY NOT NULL UNIQUE," +
                    "nombre VARCHAR NOT NULL," +
                    "apellidos VARCHAR NOT NULL," +
                    "email VARCHAR," +
                    "telefono VARCHAR," +
                    "programas_id INTEGER NOT NULL," +
                    "FOREIGN KEY (programas_id) REFERENCES Programas(id));" +

                    "CREATE TABLE Asignaturas (" +
                    "codigo VARCHAR PRIMARY KEY NOT NULL UNIQUE," +
                    "nombre VARCHAR NOT NULL," +
                    "programas_id INTEGER," +
                    "FOREIGN KEY (programas_id) REFERENCES Programas(id));" +

                    "CREATE TABLE Calificaciones (" +
                    "alumnos_id VARCHAR NOT NULL," +
                    "asignaturas_id VARCHAR NOT NULL," +
                    "nota1 FLOAT DEFAULT 0.0," +
                    "peso1 FLOAT DEFAULT 0.30," +
                    "nota2 FLOAT DEFAULT 0.0," +
                    "peso2 FLOAT DEFAULT 0.30," +
                    "nota3 FLOAT DEFAULT 0.0," +
                    "peso3 FLOAT DEFAULT 0.40," +
                    "PRIMARY KEY (alumnos_id, asignaturas_id)," +
                    "FOREIGN KEY (alumnos_id) REFERENCES Alumnos(codigo)," +
                    "FOREIGN KEY (asignaturas_id) REFERENCES Asignaturas(codigo));";
}