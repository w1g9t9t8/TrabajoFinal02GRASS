package edu.pe.udaff.ist.trabajofinal02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.pe.udaff.ist.trabajofinal02.Utilidades.Utilidades;

public class EditarGRASS extends AppCompatActivity implements View.OnClickListener {
    TextView nomUsuario,estadoGrass;
    EditText nomEGGRASS,descEGGRASS,precioEGGRASS,direccEGGRASS,celEGGRASS,tamañEGGRASS;
    Button btnVolverHome,btnActualizarGRASS,btnEstado;
    int id=0,idgrass=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_grass);

        nomUsuario=(TextView)findViewById(R.id.txt_EG_NomUsuario);
        nomEGGRASS=(EditText)findViewById(R.id.txt_EG_NomGRASS);
        descEGGRASS =(EditText)findViewById(R.id.txt_EF_DescripcionGRASS);
        precioEGGRASS=(EditText)findViewById(R.id.txt_EG_PrecioGRASS);
        direccEGGRASS=(EditText)findViewById(R.id.txt_EG_DireccionGRASS);
        tamañEGGRASS =(EditText)findViewById(R.id.txt_EG_TamañoGRASS);
        celEGGRASS=(EditText)findViewById(R.id.txt_EG_CelularGRASS);
        estadoGrass=(EditText)findViewById(R.id.txt_EG_ESTADO);

        btnActualizarGRASS=(Button) findViewById(R.id.btn_EG_ActualizarGRASS);
        btnVolverHome=(Button) findViewById(R.id.btn_EG_VolverHome);

        Bundle b= getIntent().getExtras();
        id=b.getInt("Id");
        idgrass=b.getInt("IdGRASS");

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);
        ConexionSQLiteHelper conn2 = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        SQLiteDatabase db2=conn2.getReadableDatabase();
        String idusu = Integer.toString(id);
        String idnew=Integer.toString(idgrass);
        String[] parametrosusuario={idusu};
        String[] parametros={idnew};
        Cursor cursornom = db2.rawQuery("SELECT "+Utilidades.USUARIO_nombre+","+Utilidades.USUARIO_apellido+" FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.USUARIO_idusuario+"=?  ",parametrosusuario);
        cursornom.moveToFirst();
        String nombd=cursornom.getString(0);
        String apebd=cursornom.getString(1);
        db2.close();

        nomUsuario.setHint(nombd+" "+apebd);
        Cursor cursor=db.rawQuery("SELECT "+ Utilidades.GRASS_nombreGrass+","+Utilidades.GRASS_descripcionGRASS+","+Utilidades.GRASS_precioGRASS+","+Utilidades.GRASS_direccionGRASS+","+Utilidades.GRASS_celularGRASS+","+Utilidades.GRASS_tamañoGRASS+","+Utilidades.GRASS_estadoGRASS+" FROM "+Utilidades.TABLA_GRASS+" WHERE "+Utilidades.GRASS_idgrass+"=?  ",parametros);
        cursor.moveToFirst();

        nomEGGRASS.setText(cursor.getString(0));
        descEGGRASS.setText(cursor.getString(1));
        precioEGGRASS.setText(cursor.getString(2));
        direccEGGRASS.setText(cursor.getString(3));
        celEGGRASS.setText(cursor.getString(4));
        tamañEGGRASS.setText(cursor.getString(5));
        estadoGrass.setText(cursor.getString(6));
        db.close();


        btnActualizarGRASS.setOnClickListener(this);
        btnVolverHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_EG_ActualizarGRASS:

                ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bdTBgrass", null, 1);
                SQLiteDatabase db = conn.getReadableDatabase();
                String idnew = Integer.toString(idgrass);
                String[] parametros = {idnew};
                ContentValues cv = new ContentValues();
                cv.put(Utilidades.GRASS_nombreGrass, nomEGGRASS.getText().toString());
                cv.put(Utilidades.GRASS_descripcionGRASS, descEGGRASS.getText().toString());
                cv.put(Utilidades.GRASS_precioGRASS, precioEGGRASS.getText().toString());
                cv.put(Utilidades.GRASS_direccionGRASS, direccEGGRASS.getText().toString());
                cv.put(Utilidades.GRASS_celularGRASS, celEGGRASS.getText().toString());
                cv.put(Utilidades.GRASS_tamañoGRASS, tamañEGGRASS.getText().toString());
                cv.put(Utilidades.GRASS_estadoGRASS, estadoGrass.getText().toString());

                    db.update(Utilidades.TABLA_GRASS,cv,Utilidades.GRASS_idgrass+"=?",parametros);
                    Toast.makeText(getApplicationContext(),"Se Actualizo correctamente sus datos!",Toast.LENGTH_SHORT).show();
                    db.close();
                    Intent IrHome = new Intent(EditarGRASS.this,Home.class);
                    IrHome.putExtra("Id",id);
                    startActivity(IrHome);
                    finish();

                break;

            case R.id.btn_EG_VolverHome:
                Intent IrHome2 = new Intent(EditarGRASS.this,Home.class);
                IrHome2.putExtra("Id",id);
                startActivity(IrHome2);
                finish();
                break;

        }
    }
}
