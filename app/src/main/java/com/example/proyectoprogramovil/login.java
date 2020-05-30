package com.example.proyectoprogramovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

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
                    contra=encriptar(contra);

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
                                Intent intent=new Intent(getApplicationContext(),estudiantes.class);
                                startActivity(intent);
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
    private String encriptar (String Scontra) throws Exception{
        SecretKeySpec secretKey=generateKey(Scontra);
        Cipher cipher=Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        byte[] datosEncriptadosByte=cipher.doFinal(Scontra.getBytes());
        String datosEncriptados= Base64.encodeToString(datosEncriptadosByte,Base64.DEFAULT);
        return datosEncriptados;
    }
    private SecretKeySpec generateKey(String contra) throws Exception
    {
        MessageDigest sha=MessageDigest.getInstance("SHA-256");
        byte[] key=contra.getBytes("UTF-8");
        key=sha.digest(key);
        SecretKeySpec secretKey=new SecretKeySpec(key,"AES");
        return secretKey;
    }
}
