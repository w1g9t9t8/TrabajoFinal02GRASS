package edu.pe.udaff.ist.trabajofinal02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import edu.pe.udaff.ist.trabajofinal02.Entidades.Reserva;
import edu.pe.udaff.ist.trabajofinal02.Utilidades.Utilidades;

public class ReservaGRASS extends AppCompatActivity implements View.OnClickListener {

    int id=0,idgrass=0,idusuGRASS=0;
    int horaFIN=0,horaintINICIO=0,horasint=0;
    TextView usuario,nomgrass,preciograss;
    EditText fechaReser,horaInicio, horas,comprobar1o0;
    Button Consultar,Reservar,Cancelar;
    int acum1 = 0,acum2=0,acum3=0,acum4=0,acum5=0,acum6=0,acumTotal=0;



    String fechaENVIAR;


    double CostoTotalReserva=0,HorasReservaDouble=0,PrecioGrass=0,dineroUSUcomp=0,dineroNewUsu=0;
    double dineroAlquilador=0,dineroNewAlquilador=0;
    ConexionSQLiteHelper conn;

    private static final String TAG ="Register";

    private EditText mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_grass);

        usuario=(TextView) findViewById(R.id.txt_RESG_NomUsuario);
        nomgrass=(TextView) findViewById(R.id.txt_RESG_NomGRASS);
        preciograss=(TextView) findViewById(R.id.txt_RESG_PrecioGrass);

        fechaReser=(EditText)findViewById(R.id.txt_RESG_fechaRESERVA);
        horaInicio=(EditText)findViewById(R.id.txt_RESG_HoraInicio);
        horas=(EditText)findViewById(R.id.txt_RESG_HorasReservadas);
        comprobar1o0=(EditText)findViewById(R.id.txt_RESG_Comprobar);


        Consultar=(Button)findViewById(R.id.btn_RESG_ConsultarFecha);
        Reservar=(Button)findViewById(R.id.btn_RESG_ReservarGrass);
        Cancelar=(Button)findViewById(R.id.btn_RESG_CancelarReserva);


        Consultar.setOnClickListener(this);
        Reservar.setOnClickListener(this);
        Cancelar.setOnClickListener(this);


        Intent IrHome = this.getIntent();
        Bundle b= IrHome.getExtras();
        id=b.getInt("Id");
        idgrass=b.getInt("IdGRASS");


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

        mDisplayDate= (EditText) findViewById((R.id.txt_RESG_fechaRESERVA));
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ReservaGRASS.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                Log.d(TAG,"onDateSet: date:"+day+"/"+month+"/"+year);
                String date =  day+ "/" + month +"/"+year;
                mDisplayDate.setText(date);
            }
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_RESG_ReservarGrass: {

                String fe, hi, h;
                fe = fechaReser.getText().toString();
                hi = horaInicio.getText().toString();
                h = horas.getText().toString();
                if (fe.equals("") || hi.equals("") || h.equals("")) {
                    Toast.makeText(this, "Error Campos Vacios", Toast.LENGTH_SHORT).show();
                } else {
                    horaintINICIO = Integer.parseInt(horaInicio.getText().toString());
                    horasint = Integer.parseInt(horas.getText().toString());
                    PrecioGrass = Double.parseDouble(preciograss.getText().toString());
                    HorasReservaDouble = Double.parseDouble(horas.getText().toString());
                    horaFIN = horaintINICIO + horasint;
                    CostoTotalReserva = PrecioGrass * HorasReservaDouble;
                    dineroNewUsu = dineroUSUcomp - CostoTotalReserva;
                    dineroNewAlquilador = dineroAlquilador + CostoTotalReserva;
                    if(horaintINICIO>=6 && horaintINICIO<=22 && horasint<=5 && horasint>0) {
                        if (CostoTotalReserva < dineroUSUcomp)  {

                            AlertDialog.Builder b = new AlertDialog.Builder(this);
                            b.setMessage("¿Estas seguro de reservar este GRASS?" + "\n Precio Total :" + CostoTotalReserva + " \n Hora Inicio = " + horaintINICIO + " || Hora Fin = " + horaFIN + "\nFecha = " + fechaReser.getText().toString());
                            b.setCancelable(false);
                            b.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {


                                    //Toast.makeText(getApplicationContext(), " Hola" + idgrass + " " + id + " " + nomgrass.getText().toString() + "\n " + usuario.getText().toString() + " " + fechaReser.getText().toString() + "\n " + horaInicio.getText().toString() + " " + horaFIN + " " + CostoTotalReserva + "\n " + horasint, Toast.LENGTH_LONG).show();



                                        ConexionSQLiteHelper connreserva = new ConexionSQLiteHelper(getApplicationContext(), "bdTBgrass", null, 1);
                                        SQLiteDatabase dbreserva = connreserva.getWritableDatabase();
                                        ContentValues cvreserva = new ContentValues();
                                        cvreserva.put(Utilidades.RESERVA_idGrass, idgrass);
                                        cvreserva.put(Utilidades.RESERVA_idUsuario, id);
                                        cvreserva.put(Utilidades.RESERVA_nomGrass, nomgrass.getText().toString());
                                        cvreserva.put(Utilidades.RESERVA_nomUsuario, usuario.getText().toString());
                                        cvreserva.put(Utilidades.RESERVA_fecha, fechaReser.getText().toString());
                                        cvreserva.put(Utilidades.RESERVA_horaInicio, Integer.parseInt(horaInicio.getText().toString()));
                                        cvreserva.put(Utilidades.RESERVA_horaFin, horaFIN);
                                        cvreserva.put(Utilidades.RESERVA_Costo, CostoTotalReserva);
                                        cvreserva.put(Utilidades.RESERVA_horasReservadas, horasint);
                                        cvreserva.put(Utilidades.RESERVA_reservadosss, 1);

                                        Long registro = dbreserva.insert(Utilidades.TABLA_RESERVA, Utilidades.RESERVA_idreserva, cvreserva);

                                        dbreserva.close();

                                        //dineero usuario dueño del grass
                                        ConexionSQLiteHelper conn4 = new ConexionSQLiteHelper(getApplicationContext(), "bdTBgrass", null, 1);

                                        String idusuarioCupdate = Integer.toString(idusuGRASS);
                                        String[] updatedineroC = {idusuarioCupdate};
                                        SQLiteDatabase db5 = conn4.getWritableDatabase();
                                        ContentValues cv5 = new ContentValues();
                                        cv5.put(Utilidades.USUARIO_dinero, dineroNewAlquilador);
                                        db5.update(Utilidades.TABLA_USUARIO, cv5, Utilidades.USUARIO_idusuario + "=?", updatedineroC);
                                        db5.close();




                                        ConexionSQLiteHelper conn5 = new ConexionSQLiteHelper(getApplicationContext(), "bdTBgrass", null, 1);



                                        //dinero comprrador
                                        String idusuarioupdate = Integer.toString(id);
                                        String[] updatedinero = {idusuarioupdate};
                                        SQLiteDatabase db4 = conn5.getWritableDatabase();
                                        ContentValues cv4 = new ContentValues();
                                        cv4.put(Utilidades.USUARIO_dinero, dineroNewUsu);
                                        db4.update(Utilidades.TABLA_USUARIO, cv4, Utilidades.USUARIO_idusuario + "=?", updatedinero);
                                        db4.close();

                                    Toast.makeText(getApplicationContext(), "Grass reservado correctamente" , Toast.LENGTH_SHORT).show();
                                        Intent IrHome = new Intent(ReservaGRASS.this, Home.class);
                                        IrHome.putExtra("Id", id);
                                        startActivity(IrHome);
                                        finish();




                                }
                            });
                            b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            b.show();

                        }else {
                            Toast.makeText(getApplicationContext(), "No tiene dinero suficiente", Toast.LENGTH_SHORT);
                        }
                    }else{
                        Toast.makeText(this,"Ingrese los datos segun las indicaciones...",Toast.LENGTH_SHORT).show();
                        horas.setText("");
                        horaInicio.setText("");
                    }

                }
            }
                break;
            case R.id.btn_RESG_ConsultarFecha:
                if (fechaReser.getText().toString().equals("")){
                    Toast.makeText(this,"Ingrese la fecha a consultar.",Toast.LENGTH_SHORT).show();
                }else {
                    Intent irConsulFecha = new Intent(ReservaGRASS.this, ConsultarFecha.class);
                    irConsulFecha.putExtra("Id", id);
                    irConsulFecha.putExtra("IdGrass", idgrass);
                    irConsulFecha.putExtra("Fecha", fechaReser.getText().toString());
                    startActivity(irConsulFecha);
                    finish();
                }
                break;
            case R.id.btn_RESG_CancelarReserva:
                Intent irMostrarGRASSHOME = new Intent(ReservaGRASS.this,MostrarListGrass.class);
                irMostrarGRASSHOME.putExtra("Id",id);
                irMostrarGRASSHOME.putExtra("IdGrass",idgrass);
                startActivity(irMostrarGRASSHOME);
                finish();
                break;
        }
    }


}

