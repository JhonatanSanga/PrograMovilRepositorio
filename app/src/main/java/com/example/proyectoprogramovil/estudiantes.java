package com.example.proyectoprogramovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class estudiantes extends AppCompatActivity {

    Button btnRegistrar, btnFiltrar;
    RecyclerView rvEstudiantes;
    EditText etFiltrar;

    static final String NOMBRE_ARCHIVO = "estudiantes.txt";
    static final int BLOQUE_LECTURA=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes);
        btnFiltrar=findViewById(R.id.btnFiltrar);
        btnRegistrar=findViewById(R.id.btnRegEstudiante);
        rvEstudiantes=findViewById(R.id.rvEstudiantes);
        etFiltrar=findViewById(R.id.etFiltrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mostrar Registrar Estudiantes
                Intent intent=new Intent(getApplicationContext(),RegistrarEstudiantes.class);
                startActivity(intent);
            }
        });

        String[] archivos=fileList();
        verificarArchivo(archivos);

        try{
            FileInputStream fileInputStream= openFileInput(NOMBRE_ARCHIVO);
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

            char[] bufferLectura=new char[BLOQUE_LECTURA];
            String s="";
            int charLectura;

            // leer el txt entero
            while((charLectura=inputStreamReader.read(bufferLectura))>0){
                //convertir los char a string
                String leidoString=String.copyValueOf(bufferLectura,0,charLectura);
                s += leidoString;
            }
            Toast.makeText(getApplicationContext(), "CARGADO", Toast.LENGTH_SHORT).show();

            //separar registros
            String[] a=s.split("/");
            //dar formato a los datos

            ArrayList<String> listaDatos;
            listaDatos = new ArrayList<String>();
            /*listaDatos.add("juan perez");
            listaDatos.add("ana perez");*/

            String[] estDatos=new String[a.length-1];
            int tempMin=0;
            for (int i=0;i<estDatos.length;i++){
                //fechaActual, SIU, Nombre, Carrera, Temperatura
                String[] datos=a[i].split(",");
                if(Integer.parseInt(datos[4])>=tempMin){
                    listaDatos.add(datos[0]+"\n"+datos[2]+": "+datos[4]+"ºC");
                }
            }

            rvEstudiantes.setLayoutManager(new GridLayoutManager(this, 1));
            AdaptadorDatos adaptadorDatos = new AdaptadorDatos(listaDatos);
            rvEstudiantes.setAdapter(adaptadorDatos);

            ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,estDatos);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int tempMin=Integer.parseInt(etFiltrar.getText().toString());
                    char[] bufferLectura=new char[BLOQUE_LECTURA];
                    String s="";
                    int charLectura;
                    FileInputStream fileInputStream= openFileInput(NOMBRE_ARCHIVO);
                    InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
                    while((charLectura=inputStreamReader.read(bufferLectura))>0){
                        //convertir los char a string
                        String leidoString=String.copyValueOf(bufferLectura,0,charLectura);
                        s += leidoString;
                    }

                    String[] a=s.split("/");
                    String[] estDatos=new String[a.length-1];
                    ArrayList<String> listaDatos;
                    listaDatos = new ArrayList<String>();
                    for (int i=0;i<estDatos.length;i++){
                        //fechaActual, SIU, Nombre, Carrera, Temperatura
                        String[] datos=a[i].split(",");
                        if(Integer.parseInt(datos[4])>=tempMin){
                            listaDatos.add(datos[0]+"\n"+datos[2]+": "+datos[4]+"ºC");
                        }
                    }
                    rvEstudiantes.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                    AdaptadorDatos adaptadorDatos = new AdaptadorDatos(listaDatos);
                    rvEstudiantes.setAdapter(adaptadorDatos);
                }catch (Exception ex){
                    Toast.makeText(estudiantes.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
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

                //descomentar y ejecutar para dejar el archivo txt en blanco

                /*try {
                    FileOutputStream fileOutputStream=openFileOutput(NOMBRE_ARCHIVO,MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriter= new OutputStreamWriter(fileOutputStream);
                    outputStreamWriter.write("");
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                }catch (Exception ex){

                }*/
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
