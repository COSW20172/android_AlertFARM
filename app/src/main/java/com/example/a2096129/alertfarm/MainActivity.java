package com.example.a2096129.alertfarm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText user, pass;
    Button login, register, olvcon;

    SharedPreferences almacenarusu2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //        super.onActivityResult(requestCode, resultCode, data);
        if(almacenarusu2.contains("Usuario")){
            user.setEnabled(true);
            login.setEnabled(true);
            pass.setEnabled(true);
        }
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        user= (EditText) findViewById(R.id.user);
        pass= (EditText)findViewById(R.id.pass);
        login= (Button) findViewById(R.id.login);
        register= (Button) findViewById(R.id.register);
        olvcon= (Button) findViewById(R.id.olvcon);
        login.setOnClickListener( this);
        register.setOnClickListener(this);
        olvcon.setOnClickListener(this);

        almacenarusu2= this.getSharedPreferences("com.example.clases.myapplication.datos", Context.MODE_PRIVATE);
        if(almacenarusu2.contains("Usuario")){
            user.setEnabled(false);
            pass.setEnabled(false);
            login.setEnabled(false);
        }


    }


    public void onClick(View view) {

        if(view.getId()==R.id.login) {
            /*
            String usu1=almacenarusu2.getString("Usuario","abc");
            String pass1=almacenarusu2.getString("Clave","123");
            String us=user.getText().toString();
            String pas=pass.getText().toString();
            if(us.equals(usu1) && pas.equals(pass1)) {
                Bundle datos= new Bundle();
                datos.putString("Usuario",us);*/
                Intent n= new Intent(MainActivity.this,Navigation_Drawer.class);
                //n.putExtras(datos);
                startActivity(n);
        }
        else if (view.getId() == R.id.register){
            /*String us=user.getText().toString();
            Bundle datos= new Bundle();
            datos.putString("Usuario",us);*/
            Intent next = new Intent(MainActivity.this,Activity_Register.class);
            //next.putExtras(datos);
            startActivity(next);
        }
        else{
            Intent next = new Intent(MainActivity.this,Activity_Losspass.class);
            startActivity(next);
        }
    }

}
