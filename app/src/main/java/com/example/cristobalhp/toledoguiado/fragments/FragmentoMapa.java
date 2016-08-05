package com.example.cristobalhp.toledoguiado.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoMapa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoMapa extends SupportMapFragment {

    MapView mMapView;

    GoogleMap googleMap;
    FragmentManager myFragmentManager;
    SupportMapFragment mySupportMapFragment;
    public FragmentoMapa() {
    }

    public static FragmentoMapa newInstance() {

        return new FragmentoMapa();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        return root;
    }

}
