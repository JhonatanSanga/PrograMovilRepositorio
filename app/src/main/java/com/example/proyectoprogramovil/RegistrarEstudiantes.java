package com.example.proyectoprogramovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegistrarEstudiantes extends AppCompatActivity {

    EditText etRegSiu, etRegNombre, etRegCarrera, etRegTemperatura;
    Button btnRegEstudiante;

    static final String NOMBRE_ARCHIVO = "estudiantes.txt";
    static final int BLOQUE_LECTURA=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_estudiantes);

        //sale error pero funciona xDDDD
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //el error solo es una sugerencia, no deberia estar en rojo, no me gustaaaaaaaaaaaaaaaaa

        etRegSiu=findViewById(R.id.etRegSiu);
        etRegNombre=findViewById(R.id.etRegNombreEstudiante);
        etRegCarrera=findViewById(R.id.etRegCarrera);
        etRegTemperatura=findViewById(R.id.etRegTemperatura);

        btnRegEstudiante=findViewById(R.id.btnRegEstudiante);

        String[] archivos=fileList();
        verificarArchivo(archivos);

        btnRegEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String SNombre = etRegNombre.getText().toString();
                    String SCarrera = etRegCarrera.getText().toString();
                    String SSiu = etRegSiu.getText().toString();
                    String STemperatura = etRegTemperatura.getText().toString();

                    Date c = Calendar.getInstance().getTime();

                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    String SFecha = df.format(c);

                    if ("".equals(SNombre)){
                        etRegNombre.setError("Debes ingresar el nombre");
                        etRegNombre.requestFocus();
                        return;
                    }
                    if ("".equals(SCarrera)){
                        etRegCarrera.setError("Debes ingresar la carrera");
                        etRegCarrera.requestFocus();
                        return;
                    }
                    if ("".equals(SSiu)){
                        etRegSiu.setError("Debes ingresar el SIU");
                        etRegSiu.requestFocus();
                        return;
                    }
                    if ("".equals(STemperatura)){
                        etRegTemperatura.setError("Debes ingresar la Temperatura");
                        etRegTemperatura.requestFocus();
                        return;
                    }



                    FileInputStream fileInputStream= openFileInput(NOMBRE_ARCHIVO);
                    InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

                    //encontrar siguiente ID
                    char[] bufferLectura=new char[BLOQUE_LECTURA];
                    String s="";
                    int charLectura;

                    while((charLectura=inputStreamReader.read(bufferLectura))>0){
                        //convertir los char a string
                        String leidoString=String.copyValueOf(bufferLectura,0,charLectura);
                        s += leidoString;
                    }
                    String[] alumnos=s.split("/");

                    //Agregar estudiante
                    String estudiante=SFecha+","+SSiu+","+SNombre+","+SCarrera+","+STemperatura+"/";
                    FileOutputStream fileOutputStream = openFileOutput(NOMBRE_ARCHIVO,MODE_APPEND);
                    OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);

                    outputStreamWriter.write(estudiante+"\n");
                    outputStreamWriter.flush();
                    outputStreamWriter.close();

                    Toast.makeText(getApplicationContext(), "Estudiante Registrado", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),estudiantes.class);
                    startActivity(intent);
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void verificarArchivo(String[] archivos){
        //verificar si existe, si no, lo creamos
        boolean existe=false;
        for (int i = 0; i < archivos.length; i++){
            if(NOMBRE_ARCHIVO.equals(archivos[i])){
                //archivo existe
                Toast.makeText(getApplicationContext(), "El archivo existe", Toast.LENGTH_SHORT).show();
                existe=true;
            }
        }
        if(!existe){
            //el archivo no existe
            try{
                FileOutputStream fileOutputStream=openFileOutput(NOMBRE_ARCHIVO,MODE_APPEND);
                OutputStreamWriter outputStreamWriter= new OutputStreamWriter(fileOutputStream);
                Toast.makeText(getApplicationContext(), "Archivo creado con exito", Toast.LENGTH_SHORT).show();

            }catch(Exception ex){
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
