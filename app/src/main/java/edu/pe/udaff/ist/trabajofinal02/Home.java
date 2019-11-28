package edu.pe.udaff.ist.trabajofinal02;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import edu.pe.udaff.ist.trabajofinal02.Entidades.Usuario;
import edu.pe.udaff.ist.trabajofinal02.Utilidades.Utilidades;


public class Home extends AppCompatActivity implements View.OnClickListener {
Button btnEditar,btnEliminar, btnMostrar, btnSalir,btnGrupoUsuario,btnMostrarAddDiner,btnIngresarDINERO;
    Button btnMostrarListGrass, btnRegistrarGrass,btnMostrarListGrassReservados, btnMostrarMisGrass;
    TextView nombre,dinero;
    EditText txtAñadirDinero;
int id=0;
    ConexionSQLiteHelper conn;
View linea,linea2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        nombre=(TextView) findViewById(R.id.txt_H_bienvenida);
        dinero=(TextView) findViewById(R.id.txt_H_MostrarDinero);
        txtAñadirDinero=(EditText)findViewById(R.id.txt_H_IngresarDinero);

        btnEditar=(Button) findViewById(R.id.btn_H_Editar);
        btnEliminar=(Button) findViewById(R.id.btn_H_Eliminar);
        btnMostrar=(Button) findViewById(R.id.btn_H_Mostrar);
        btnSalir=(Button) findViewById(R.id.btn_H_Salir);

        btnGrupoUsuario=(Button)findViewById(R.id.btn_H_GrupoUsuario);
        btnIngresarDINERO=(Button)findViewById(R.id.btn_H_IngresarDinero);

        btnMostrarListGrass=(Button)findViewById(R.id.btn_H_MostrarListGrass);
        btnRegistrarGrass=(Button)findViewById(R.id.btn_H_AgregarGRASS);
        btnMostrarListGrassReservados=(Button)findViewById(R.id.btn_H_ListGrassReservado);
        btnMostrarMisGrass=(Button)findViewById(R.id.btn_H_ListMissGRASS);
        btnMostrarAddDiner=(Button)findViewById(R.id.btn_H_MostrarVentanAgregarD);

        btnMostrarListGrass.setOnClickListener(this);
        btnRegistrarGrass.setOnClickListener(this);
        btnMostrarListGrassReservados.setOnClickListener(this);
        btnMostrarMisGrass.setOnClickListener(this);

        btnEditar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnMostrar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
        btnGrupoUsuario.setOnClickListener(this);
        btnMostrarAddDiner.setOnClickListener(this);
        btnIngresarDINERO.setOnClickListener(this);

        linea=(View)findViewById(R.id.view_H_Finish);
        linea2=(View)findViewById(R.id.view_H_Finish2);

        Intent IrHome = this.getIntent();
        Bundle b = IrHome.getExtras();
        id=b.getInt("Id");

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        String idnew=Integer.toString(id);
        String[] parametros={idnew};
        Cursor cursor=db.rawQuery("SELECT "+Utilidades.USUARIO_dinero+","+Utilidades.USUARIO_nombre+","+Utilidades.USUARIO_apellido+" FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.USUARIO_idusuario+"=?  ",parametros);
        cursor.moveToFirst();
        Double bddinero =cursor.getDouble(0);
        String bdNombre=cursor.getString(1);
        String bdApellido=cursor.getString(2);
        db.close();



        nombre.setText("Bienvenido "+bdNombre+" "+bdApellido);
        dinero.setText("$ "+bddinero);



}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_H_Editar:
                Intent IrEditUser = new Intent(Home.this,EditarUser.class);
                IrEditUser.putExtra("Id",id);
                startActivity(IrEditUser);
                finish();

