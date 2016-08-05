package com.example.cristobalhp.toledoguiado.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cristobalhp.toledoguiado.R;
import com.example.cristobalhp.toledoguiado.fragments.FragmentoBio;
import com.example.cristobalhp.toledoguiado.fragments.FragmentoContacto;
import com.example.cristobalhp.toledoguiado.fragments.FragmentoGrupos;
import com.example.cristobalhp.toledoguiado.fragments.FragmentoInicio;
import com.example.cristobalhp.toledoguiado.fragments.FragmentoMapa;
import com.example.cristobalhp.toledoguiado.fragments.FragmentoReserva;
import com.example.cristobalhp.toledoguiado.fragments.FragmentoTarifas;
import com.example.cristobalhp.toledoguiado.fragments.FragmentoVisitas;
import com.example.cristobalhp.toledoguiado.fragments.FragmentoWeb;
import com.example.cristobalhp.toledoguiado.vo.VisitaVO;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final LocationRequest GPS_REQUEST = LocationRequest.create()
            .setInterval(3000)
            .setFastestInterval(16)
            .setNumUpdates(3)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    private static int REQUEST_CODE_RECOVER_PLAY_SERVICES = 200;

    private DrawerLayout drawerLayout;
    private FragmentoMapa mFirstFragmentoMapa;
    private FragmentoVisitas fragmentoVisitas;
    private FragmentoGrupos fragmentoGrupos;
    private ArrayList<VisitaVO> favoritos;
    private GoogleApiClient googleApiClient;
    private Double longitude;
    private Double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        agregarToolbar();
        initializeRuntimePermissions();

        initializeGooglePlayServices();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_principal);
        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_principal);

    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_help:
                showOverLay();
                return true;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }


    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle ;
        switch (itemDrawer.getItemId()) {
            case R.id.nav_inicio:
                fragmentoGenerico = new FragmentoInicio();
                break;
            case R.id.nav_info:
                fragmentoGenerico = new FragmentoBio();
                break;
            case R.id.nav_visitas:
                // Fragmento para la sección Cuenta
                fragmentoVisitas = new FragmentoVisitas();
                fragmentoVisitas.setManager(fragmentManager);
                fragmentoGenerico = fragmentoVisitas;
                break;
            case R.id.nav_grupos:
                // Fragmento para la sección Categorías
                fragmentoGrupos = new FragmentoGrupos();
                fragmentoGrupos.setManager(fragmentManager);
                fragmentoGenerico = fragmentoGrupos;
                break;
            case R.id.nav_reservas:
                // Iniciar actividad de configuración
                fragmentoGenerico = new FragmentoReserva();
                break;
            case R.id.nav_contacto:
                fragmentoGenerico = new FragmentoContacto();
                break;
            case R.id.nav_tarifas:
                fragmentoGenerico = new FragmentoTarifas();
                break;
            case R.id.nav_mapa:

                mFirstFragmentoMapa = FragmentoMapa.newInstance();
                mFirstFragmentoMapa.getMapAsync(this);
                fragmentoGenerico = mFirstFragmentoMapa;
                break;
            case R.id.nav_facebook:
                fragmentoGenerico   = new FragmentoWeb();
                bundle = new Bundle();
                bundle.putString("url", "https://www.facebook.com/Toledoguiado");
                fragmentoGenerico.setArguments(bundle);
                break;

            case R.id.nav_twitter:
                fragmentoGenerico =   new FragmentoWeb();
               bundle = new Bundle();
                bundle.putString("url", "https://twitter.com/Toledoguiado");
                fragmentoGenerico.setArguments(bundle);
                break;
        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng toledo = new LatLng(39.8595222, -4.0213528);
        LatLng catedral = new LatLng(39.8585942, -4.02550020000001);
        LatLng alcazar = new LatLng(39.8577545, -4.020496200000025);
        LatLng miLoc = new LatLng(latitude,longitude);

        MarkerOptions mrkCatdral = new MarkerOptions();
        MarkerOptions mrkAlcazar = new MarkerOptions();
        MarkerOptions mrkMiLoc = new MarkerOptions();

        mrkCatdral.position(catedral).title("Catedral de Toledo");
        mrkAlcazar.position(alcazar).title("Alcazar de Toledo");
        mrkMiLoc.position(miLoc).title("Aquí estoy");
        googleMap.addMarker(new MarkerOptions()
                .position(toledo)
                .title("Punto de partida"));
        googleMap.addMarker(mrkCatdral);
        googleMap.addMarker(mrkAlcazar);
        googleMap.addMarker(mrkMiLoc);

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(miLoc)
                .zoom(10)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
    }

    private void showOverLay() {

        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);

        dialog.setContentView(R.layout.overlay_view);

        LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.overlayLayout);
        dialog.findViewById(R.id.close_overlay);

        layout.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View arg0) {

                dialog.dismiss();

            }

        });

        dialog.show();

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
                } else {
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (lastLocation != null) {
            longitude = lastLocation.getLongitude();
            latitude = lastLocation.getLatitude();
        }

       /* LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, GPS_REQUEST, this);*/

    }

    private void initializeGooglePlayServices() {
        int checkGooglePlayServices = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (checkGooglePlayServices != ConnectionResult.SUCCESS) {
            /*
            * google play services is missing or update is required
            *  return code could be
            * SUCCESS,
            * SERVICE_MISSING, SERVICE_VERSION_UPDATE_REQUIRED,
            * SERVICE_DISABLED, SERVICE_INVALID.
            */
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
                    checkGooglePlayServices, this, REQUEST_CODE_RECOVER_PLAY_SERVICES);

            errorDialog.show();

            return;
        }

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void initializeRuntimePermissions() {
        int requestCode = PackageManager.PERMISSION_GRANTED;

        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.INTERNET,Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        checkRuntimePermissions(permissions, requestCode);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_RECOVER_PLAY_SERVICES) {
            return;
        }

        if (resultCode == RESULT_OK) {
            if (!googleApiClient.isConnecting() && !googleApiClient.isConnected()) {
                googleApiClient.connect();
            }
        }
        else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(
                    this, "Google Play Services must be installed.", Toast.LENGTH_SHORT).show();

            finish();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (googleApiClient != null) {
            googleApiClient.connect();
        }


    }

    @Override
    protected void onPause() {

        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }

        super.onPause();
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
