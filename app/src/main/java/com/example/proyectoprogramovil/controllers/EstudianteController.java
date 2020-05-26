package com.example.proyectoprogramovil.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyectoprogramovil.AyudanteBaseDeDatos;
import com.example.proyectoprogramovil.Modelo.Estudiante;

public class EstudianteController {

    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "usuarios";

    public EstudianteController(Context context){
        ayudanteBaseDeDatos= new AyudanteBaseDeDatos(context);
    }

    //nuevo estudiante
    public long nuevoEstudiante(Estudiante estudiante){
        SQLiteDatabase baseDeDatos =ayudanteBaseDeDatos.getReadableDatabase();
        ContentValues valoresInsertar= new ContentValues();
        valoresInsertar.put("nombre",estudiante.getNombre());
        valoresInsertar.put("email",estudiante.getEmail());
        valoresInsertar.put("direccion", estudiante.getDireccion());
        valoresInsertar.put("telefono", estudiante.getTelefono());
        valoresInsertar.put("usuario", estudiante.getUsuario());
        valoresInsertar.put("contrasenia", estudiante.getContrasenia());
        return baseDeDatos.insert(NOMBRE_TABLA,null,valoresInsertar);
    }
}
