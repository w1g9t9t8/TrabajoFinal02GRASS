package edu.pe.udaff.ist.trabajofinal02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.pe.udaff.ist.trabajofinal02.Utilidades.Utilidades;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText user,pass;
    Button btnIniciarS, btnRegistrar;
    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);

        user=(EditText)findViewById(R.id.txt_L_Usuario);
        pass=(EditText)findViewById(R.id.txt_L_Contraseña);
        btnIniciarS=(Button)findViewById(R.id.btn_L_IniciarSesion);
        btnRegistrar=(Button)findViewById(R.id.btn_L_Registrarse);

        btnIniciarS.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_L_IniciarSesion:
                ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);
                String u=user.getText().toString();
                String p=pass.getText().toString();
                SQLiteDatabase db=conn.getReadableDatabase();
                String[] parametros={u,p};

                try {
                    //select nombre,apellido,dinero from usuario where user pass
                    Cursor cursor=db.rawQuery("SELECT "+Utilidades.USUARIO_idusuario+","+Utilidades.USUARIO_nombre+","+Utilidades.USUARIO_apellido+" FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.USUARIO_usuario+"=? AND "+ Utilidades.USUARIO_contraseña+"=?",parametros);
                    cursor.moveToFirst();
                    int bdidusuario =cursor.getInt(0);
                    String bdNombre=cursor.getString(1);
                    String bdApellido=cursor.getString(2);

                    Toast.makeText(this,"Bienvenido "+ bdNombre+" "+bdApellido,Toast.LENGTH_LONG).show();

                    Intent IrHome = new Intent(MainActivity.this,Home.class);
                    IrHome.putExtra("Id",bdidusuario);
                    startActivity(IrHome);
                    db.close();
                    finish();

                }catch (Exception e){
                    Toast.makeText(this,"Usuario y/o Contraseña Incorrecta",Toast.LENGTH_LONG).show();
            }





                break;
            case R.id.btn_L_Registrarse:
                Intent IrRegistrarse = new Intent(MainActivity.this,Register.class);
                startActivity(IrRegistrarse);
                break;

        }
    }




}
