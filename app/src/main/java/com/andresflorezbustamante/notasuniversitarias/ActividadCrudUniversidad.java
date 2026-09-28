package com.andresflorezbustamante.notasuniversitarias;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.andresflorezbustamante.notasuniversitarias.dao.DAOUniversidad;
import com.andresflorezbustamante.notasuniversitarias.entidades.Universidad;

public class ActividadCrudUniversidad extends AppCompatActivity {

    private EditText campoId;
    private EditText campoNombre;
    private EditText campoWww;
    private DAOUniversidad dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_crud_universidad);

        campoId = findViewById(R.id.campoId);
        campoNombre = findViewById(R.id.campoNombre);
        campoWww = findViewById(R.id.campoWww);

        dao = new DAOUniversidad(this);
    }

    // Se invoca al pulsar el botón Agregar
    public void agregarUniversidad(View v) {
        String nombre = campoNombre.getText().toString();
        String www = campoWww.getText().toString();

        if (nombre.trim().isEmpty()) {
            Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        Universidad u = new Universidad();
        u.setNombre(nombre);
        u.setWww(www);

        try {
            dao.agregarUniversidad(u);
            Toast.makeText(this, "Universidad agregada", Toast.LENGTH_SHORT).show();
            limpiarCampos(v);
        } catch (Exception e) {
            Toast.makeText(this, "Error al agregar: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // Se invoca al pulsar el botón Buscar
    public void buscarUniversidad(View v) {
        String textoId = campoId.getText().toString();

        if (textoId.trim().isEmpty()) {
            Toast.makeText(this, "Escribe un Id para buscar", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int id = Integer.parseInt(textoId);
            Universidad u = dao.buscarUniversidad(id);
            campoNombre.setText(u.getNombre());
            campoWww.setText(u.getWww());
            Toast.makeText(this, "Universidad encontrada", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "El Id debe ser un número", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "No existe una Universidad con ese Id", Toast.LENGTH_LONG).show();
        }
    }

    // Se invoca al pulsar el botón Limpiar
    public void limpiarCampos(View v) {
        campoId.setText("");
        campoNombre.setText("");
        campoWww.setText("");
    }
}