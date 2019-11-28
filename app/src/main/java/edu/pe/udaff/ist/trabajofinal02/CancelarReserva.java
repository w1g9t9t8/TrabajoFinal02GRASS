package edu.pe.udaff.ist.trabajofinal02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import edu.pe.udaff.ist.trabajofinal02.Utilidades.Utilidades;

public class CancelarReserva extends AppCompatActivity implements View.OnClickListener {
    int id=0,idgrass=0,idreserva=0,idusuGRASS=0;
    TextView usuario,nomgrass,preciograss,fechaReser,horaInicio, horaFin;
    EditText motivo;
    Button EliminarReser,Cancelar;

    double CostoTotalReserva=0,dineroUSUcomp=0,dineroNewUsu=0;
    double dineroAlquilador=0,dineroNewAlquilador=0;
    double devolDiner=0;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelar_reserva);

        usuario=(TextView) findViewById(R.id.txt_CR_NomUsuario);
        nomgrass=(TextView) findViewById(R.id.txt_CR_NomGRASS);
        preciograss=(TextView) findViewById(R.id.txt_CR_PrecioGrass);

        fechaReser=(TextView)findViewById(R.id.txt_CR_fechaRESERVA);
        horaInicio=(TextView)findViewById(R.id.txt_CR_HoraInicio);
        horaFin=(TextView)findViewById(R.id.txt_CR_horaFIn);
        motivo=(EditText) findViewById(R.id.txt_CR_Motivo);


        EliminarReser=(Button)findViewById(R.id.btn_CR_ElimnarReser);
        Cancelar=(Button)findViewById(R.id.btn_CR_Cancelar);


        EliminarReser.setOnClickListener(this);
        Cancelar.setOnClickListener(this);


        Intent IrHome = this.getIntent();
        Bundle b= IrHome.getExtras();
        id=b.getInt("Id");
        idgrass=b.getInt("IdGRASS");
        idreserva=b.getInt("IdReserva");

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        String idnew=Integer.toString(id);
        String[] parametros={idnew};
        Cursor cursor=db.rawQuery("SELECT "+ Utilidades.USUARIO_usuario+","+Utilidades.USUARIO_dinero+" FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.USUARIO_idusuario+"=?  ",parametros);
        cursor.moveToFirst();
        usuario.setText(cursor.getString(0));
        dineroUSUcomp=Double.parseDouble(cursor.getString(1));
        db.close();


        SQLiteDatabase db2=conn.getReadableDatabase();
        String idparamgrass=Integer.toString(idgrass);
        String[] parametrosgrass={idparamgrass};
        Cursor cursor1=db2.rawQuery("SELECT "+ Utilidades.GRASS_nombreGrass+","+Utilidades.GRASS_precioGRASS+","+Utilidades.GRASS_idusuario+" FROM "+Utilidades.TABLA_GRASS+" WHERE "+Utilidades.GRASS_idgrass+"=?  ",parametrosgrass);
        cursor1.moveToFirst();
        nomgrass.setText(cursor1.getString(0));
        preciograss.setText(cursor1.getString(1));
        idusuGRASS=Integer.parseInt(cursor1.getString(2));
        db2.close();


        SQLiteDatabase db6=conn.getReadableDatabase();
        String idparamAlq=Integer.toString(idusuGRASS);
        String[] parametrosAlqu={idparamAlq};
        Cursor cursor6=db6.rawQuery("SELECT "+Utilidades.USUARIO_dinero+" FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.USUARIO_idusuario+"=?  ",parametrosAlqu);
        cursor6.moveToFirst();
        dineroAlquilador=Double.parseDouble(cursor6.getString(0));
        db6.close();

        SQLiteDatabase db10=conn.getReadableDatabase();
        String idparamReser=Integer.toString(idreserva);
        String[] parametrosReserva={idparamReser};
        Cursor cursor10=db10.rawQuery("SELECT "+Utilidades.RESERVA_fecha+","+Utilidades.RESERVA_horaInicio+","+Utilidades.RESERVA_horaFin+","+Utilidades.RESERVA_Costo+" FROM "+Utilidades.TABLA_RESERVA+" WHERE "+Utilidades.RESERVA_idreserva+"=?  ",parametrosReserva);
        cursor10.moveToFirst();
        fechaReser.setText(cursor10.getString(0));
        horaInicio.setText(cursor10.getString(1));
        horaFin.setText(cursor10.getString(2));
        CostoTotalReserva=cursor10.getInt(3);
        db10.close();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_CR_ElimnarReser:
                String mo;
                mo = motivo.getText().toString();
                if ( mo.equals("")) {
                    Toast.makeText(this, "RELLENE el motivo por favor", Toast.LENGTH_SHORT).show();
                }else {
                    devolDiner=(CostoTotalReserva)/2;
                    dineroNewUsu = dineroUSUcomp + devolDiner;
                    dineroNewAlquilador = dineroAlquilador - devolDiner;
                    AlertDialog.Builder b = new AlertDialog.Builder(this);
                    b.setMessage("¿Estas seguro de eliminar esta RESERVA?");
                    b.setCancelable(false);
                    b.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getBaseContext(),"bdTBgrass",null,1);
                                    SQLiteDatabase db = conn.getWritableDatabase();
                                    ContentValues cv = new ContentValues();
                                    cv.put(Utilidades.CANCELADOS_idreserva, idreserva);
                                    cv.put(Utilidades.CANCELADOS_motivo, motivo.getText().toString());

                                     db.insert(Utilidades.TABLA_CANCELADOS, Utilidades.CANCELADOS_idcancelados, cv);


                                    db.close();

                            ConexionSQLiteHelper connelim = new ConexionSQLiteHelper(getBaseContext(),"bdTBgrass",null,1);
                            SQLiteDatabase dbel = connelim.getReadableDatabase();
                            String idelim = Integer.toString(idreserva);
                            String[] parmelim = {idelim};
                            dbel.delete(Utilidades.TABLA_RESERVA,Utilidades.RESERVA_idreserva+"=?",parmelim);
                            Toast.makeText(getApplicationContext(),"Se elimino la reserva correctamente",Toast.LENGTH_SHORT).show();
                            dbel.close();


                                    //dineero usuario dueño del grass
                                    ConexionSQLiteHelper conn4 = new ConexionSQLiteHelper(getApplicationContext(), "bdTBgrass", null, 1);

                                    String idusuarioCupdate = Integer.toString(idusuGRASS);
                                    String[] updatedineroC = {idusuarioCupdate};
                                    SQLiteDatabase db5 = conn4.getWritableDatabase();
                                    ContentValues cv5 = new ContentValues();
                                    cv5.put(Utilidades.USUARIO_dinero, dineroNewAlquilador);
                                    db5.update(Utilidades.TABLA_USUARIO, cv5, Utilidades.USUARIO_idusuario + "=?", updatedineroC);
                                    db5.close();


                            Toast.makeText(getBaseContext(), "Se le devolvio $" + devolDiner+" a su cuenta.", Toast.LENGTH_LONG).show();
                                    ConexionSQLiteHelper conn5 = new ConexionSQLiteHelper(getApplicationContext(), "bdTBgrass", null, 1);



                                    //dinero comprrador
                                    String idusuarioupdate = Integer.toString(id);
                                    String[] updatedinero = {idusuarioupdate};
                                    SQLiteDatabase db4 = conn5.getWritableDatabase();
                                    ContentValues cv4 = new ContentValues();
                                    cv4.put(Utilidades.USUARIO_dinero, dineroNewUsu);
                                    db4.update(Utilidades.TABLA_USUARIO, cv4, Utilidades.USUARIO_idusuario + "=?", updatedinero);
                                    db4.close();


                                    Intent IrHome = new Intent(CancelarReserva.this, Home.class);
                                    IrHome.putExtra("Id", id);
                                    startActivity(IrHome);
                                    finish();


                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    b.show();
                }
            break;
            case R.id.btn_CR_Cancelar:
                Intent irMostrarListReser = new Intent(CancelarReserva.this,MostrarListReservadosGrass.class);
                irMostrarListReser.putExtra("Id",id);
                irMostrarListReser.putExtra("IdGrass",idgrass);
                startActivity(irMostrarListReser);
                finish();
            break;
        }
    }
}

