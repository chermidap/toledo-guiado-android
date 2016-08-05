package com.example.cristobalhp.toledoguiado.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cristobalhp.toledoguiado.R;
import com.example.cristobalhp.toledoguiado.utils.ToledoGuiadoFont;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtTitulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeRuntimePermissions();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ImageView myImageView = (ImageView)findViewById(R.id.img_portada);
        Glide.with(this)
                .load(R.drawable.portadatoledoguiado2)
                .centerCrop()
                .into(myImageView);

        txtTitulo = (TextView)findViewById(R.id.titulomain);
        Typeface tf = ToledoGuiadoFont.getFont(getAssets(), ToledoGuiadoFont.QUICKSAND_LIGHT);
        txtTitulo.setTypeface(tf);
        txtTitulo.setText("ToledoGuiado");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_info) {
            Intent intent = new Intent(this, BioActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.nav_visitas) {
            Intent intent = new Intent(this, VisitasActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.nav_grupos) {
            Intent intent = new Intent(this, GruposActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.nav_reservas) {
            Intent intent = new Intent(this, ReservasActivity.class);
            this.startActivity(intent);

        } else if (id == R.id.nav_contacto) {
            Intent intent = new Intent(this, ContactoActivity.class);
            this.startActivity(intent);

        }else if(id==R.id.nav_mapa){
            Intent intent = new Intent(this, MapaActivity.class);
            this.startActivity(intent);

        }

        else if (id == R.id.nav_twitter) {

        }else if (id == R.id.nav_facebook) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void initializeRuntimePermissions() {
        int requestCode = PackageManager.PERMISSION_GRANTED;

        String[] permissions = {
                Manifest.permission.CALL_PHONE
        };

        checkRuntimePermissions(permissions, requestCode);
    }

    private void checkRuntimePermissions(String[] permissions, int requestCode) {
        boolean explanation = false;

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    explanation = true;
                }
                else {
                    // No explanation needed, we can request the permission.

                    // requestCode is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }
        }

        if (!explanation) {
            ActivityCompat.requestPermissions(this, permissions, requestCode);
        }
    }

}
