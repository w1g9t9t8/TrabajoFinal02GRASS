package edu.pe.udaff.ist.trabajofinal02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.pe.udaff.ist.trabajofinal02.Utilidades.Utilidades;


public class MostrarUser extends AppCompatActivity implements View.OnClickListener {
    TextView user,nom,ape,email,dni,fechaN,Cel,Direcc,diner;
    Button btnEditar, btnVolv;
int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_user);

        user=(TextView)findViewById(R.id.txt_MU_Usuario);
        nom=(TextView)findViewById(R.id.txt_MU_Nombres);
        ape=(TextView)findViewById(R.id.txt_MU_Apellidos);
        email=(TextView)findViewById(R.id.txt_MU_Correo);
        dni=(TextView)findViewById(R.id.txt_MU_DNI);
        fechaN=(TextView)findViewById(R.id.txt_MU_fechaNacimiento);
        Cel=(TextView)findViewById(R.id.txt_MU_Celular);
        Direcc=(TextView)findViewById(R.id.txt_MU_Direccion);
        diner=(TextView)findViewById(R.id.txt_MU_Dinero);


        btnEditar=(Button)findViewById(R.id.btn_MU_EditarDatos);
        btnVolv=(Button)findViewById(R.id.btn_MU_Volver);


        btnEditar.setOnClickListener(this);
        btnVolv.setOnClickListener(this);

        Intent IrHome = this.getIntent();
        Bundle b = IrHome.getExtras();
        id=b.getInt("Id");

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        String idnew=Integer.toString(id);
        String[] parametros={idnew};
        Cursor cursor=db.rawQuery("SELECT "+ Utilidades.USUARIO_usuario+","+Utilidades.USUARIO_correo+","+Utilidades.USUARIO_nombre+","+Utilidades.USUARIO_apellido+","+Utilidades.USUARIO_dni+","+Utilidades.USUARIO_fechanacimiento+","+Utilidades.USUARIO_celular+","+Utilidades.USUARIO_direccion+","+Utilidades.USUARIO_dinero+" FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.USUARIO_idusuario+"=?  ",parametros);
        cursor.moveToFirst();
        user.setHint(cursor.getString(0));
        email.setHint(cursor.getString(1));
        nom.setHint(cursor.getString(2));
        ape.setHint(cursor.getString(3));
        dni.setHint(cursor.getString(4));
        fechaN.setHint(cursor.getString(5));
        Cel.setHint(cursor.getString(6));
        Direcc.setHint(cursor.getString(7));
        diner.setHint( cursor.getString(8));
        db.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_MU_Volver:
                Intent IrHome = new Intent(MostrarUser.this,Home.class);
                IrHome.putExtra("Id",id);
                startActivity(IrHome);
                break;
            case R.id.btn_MU_EditarDatos:
                Intent IrEditUser = new Intent(MostrarUser.this,EditarUser.class);
                IrEditUser.putExtra("Id",id);
                startActivity(IrEditUser);
                finish();
                break;
        }
    }
}
