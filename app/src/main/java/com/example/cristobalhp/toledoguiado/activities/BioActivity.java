package com.example.cristobalhp.toledoguiado.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cristobalhp.toledoguiado.R;

public class BioActivity extends AppCompatActivity {


    private TextView textPerfil;
    private TextView textTituloPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);


      /*  textPerfil = (TextView)findViewById(R.id.textPerfil);
        Typeface tf = ToledoGuiadoFont.getFont(getAssets(), ToledoGuiadoFont.QUICKSAND_REGULAR);
        Typeface tfb = ToledoGuiadoFont.getFont(getAssets(), ToledoGuiadoFont.QUICKSAND_BOLD);
        textPerfil.setTypeface(tf);
        textTituloPerfil = (TextView)findViewById(R.id.tituloPerfil);
        textTituloPerfil.setTypeface(tfb);*/


        ImageView myImageView = (ImageView)findViewById(R.id.image_bio);
        setToolbar();

        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();

        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.collapser);
        collapser.setTitle("Conócenos"); // Cambiar título
        Glide.with(this)
                .load(R.drawable.img_bio)
                .centerCrop()
                .into(myImageView);

        // Setear escucha al FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSnackBar("Opción de Chatear");
                    }
                }
        );
    }

    private void setToolbar() {
        // Añadir la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarbio);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    /**
     * Proyecta una {@link Snackbar} con el string usado
     *
     * @param msg Mensaje
     */
    private void showSnackBar(String msg) {
        Snackbar
                .make(findViewById(R.id.coordinator), msg, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                showSnackBar("Se abren los ajustes");
                return true;
            case R.id.action_add:
                showSnackBar("Añadir a contactos");
                return true;
            case R.id.action_favorite:
                showSnackBar("Añadir a favoritos");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

}
