package edu.pe.udaff.ist.trabajofinal02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import edu.pe.udaff.ist.trabajofinal02.Entidades.Grass;
import edu.pe.udaff.ist.trabajofinal02.Entidades.Reserva;
import edu.pe.udaff.ist.trabajofinal02.Utilidades.Utilidades;

public class ConsultarFecha extends AppCompatActivity implements View.OnClickListener{

    ListView listaConsulFECHA ;
    Button btnsalir;
    int id=0,idgrass=0;
    String fecha;
    ArrayList<String> listaInformacion;
    ArrayList<Reserva> listafecha;
    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_fecha);
        Intent IrHome = this.getIntent();
        Bundle b = IrHome.getExtras();
        id=b.getInt("Id");
        idgrass=b.getInt("IdGrass");
        fecha=b.getString("Fecha");

        btnsalir=(Button)findViewById(R.id.btn_CF_Salir);
        btnsalir.setOnClickListener(this);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);

        listaConsulFECHA=(ListView)findViewById(R.id.lista_CF_Consultarfecha);

        SQLiteDatabase db=conn.getReadableDatabase();
        Reserva reserva= null;

        listafecha= new ArrayList<Reserva>();
        String idnew=Integer.toString(idgrass);
        String[] parametros={idnew,fecha};
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_RESERVA +" WHERE "+Utilidades.RESERVA_idGrass+"=? "+" AND "+Utilidades.RESERVA_fecha+"=? ",parametros);
        while (cursor.moveToNext()){
            reserva=new Reserva();
            reserva.setFechaReserv(cursor.getString(5));
            reserva.setHoraInicio(cursor.getInt(6));
            reserva.setHoraFin(cursor.getInt(7));
            reserva.setNomgrass(cursor.getString(3));
            listafecha.add(reserva);
        }
        listaInformacion=new ArrayList<String>();
        for (int i=0;i<listafecha.size();i++){
            listaInformacion.add(" Fecha: "+listafecha.get(i).getFechaReserv()+" GRASS : "+listafecha.get(i).getNomgrass()+" \n Hora Inicio: "+listafecha.get(i).getHoraInicio()+" Hora Fin "+listafecha.get(i).getHoraFin());
        }
        ArrayAdapter adaptador= new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listaConsulFECHA.setAdapter(adaptador);

        /*listaConsulFECHA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                idgrass=listafecha.get(pos).getIdgrass();
                Intent IrEditarGrass = new Intent(ConsultarFecha.this,EditarGRASS.class);
                IrEditarGrass.putExtra("Id",id);
                IrEditarGrass.putExtra("IdGRASS",idgrass);
                startActivity(IrEditarGrass);
                finish();

            }
        });*/
        db.close();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_CF_Salir:
                Intent Volver = new Intent(ConsultarFecha.this,ReservaGRASS.class);
                Volver.putExtra("Id",id);
                Volver.putExtra("IdGRASS",idgrass);
                startActivity(Volver);
                finish();
                break;
        }
    }
}
