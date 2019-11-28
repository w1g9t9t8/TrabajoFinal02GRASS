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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.pe.udaff.ist.trabajofinal02.Entidades.Grass;
import edu.pe.udaff.ist.trabajofinal02.Entidades.Reserva;
import edu.pe.udaff.ist.trabajofinal02.Utilidades.Utilidades;

public class MostrarListReservadosGrass extends AppCompatActivity implements View.OnClickListener {

    ListView LISTReservados ;
    Button btnsalir;
    int id=0,idgrass=0,idreserva=0;
    ArrayList<String> listaInformacion;
    ArrayList<Reserva> listaReserva;

    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_list_reservados_grass);

        Intent IrHome = this.getIntent();
        Bundle b = IrHome.getExtras();
        id=b.getInt("Id");

        btnsalir=(Button)findViewById(R.id.btn_LRG_Salir);
        btnsalir.setOnClickListener(this);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);

        LISTReservados=(ListView)findViewById(R.id.lista_LRG_GRASS);

        SQLiteDatabase db=conn.getReadableDatabase();
        Reserva reserva= null;

        listaReserva= new ArrayList<Reserva>();
        String idnew=Integer.toString(id);
        String[] parametros={idnew};

        //Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_RESERVA+" WHERE "+Utilidades.RESERVA_idUsuario+"=?",parametros);
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_RESERVA,null);
        while (cursor.moveToNext()){
            reserva=new Reserva();
            reserva.setIdreserva(cursor.getInt(0));
            reserva.setIdgrass(cursor.getInt(1));
            reserva.setIdusuario(cursor.getInt(2));
            reserva.setNomgrass(cursor.getString(3));
            reserva.setNomuser(cursor.getString(4));
            reserva.setFechaReserv(cursor.getString(5));
            reserva.setHoraInicio(cursor.getInt(6));
            reserva.setHoraFin(cursor.getInt(7));
            reserva.setCosto(cursor.getDouble(8));
            reserva.setHoras(cursor.getInt(9));
            reserva.setReservado(cursor.getInt(10));
            listaReserva.add(reserva);
        }
        listaInformacion=new ArrayList<String>();
        for (int i=0;i<listaReserva.size();i++){
            listaInformacion.add(" Nombre GRASS : "+listaReserva.get(i).getNomgrass()+" Nombre USER: "+listaReserva.get(i).getNomuser()+" Fecha: "+listaReserva.get(i).getFechaReserv()+" \n Hora Inicio "+listaReserva.get(i).getHoraInicio()+" Hora Fin: "+listaReserva.get(i).getHoraFin()+" Costo: "+listaReserva.get(i).getCosto());
            idreserva=listaReserva.get(i).getIdreserva();
        }
        ArrayAdapter adaptador= new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        LISTReservados.setAdapter(adaptador);

        LISTReservados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                idgrass=listaReserva.get(pos).getIdgrass();
                Intent IrCancelarRESERVA = new Intent(MostrarListReservadosGrass.this,CancelarReserva.class);
                IrCancelarRESERVA.putExtra("Id",id);
                IrCancelarRESERVA.putExtra("IdGRASS",idgrass);
                IrCancelarRESERVA.putExtra("IdReserva",idreserva);
                startActivity(IrCancelarRESERVA);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_LRG_Salir:
                Intent IrHome = new Intent(MostrarListReservadosGrass.this, Home.class);
                IrHome.putExtra("Id", id);
                startActivity(IrHome);
                break;
        }

    }
}
