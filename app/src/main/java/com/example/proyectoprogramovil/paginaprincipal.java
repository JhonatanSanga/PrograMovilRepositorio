package com.example.proyectoprogramovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class paginaprincipal extends AppCompatActivity {

    Button btnLogin, btnInfo, btnRegistro;
    ImageView ivLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginaprincipal);
        btnInfo=findViewById(R.id.btn_quienes_somos);
        btnLogin=findViewById(R.id.btn_login);
        btnRegistro=findViewById(R.id.btn_registro);
        ivLogo=findViewById(R.id.ivLogo);

        rotarLogo();

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),quienes_somos.class);
                startActivity(intent);
            }
        });
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),registro.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });
    }
    private void rotarLogo() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.girarlogo);
        animation.setFillAfter(false);
        ivLogo.startAnimation(animation);
    }
}