                break;
            case R.id.btn_H_Eliminar:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("¿Estas seguro de eliminar tu cuenta?");
                b.setCancelable(false);
                b.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getBaseContext(),"bdTBgrass",null,1);
                        SQLiteDatabase db = conn.getReadableDatabase();
                        String idnew = Integer.toString(id);
                        String[] parametros = {idnew};
                        db.delete(Utilidades.TABLA_USUARIO,Utilidades.USUARIO_idusuario+"=?",parametros);
                        Toast.makeText(getApplicationContext(),"Se elimino la cuenta correctamente",Toast.LENGTH_SHORT).show();
                        db.close();
                        Intent IrLogin = new Intent(Home.this, MainActivity.class);
                        startActivity(IrLogin);
                        finish();


                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                b.show();
                break;
            case R.id.btn_H_Mostrar:
                Intent IrMostrarUser = new Intent(Home.this,MostrarUser.class);
                IrMostrarUser.putExtra("Id",id);
                startActivity(IrMostrarUser);
                finish();
                break;
            case R.id.btn_H_Salir:
                Intent IrLogin = new Intent(Home.this,MainActivity.class);
                startActivity(IrLogin);
                finish();
                break;


            case R.id.btn_H_AgregarGRASS:
                Intent IrAgregarGRASSHOME = new Intent(Home.this,RegisterGrass.class);
                IrAgregarGRASSHOME.putExtra("Id",id);
                startActivity(IrAgregarGRASSHOME);
                finish();
                break;
            case R.id.btn_H_MostrarListGrass:
                Intent irMostrarGRASSHOME = new Intent(Home.this,MostrarListGrass.class);
                irMostrarGRASSHOME.putExtra("Id",id);
                startActivity(irMostrarGRASSHOME);
                finish();
                break;
            case R.id.btn_H_ListMissGRASS:
                Intent irListMissGRASS = new Intent(Home.this,ListMiGRASS.class);
                irListMissGRASS.putExtra("Id",id);
                startActivity(irListMissGRASS);
                finish();
                break;
            case R.id.btn_H_ListGrassReservado:
                Intent irLisGrassReservado = new Intent(Home.this,MostrarListReservadosGrass.class);
                irLisGrassReservado.putExtra("Id",id);
                startActivity(irLisGrassReservado);
                finish();
                break;


            case R.id.btn_H_GrupoUsuario:
                if (btnEditar.getVisibility()==v.GONE && btnEliminar.getVisibility()==v.GONE && btnMostrar.getVisibility()==v.GONE && btnSalir.getVisibility()==v.GONE){
                    btnEditar.setVisibility(v.VISIBLE);
                    btnEliminar.setVisibility(v.VISIBLE);
                    btnMostrar.setVisibility(v.VISIBLE);
                    btnSalir.setVisibility(v.VISIBLE);
                    linea.setVisibility(v.VISIBLE);
                }else{
                    btnEditar.setVisibility(v.GONE);
                    btnEliminar.setVisibility(v.GONE);
                    btnMostrar.setVisibility(v.GONE);
                    btnSalir.setVisibility(v.GONE);
                    linea.setVisibility(v.GONE);
                }
                break;
            case R.id.btn_H_MostrarVentanAgregarD:
                if (txtAñadirDinero.getVisibility()==v.GONE && btnIngresarDINERO.getVisibility()==v.GONE) {
                    txtAñadirDinero.setVisibility(v.VISIBLE);
                    btnIngresarDINERO.setVisibility(v.VISIBLE);
                }else{
                    txtAñadirDinero.setVisibility(v.GONE);
                    btnIngresarDINERO.setVisibility(v.GONE);
                }
                break;
            case R.id.btn_H_IngresarDinero: {
                String comptext;
                comptext = txtAñadirDinero.getText().toString();
                if (comptext.equals("")) {
                    Toast.makeText(this, "Error Ingrese algun dato", Toast.LENGTH_SHORT).show();
                } else {
                    ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bdTBgrass", null, 1);
                    SQLiteDatabase db = conn.getReadableDatabase();
                    String idnew = Integer.toString(id);
                    String[] parametros = {idnew};
                    ContentValues cv = new ContentValues();
                    Cursor cursor=db.rawQuery("SELECT "+ Utilidades.USUARIO_dinero+" FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.USUARIO_idusuario+"=?  ",parametros);
                    cursor.moveToFirst();
                    Double dan,dag,dnu;
                    dan=Double.parseDouble(cursor.getString(0));
                    dag=Double.parseDouble(txtAñadirDinero.getText().toString());
                    dnu=dan+dag;
                    db.close();
                    ConexionSQLiteHelper conn2 = new ConexionSQLiteHelper(this, "bdTBgrass", null, 1);
                    SQLiteDatabase db2 = conn2.getReadableDatabase();
                    ContentValues cvup = new ContentValues();
                    cvup.put(Utilidades.USUARIO_dinero, dnu);
                    db2.update(Utilidades.TABLA_USUARIO,cvup,Utilidades.USUARIO_idusuario+"=?",parametros);
                    Toast.makeText(getApplicationContext(),"Se Actualizo correctamente sus datos!",Toast.LENGTH_SHORT).show();
                    db2.close();
                    Toast.makeText(getBaseContext(), "Dinero Añadido Correctamete", Toast.LENGTH_SHORT).show();
                    txtAñadirDinero.setVisibility(v.GONE);
                    btnIngresarDINERO.setVisibility(v.GONE);
                    txtAñadirDinero.setText("");
                    String dnenviar=Double.toString(dnu);
                    dinero.setText("$"+dnenviar);
                }

            }

                break;

        }
    }
    public void ms1(int msnum){
        switch (msnum){
            case 1:
                Toast.makeText(this,"Se elimino Correctamente",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"ERROR no se elimino cuenta",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
