package com.example.proyectoprogramovil;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText etNombreUsuario,etContraUsuario;
    Button btnIngresarSis;
    TextView tvMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNombreUsuario=findViewById(R.id.etNombreUsuario);
        etContraUsuario=findViewById(R.id.etContraUsuario);
        btnIngresarSis=findViewById(R.id.btnIngresarSis);
        tvMensaje=findViewById(R.id.tvRespuesta);

        btnIngresarSis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String usuario=etNombreUsuario.getText().toString();
                    String contra=etContraUsuario.getText().toString();

                    AyudanteBaseDeDatos ayudanteBaseDeDatos=new AyudanteBaseDeDatos(login.this);
                    SQLiteDatabase db = ayudanteBaseDeDatos.getReadableDatabase();

                    Cursor c = db.rawQuery("SELECT usuario, contrasenia FROM usuarios",null);

                    if(c.moveToFirst()){
                        String m="";
                        do{
                            String usuarioLeido=c.getString(0);
                            String contraLeida=c.getString(1);
                            if(usuario.equals(usuarioLeido) && contra.equals(contraLeida)){
                                m="INGRESASTE CON EXITO :D";
                                tvMensaje.setText(m);
                            }
                        }while(c.moveToNext());
                        if(m.equals("")){
                            tvMensaje.setText("Error al ingresar");
                        }
                    }
                }catch (Exception ex){
                    tvMensaje.setText(ex.getMessage());
                }
            }
        });
    }
}
