package edu.pe.udaff.ist.trabajofinal02;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
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


public class Register extends AppCompatActivity implements View.OnClickListener {
EditText user,pass,passconfirm,nom,ape,email,dni,fechaN,Cel,Direcc,diner;
Button btnRegis, btnVolv;
    private static final String TAG ="Register";

    private EditText mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user=(EditText)findViewById(R.id.txt_R_Usuario);
        pass=(EditText)findViewById(R.id.txt_R_Contraseña);
        passconfirm=(EditText)findViewById(R.id.txt_R_RepContraseña);
        nom=(EditText)findViewById(R.id.txt_R_Nombres);
        ape=(EditText)findViewById(R.id.txt_R_Apellidos);
        email=(EditText)findViewById(R.id.txt_R_Correo);
        dni=(EditText)findViewById(R.id.txt_R_DNI);
        fechaN=(EditText)findViewById(R.id.txt_R_fechaNacimiento);
        Cel=(EditText)findViewById(R.id.txt_R_Celular);
        Direcc=(EditText)findViewById(R.id.txt_R_Direccion);
        diner=(EditText)findViewById(R.id.txt_R_Dinero);





        btnRegis=(Button)findViewById(R.id.btn_R_Registrarse);
        btnVolv=(Button)findViewById(R.id.btn_R_Volver);


        btnRegis.setOnClickListener(this);
        btnVolv.setOnClickListener(this);
        mDisplayDate= (EditText) findViewById((R.id.txt_R_fechaNacimiento));
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Register.this,
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
            case R.id.btn_R_Registrarse: {



                String u1,c1,rc1,co1,no1,ap1,dn1,fn1,cl1,dc1;
                u1=user.getText().toString();
                c1=pass.getText().toString();
                rc1=passconfirm.getText().toString();
                co1=email.getText().toString();
                no1=nom.getText().toString();
                ap1=ape.getText().toString();
                dn1=dni.getText().toString();
                fn1=fechaN.getText().toString();
                cl1=Cel.getText().toString();
                dc1=Direcc.getText().toString();
                if (u1.equals("") || c1.equals("") || rc1.equals("") ||co1.equals("") || no1.equals("") || ap1.equals("") || dn1.equals("") || fn1.equals("") ||cl1.equals("") ||dc1.equals("") ){
                    Toast.makeText(this, "Error Campos Vacios", Toast.LENGTH_SHORT).show();
                }else
                {
                    String comprobar = passconfirm.getText().toString();
                    String contrase = pass.getText().toString();



                        if (contrase.equals(comprobar)) {
                            Toast.makeText(getBaseContext(), "Las Contraseñas Coinciden", Toast.LENGTH_SHORT).show();
                            //RegistrarGrassContent();
                            RegistrarUsuarioConsultas();


                            Intent RLogin = new Intent(Register.this, MainActivity.class);
                            startActivity(RLogin);
                            finish();

                        } else {
                            Toast.makeText(getBaseContext(), "Las Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        }



                }
            }

                break;
            case R.id.btn_R_Volver:
                Intent RVolver = new Intent(Register.this,MainActivity.class);
                startActivity(RVolver);
                finish();
                break;
        }
    }

    private void RegistrarUsuarioConsultas() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);

        //indica que abrimos la bd para editar
        SQLiteDatabase db = conn.getWritableDatabase();

        //insert into usuario() values()
        String insert="INSERT INTO "+Utilidades.TABLA_USUARIO+" (" +Utilidades.USUARIO_usuario+","+Utilidades.USUARIO_contraseña+","+Utilidades.USUARIO_correo+","+Utilidades.USUARIO_nombre+","+
                Utilidades.USUARIO_apellido+","+Utilidades.USUARIO_dni+","+Utilidades.USUARIO_fechanacimiento+","+Utilidades.USUARIO_celular+","+Utilidades.USUARIO_direccion+","+Utilidades.USUARIO_dinero+")" +
                "VALUES ('"+user.getText().toString()+"','"+pass.getText().toString()+"','"+email.getText().toString()+"','"+nom.getText().toString()+"','"+ape.getText().toString()+"',"+Integer.parseInt(dni.getText().toString())+",'"+
                fechaN.getText().toString()+"',"+Integer.parseInt(Cel.getText().toString())+",'"+Direcc.getText().toString()+"',"+Double.parseDouble(diner.getText().toString())+")";

        db.execSQL(insert);
        Toast.makeText(getBaseContext(), "Registro Exitoso!! "  , Toast.LENGTH_SHORT).show();
        db.close();

    }

    private void RegistrarUsuarioContent() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bdTBgrass",null,1);

        //indica que abrimos la bd para editar
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Utilidades.USUARIO_usuario,user.getText().toString());
        cv.put(Utilidades.USUARIO_contraseña,pass.getText().toString());
        cv.put(Utilidades.USUARIO_correo,email.getText().toString());
        cv.put(Utilidades.USUARIO_nombre,nom.getText().toString());
        cv.put(Utilidades.USUARIO_apellido,ape.getText().toString());
        cv.put(Utilidades.USUARIO_dni,Integer.parseInt(dni.getText().toString()));
        cv.put(Utilidades.USUARIO_fechanacimiento,fechaN.getText().toString());
        cv.put(Utilidades.USUARIO_celular,Integer.parseInt(Cel.getText().toString()));
        cv.put(Utilidades.USUARIO_direccion,Direcc.getText().toString());
        cv.put(Utilidades.USUARIO_dinero,Double.parseDouble(diner.getText().toString()));
        Long idRegistro = db.insert(Utilidades.TABLA_USUARIO,Utilidades.USUARIO_idusuario,cv);

        Toast.makeText(getBaseContext(), "Registro Exitoso | idRegis: "+idRegistro  , Toast.LENGTH_SHORT).show();
        db.close();
    }
}
