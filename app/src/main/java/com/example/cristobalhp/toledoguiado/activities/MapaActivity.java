package com.example.cristobalhp.toledoguiado.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.cristobalhp.toledoguiado.R;
import com.example.cristobalhp.toledoguiado.fragments.FragmentoMapa;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends AppCompatActivity  implements OnMapReadyCallback {

    private FragmentoMapa mFirstFragmentoMapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        setToolbar();

        mFirstFragmentoMapa =  FragmentoMapa.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.map, mFirstFragmentoMapa)
                .commit();
        mFirstFragmentoMapa.getMapAsync(this);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mapa);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitle(R.string.title_grupos_mapa);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng toledo = new LatLng(39.8595222, -4.0213528);

        googleMap.addMarker(new MarkerOptions()
                .position(toledo)
                .title("Punto de partida"));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(toledo)
                .zoom(16)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}
