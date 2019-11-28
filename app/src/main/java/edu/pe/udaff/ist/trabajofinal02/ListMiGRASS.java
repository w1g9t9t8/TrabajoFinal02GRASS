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
import edu.pe.udaff.ist.trabajofinal02.Utilidades.Utilidades;

public class ListMiGRASS extends AppCompatActivity implements View.OnClickListener {
    ListView listaMYgrass ;
    Button btnsalir;
    int id=0,idgrass=0;
    ArrayList<String> listaInformacion;
    ArrayList<Grass> listamyGrass;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mi_grass);

        Intent IrHome = this.getIntent();
        Bundle b = IrHome.getExtras();
        id=b.getInt("Id");

        btnsalir=(Button)findViewById(R.id.btn_MG_Salir);
        btnsalir.setOnClickListener(this);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);

        listaMYgrass=(ListView)findViewById(R.id.lista_MY_GRASS);

        SQLiteDatabase db=conn.getReadableDatabase();
        Grass grass= null;

        listamyGrass= new ArrayList<Grass>();
        String idnew=Integer.toString(id);
        String[] parametros={idnew};
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_GRASS +" WHERE "+Utilidades.GRASS_idusuario+"=?",parametros);
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
            listamyGrass.add(grass);
        }
        listaInformacion=new ArrayList<String>();
        for (int i=0;i<listamyGrass.size();i++){
            listaInformacion.add(" Nombre : "+listamyGrass.get(i).getNomgrass()+" Dirección: "+listamyGrass.get(i).getDireccion()+" Precio: "+listamyGrass.get(i).getPrecio()+" \n Tamaño "+listamyGrass.get(i).getTamaño()+" Celular: "+listamyGrass.get(i).getCelular()+" Estado: "+listamyGrass.get(i).getEstado());
        }

        ArrayAdapter adaptador= new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listaMYgrass.setAdapter(adaptador);

        listaMYgrass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                idgrass=listamyGrass.get(pos).getIdgrass();
                Intent IrEditarGrass = new Intent(ListMiGRASS.this,EditarGRASS.class);
                IrEditarGrass.putExtra("Id",id);
                IrEditarGrass.putExtra("IdGRASS",idgrass);
                startActivity(IrEditarGrass);
                finish();

            }
        });
        db.close();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_MG_Salir:
                Intent IrHome = new Intent(ListMiGRASS.this,Home.class);
                IrHome.putExtra("Id",id);
                startActivity(IrHome);
                break;
        }

    }
}
