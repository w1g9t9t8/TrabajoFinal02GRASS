package edu.pe.udaff.ist.trabajofinal02;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.pe.udaff.ist.trabajofinal02.Utilidades.Utilidades;


public class RegisterGrass extends AppCompatActivity {
TextView nomUsuario;
EditText nomReGRASS,descReGRASS,precioReGRASS,direccReGRASS,celReGRASS,tamañReGRASS;
Button btnVolverHome,btnRegistrarGRASS;



int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_grass);
        nomUsuario=(TextView)findViewById(R.id.txt_RG_NomUsuario);
        nomReGRASS=(EditText)findViewById(R.id.txt_RG_NomGRASS);
       descReGRASS =(EditText)findViewById(R.id.txt_RF_DescripcionGRASS);
        precioReGRASS=(EditText)findViewById(R.id.txt_RG_PrecioGRASS);
        direccReGRASS=(EditText)findViewById(R.id.txt_RG_DireccionGRASS);
        tamañReGRASS =(EditText)findViewById(R.id.txt_RG_TamañoGRASS);
        celReGRASS=(EditText)findViewById(R.id.txt_RG_CelularGRASS);
        btnRegistrarGRASS=(Button) findViewById(R.id.btn_RG_RegistrarGRASS);
        btnVolverHome=(Button) findViewById(R.id.btn_RG_VolverHome);

        Bundle b= getIntent().getExtras();
        id=b.getInt("Id");

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        String idnew=Integer.toString(id);
        String[] parametros={idnew};
        Cursor cursor=db.rawQuery("SELECT "+ Utilidades.USUARIO_usuario+" FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.USUARIO_idusuario+"=?  ",parametros);
        cursor.moveToFirst();
        nomUsuario.setHint(cursor.getString(0));
        db.close();



        btnRegistrarGRASS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String Ngrass,Dgrass,Pgrass,Digrass,Cegrass,Tagrass;
                    Ngrass=nomReGRASS.getText().toString();
                    Dgrass=descReGRASS.getText().toString();
                    Pgrass =precioReGRASS.getText().toString();
                    Digrass=direccReGRASS.getText().toString();
                    Cegrass=celReGRASS.getText().toString();
                Tagrass=tamañReGRASS.getText().toString();
                    if (Ngrass.equals("") || Dgrass.equals("") || Pgrass.equals("") ||Digrass.equals("") || Cegrass.equals("") ||Tagrass.equals("") ){
                        Toast.makeText(getApplicationContext(), "Error Campos Vacios", Toast.LENGTH_SHORT).show();
                    }else {
                        ConexionSQLiteHelper conn2 = new ConexionSQLiteHelper(getApplicationContext(),"bdTBgrass",null,1);

                        //indica que abrimos la bd para editar
                        SQLiteDatabase db2 = conn2.getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        cv.put(Utilidades.GRASS_idusuario,id);
                        cv.put(Utilidades.GRASS_nombreGrass,nomReGRASS.getText().toString());
                        cv.put(Utilidades.GRASS_descripcionGRASS,descReGRASS.getText().toString());
                        cv.put(Utilidades.GRASS_precioGRASS,Double.parseDouble(precioReGRASS.getText().toString()));
                        cv.put(Utilidades.GRASS_direccionGRASS,direccReGRASS.getText().toString());
                        cv.put(Utilidades.GRASS_celularGRASS,Integer.parseInt(celReGRASS.getText().toString()));
                        cv.put(Utilidades.GRASS_tamañoGRASS,tamañReGRASS.getText().toString());
                        cv.put(Utilidades.GRASS_estadoGRASS,"Activo");


                        Long idRegistroGrass = db2.insert(Utilidades.TABLA_GRASS,Utilidades.GRASS_idgrass,cv);

                        Toast.makeText(getBaseContext(), "Registro de GRASS Exitoso | idgrass: "+Utilidades.GRASS_idgrass  , Toast.LENGTH_SHORT).show();
                        db2.close();
                        Intent IrHome = new Intent(RegisterGrass.this,Home.class);
                        IrHome.putExtra("Id",id);
                        startActivity(IrHome);
                        finish();
                    }

            }
        });
        btnVolverHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IrHome = new Intent(RegisterGrass.this,Home.class);
                IrHome.putExtra("Id",id);
                startActivity(IrHome);
                finish();
            }
        });







    }



}
