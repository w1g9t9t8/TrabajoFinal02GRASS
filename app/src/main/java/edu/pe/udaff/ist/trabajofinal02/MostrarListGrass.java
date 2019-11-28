package edu.pe.udaff.ist.trabajofinal02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.pe.udaff.ist.trabajofinal02.Entidades.Grass;
import edu.pe.udaff.ist.trabajofinal02.Entidades.Usuario;
import edu.pe.udaff.ist.trabajofinal02.Utilidades.Utilidades;


public class MostrarListGrass extends AppCompatActivity implements View.OnClickListener {
ListView listaMgrass ;
    Button btnsalir;
    int id=0,idgrass=0;
    ArrayList<String> listaInformacion;
    ArrayList<Grass> listaGrass;

    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_list_grass);


        Intent IrHome = this.getIntent();
        Bundle b = IrHome.getExtras();
        id=b.getInt("Id");

        btnsalir=(Button)findViewById(R.id.btn_MG_Salir);
        btnsalir.setOnClickListener(this);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);

        listaMgrass=(ListView)findViewById(R.id.lista_MG_GRASS);

        SQLiteDatabase db=conn.getReadableDatabase();
        Grass grass= null;

        listaGrass= new ArrayList<Grass>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_GRASS,null);
        while (cursor.moveToNext()){
            grass=new Grass();
            grass.setIdgrass(cursor.getInt(0));
            grass.setIdusuario(cursor.getInt(1));
            grass.setNomgrass(cursor.getString(2));
            grass.setDescripcion(cursor.getString(3));
            grass.setPrecio(cursor.getDouble(4));
            grass.setDireccion(cursor.getString(5));
            grass.setCelular(cursor.getInt(6));
            grass.setTamaño(cursor.getString(7));
            grass.setEstado(cursor.getString(8));
                listaGrass.add(grass);
        }
        listaInformacion=new ArrayList<String>();
        for (int i=0;i<listaGrass.size();i++){
            listaInformacion.add(" Nombre : "+listaGrass.get(i).getNomgrass()+" Dirección: "+listaGrass.get(i).getDireccion()+" Precio: "+listaGrass.get(i).getPrecio()+" \n Tamaño "+listaGrass.get(i).getTamaño()+" Celular: "+listaGrass.get(i).getCelular()+" Estado: "+listaGrass.get(i).getEstado());
        }
        ArrayAdapter adaptador= new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listaMgrass.setAdapter(adaptador);

        listaMgrass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                idgrass=listaGrass.get(pos).getIdgrass();
                Intent IrEditarGrass = new Intent(MostrarListGrass.this,ReservaGRASS.class);
                IrEditarGrass.putExtra("Id",id);
                IrEditarGrass.putExtra("IdGRASS",idgrass);
                startActivity(IrEditarGrass);
                finish();
                /*String informacion ="id: "+listaGrass.get(pos).getIdgrass()+"\n";
                informacion+="Nombre: "+listaGrass.get(pos).getNomgrass()+"\n";
                informacion+="Precio: "+listaGrass.get(pos).getPrecio()+"\n";
                informacion+="Tamaño: "+listaGrass.get(pos).getTamaño()+"\n";
                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();*/

            }
        });
        db.close();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_MG_Salir:
                Intent IrHome = new Intent(MostrarListGrass.this,Home.class);
                IrHome.putExtra("Id",id);
                startActivity(IrHome);
                break;
        }
    }
}
