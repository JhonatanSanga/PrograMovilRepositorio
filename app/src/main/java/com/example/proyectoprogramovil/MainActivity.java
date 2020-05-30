package com.example.proyectoprogramovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HiloSecundario hilo= new HiloSecundario();
        hilo.start();
    }
    class HiloSecundario extends Thread{

        @Override
        public void run() {
            SystemClock.sleep(2000);
            /*Intent intent=new Intent(getApplicationContext(),paginaprincipal.class);
            startActivity(intent);*/
            Intent intent=new Intent(getApplicationContext(),estudiantes.class);
            startActivity(intent);
        }
    }
}
