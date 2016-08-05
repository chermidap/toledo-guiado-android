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
import com.example.cristobalhp.toledoguiado.adapters.GruposAdapter;
import com.example.cristobalhp.toledoguiado.adapters.VisitaAdapter;
import com.example.cristobalhp.toledoguiado.vo.GrupoVO;


public class FragmentoGrupos extends Fragment {

    private RecyclerView reciclador;
    private GruposAdapter adaptador;
    private LinearLayoutManager layoutManager;
    private FragmentManager manager;

    public FragmentoGrupos() {
        // Required empty public constructor
    }
    public void setManager(FragmentManager manager) {
        this.manager = manager;
    }

    public FragmentManager getManager() {
        return manager;
    }

    public static FragmentoGrupos newInstance() {
        FragmentoGrupos fragment = new FragmentoGrupos();
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
        View view =  inflater.inflate(R.layout.fragmento_grupos, container, false);
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador_grupos);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);
        adaptador = new GruposAdapter(GrupoVO.GRUPOS,manager);
        reciclador.setAdapter(adaptador);
        return view;
    }

}
