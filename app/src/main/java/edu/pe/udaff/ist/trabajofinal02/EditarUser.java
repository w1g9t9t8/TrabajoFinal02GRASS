package edu.pe.udaff.ist.trabajofinal02;

import android.app.DatePickerDialog;
import android.content.ContentValues;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import edu.pe.udaff.ist.trabajofinal02.Utilidades.Utilidades;


public class EditarUser extends AppCompatActivity implements View.OnClickListener{
    EditText EDIuser,EDIpass,EDIpassconfirm,EDInom,EDIape,EDIemail,EDIdni,EDIfechaN,EDICel,EDIDirecc;
    Button btnGuardarCambio, btnCancel;

    int id=0;

    Intent x;

    private static final String TAG ="EditarUser";
    private EditText mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_user);

        EDIuser=(EditText)findViewById(R.id.txt_E_Usuario);
        EDIpass=(EditText)findViewById(R.id.txt_E_Contraseña);
        EDIpassconfirm=(EditText)findViewById(R.id.txt_E_RepContraseña);
        EDInom=(EditText)findViewById(R.id.txt_E_Nombres);
        EDIape=(EditText)findViewById(R.id.txt_E_Apellidos);
        EDIemail=(EditText)findViewById(R.id.txt_E_Correo);
        EDIdni=(EditText)findViewById(R.id.txt_E_DNI);
        EDIfechaN=(EditText)findViewById(R.id.txt_E_fechaNacimiento);
        EDICel=(EditText)findViewById(R.id.txt_E_Celular);
        EDIDirecc=(EditText)findViewById(R.id.txt_E_Direccion);
        btnGuardarCambio=(Button)findViewById(R.id.btn_E_GuardarCambios);
        btnCancel=(Button)findViewById(R.id.btn_E_Cancelar);

        btnGuardarCambio.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        Bundle b= getIntent().getExtras();
        id=b.getInt("Id");

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);
        SQLiteDatabase db=conn.getReadableDatabase();
        String idnew=Integer.toString(id);
        String[] parametros={idnew};
        Cursor cursor=db.rawQuery("SELECT "+ Utilidades.USUARIO_usuario+","+Utilidades.USUARIO_contraseña+","+Utilidades.USUARIO_correo+","+Utilidades.USUARIO_nombre+","+Utilidades.USUARIO_apellido+","+Utilidades.USUARIO_dni+","+Utilidades.USUARIO_fechanacimiento+","+Utilidades.USUARIO_celular+","+Utilidades.USUARIO_direccion+" FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.USUARIO_idusuario+"=?  ",parametros);
        cursor.moveToFirst();
        EDIuser.setText(cursor.getString(0));
        EDIpass.setText(cursor.getString(1));
        EDIpassconfirm.setText(cursor.getString(1));
        EDIemail.setText(cursor.getString(2));
        EDInom.setText(cursor.getString(3));
        EDIape.setText(cursor.getString(4));
        EDIdni.setText(cursor.getString(5));
        EDIfechaN.setText(cursor.getString(6));
        EDICel.setText(cursor.getString(7));
        EDIDirecc.setText(cursor.getString(8));
        db.close();


        btnGuardarCambio.setOnClickListener(this);
        btnCancel.setOnClickListener(this);


        mDisplayDate= (EditText) findViewById((R.id.txt_E_fechaNacimiento));
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditarUser.this,
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_E_GuardarCambios: {
                ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bdTBgrass", null, 1);
                SQLiteDatabase db = conn.getReadableDatabase();
                String idnew = Integer.toString(id);
                String[] parametros = {idnew};
                ContentValues cv = new ContentValues();


                    cv.put(Utilidades.USUARIO_usuario, EDIuser.getText().toString());
                    cv.put(Utilidades.USUARIO_contraseña, EDIpass.getText().toString());
                    cv.put(Utilidades.USUARIO_correo, EDIemail.getText().toString());
                    cv.put(Utilidades.USUARIO_nombre, EDInom.getText().toString());
                    cv.put(Utilidades.USUARIO_apellido, EDIape.getText().toString());
                    cv.put(Utilidades.USUARIO_dni, EDIdni.getText().toString());
                    cv.put(Utilidades.USUARIO_fechanacimiento, EDIfechaN.getText().toString());
                    cv.put(Utilidades.USUARIO_celular, EDICel.getText().toString());
                    cv.put(Utilidades.USUARIO_direccion, EDIDirecc.getText().toString());

                    if (EDIpass.getText().toString().equals(EDIpassconfirm.getText().toString())) {
                        db.update(Utilidades.TABLA_USUARIO,cv,Utilidades.USUARIO_idusuario+"=?",parametros);
                        Toast.makeText(getApplicationContext(),"Se Actualizo correctamente sus datos!",Toast.LENGTH_SHORT).show();
                        db.close();
                        Intent IrHome = new Intent(EditarUser.this,Home.class);
                        IrHome.putExtra("Id",id);
                        startActivity(IrHome);
                        finish();
                    } else {
                        Toast.makeText(this, "Las Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
            }

            break;
            case R.id.btn_E_Cancelar:
                Intent IrHome = new Intent(EditarUser.this,Home.class);
                IrHome.putExtra("Id",id);
                startActivity(IrHome);
                finish();
                break;
        }
    }
}
