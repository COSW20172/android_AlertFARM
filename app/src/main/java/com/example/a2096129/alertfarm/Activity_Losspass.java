package com.example.a2096129.alertfarm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 2096129 on 11/22/17.
 */

public class Activity_Losspass extends AppCompatActivity {

    EditText email;
    Button sendemail,sendcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.losspass);

        email = (EditText) findViewById(R.id.email);
        sendemail = (Button) findViewById(R.id.sendemail);
        sendcode = (Button) findViewById(R.id.sendcode);


    }
}
