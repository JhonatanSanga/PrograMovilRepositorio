package com.example.proyectoprogramovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoprogramovil.Modelo.Estudiante;
import com.example.proyectoprogramovil.controllers.EstudianteController;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class registro extends AppCompatActivity {

    EditText etRegNombre, etRegEmail, etRegCelular, etRegDomicilio, etRegNombreUsuario, etRegContra, etRegRepContra;
    Button btnRegUsuario;
    EstudianteController estudianteController;
    Estudiante est;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etRegNombre=findViewById(R.id.etRegNombre);
        etRegEmail=findViewById(R.id.etRegEmail);
        etRegCelular=findViewById(R.id.etRegCelular);
        etRegDomicilio=findViewById(R.id.etRegDomicilio);
        etRegNombreUsuario=findViewById(R.id.etRegNombreUsuario);
        etRegContra=findViewById(R.id.etRegContra);
        etRegRepContra=findViewById(R.id.etRegRepContra);
        btnRegUsuario=findViewById(R.id.btnRegUsuario);
        estudianteController=new EstudianteController(registro.this);

        btnRegUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String SNombre = etRegNombre.getText().toString();
                    String SEmail = etRegEmail.getText().toString();
                    String SDireccion = etRegDomicilio.getText().toString();
                    String STelefono = etRegCelular.getText().toString();
                    String SNombreUsuario = etRegNombreUsuario.getText().toString();
                    String SContra = etRegContra.getText().toString();
                    String SRepContra = etRegRepContra.getText().toString();

                    if ("".equals(SNombre)){
                        etRegNombre.setError("Debes ingresar el nombre");
                        etRegNombre.requestFocus();
                        return;
                    }
                    if ("".equals(SEmail)){
                        etRegEmail.setError("Debes ingresar el email");
                        etRegEmail.requestFocus();
                        return;
                    }
                    if ("".equals(SDireccion)){
                        etRegDomicilio.setError("Debes ingresar la dirección");
                        etRegDomicilio.requestFocus();
                        return;
                    }
                    if ("".equals(STelefono)){
                        etRegCelular.setError("Debes ingresar tu numero de telefono");
                        etRegCelular.requestFocus();
                        return;
                    }
                    if ("".equals(SNombreUsuario)){
                        etRegNombreUsuario.setError("Debes ingresar el nombre de usuario");
                        etRegNombreUsuario.requestFocus();
                        return;
                    }
                    if ("".equals(SContra)){
                        etRegContra.setError("Debes ingresar la contraseña");
                        etRegContra.requestFocus();
                        return;
                    }
                    if ("".equals(SRepContra)){
                        etRegRepContra.setError("Debes repetir tu contraseña");
                        etRegRepContra.requestFocus();
                        return;
                    }
                    if (!(SContra.equals(SRepContra))){
                        etRegRepContra.setError("Las contraseñas deben ser iguales");
                        etRegRepContra.requestFocus();
                        return;
                    }
                    SRepContra=encriptar(SContra);
                    est = new Estudiante(SNombre,SEmail,SNombreUsuario,SRepContra,SDireccion,STelefono);
                    long creado=estudianteController.nuevoEstudiante(est);
                    if(creado==-1){
                        Toast.makeText(registro.this, "Error al insertar el usuario", Toast.LENGTH_SHORT).show();
                        etRegNombre.setText("Error al insertar el usuario");
                    }
                    else{
                        Toast.makeText(registro.this, "Se insertó correctamente", Toast.LENGTH_SHORT).show();
                        etRegNombre.setText("Se insertó correctamente");
                        Intent intent=new Intent(registro.this,MainActivity.class);
                        startActivity(intent);
                    }
                }catch(Exception ex){
                    Toast.makeText(registro.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
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
