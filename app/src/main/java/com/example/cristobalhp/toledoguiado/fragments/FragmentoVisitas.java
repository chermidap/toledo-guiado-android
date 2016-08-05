package com.example.cristobalhp.toledoguiado.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cristobalhp.toledoguiado.R;
import com.example.cristobalhp.toledoguiado.adapters.VisitaAdapter;
import com.example.cristobalhp.toledoguiado.vo.VisitaVO;


public class FragmentoVisitas extends Fragment {

    private RecyclerView reciclador;
    private VisitaAdapter adaptador;
    private LinearLayoutManager layoutManager;
    private FragmentManager manager;


    public FragmentoVisitas() {};

    public void setManager(FragmentManager manager) {
        this.manager = manager;
    }

    public FragmentManager getManager() {
        return manager;
    }

    public static FragmentoVisitas newInstance() {
        FragmentoVisitas fragment = new FragmentoVisitas();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragmento_visitas, container, false);
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador_visitas);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);
        adaptador = new VisitaAdapter(VisitaVO.VISITAS,manager);
        reciclador.setAdapter(adaptador);


        return view;
    }

}
