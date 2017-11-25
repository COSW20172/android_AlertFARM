package com.example.a2096129.alertfarm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 2096129 on 11/21/17.
 */

public class Activity_Changepass extends AppCompatActivity implements View.OnClickListener{

    TextView txtusu;
    EditText ant,nue,confi;
    Button cam;
    SharedPreferences almacenarusu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.changepass);
        super.onCreate(savedInstanceState);
        txtusu= (TextView) findViewById(R.id.txtusu);

        Intent anterior=getIntent();
        Bundle dat=anterior.getExtras();
        String usu=dat.getString("Usuario");
        txtusu.setText(usu);
        ant= (EditText) findViewById(R.id.ant);
        nue= (EditText) findViewById(R.id.nue);
        confi= (EditText) findViewById(R.id.confi);
        cam= (Button) findViewById(R.id.cam);
        cam.setOnClickListener(this);
        almacenarusu2= this.getSharedPreferences("com.example.clases.myapplication.datos", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        String leerant=ant.getText().toString();
        String nuevaclave=nue.getText().toString();
        String conclave=confi.getText().toString();
        String clavealma=almacenarusu2.getString("Clave","123");
        if (leerant.equals(clavealma)){
            if (nuevaclave.equals(conclave)){
                SharedPreferences.Editor datosguar;
                datosguar=almacenarusu2.edit();
                datosguar.putString("Clave",conclave);
                datosguar.commit();


                Toast.makeText(this, "Clave guardada", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "No coinciden claves", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this,"No es la clave del usuario",Toast.LENGTH_LONG).show();
        }
    }
}
