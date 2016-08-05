package com.example.cristobalhp.toledoguiado.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristobalhp.toledoguiado.R;

public class ContactoActivity extends AppCompatActivity {

    TextView textoPhone;
    TextView textoEmail;
    String emailDestino;
    String phoneNumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        textoPhone = (TextView) findViewById(R.id.contacto_txt_phone);
        phoneNumer = (String)textoPhone.getText();
        textoPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                phoneIntent.setData(Uri.parse("tel:"+phoneNumer));
                try{
                    startActivity(phoneIntent);
                }

                catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show();
                }
            }
        });

        textoEmail = (TextView) findViewById(R.id.contacto_txt_email);
        emailDestino =(String) textoEmail.getText();

        textoEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{emailDestino});
                email.putExtra(Intent.EXTRA_SUBJECT, "Información");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Selecciona un cliente de correo :"));
            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
