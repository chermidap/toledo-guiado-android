package com.example.cristobalhp.toledoguiado.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.cristobalhp.toledoguiado.R;
import com.example.cristobalhp.toledoguiado.adapters.VisitaAdapter;
import com.example.cristobalhp.toledoguiado.vo.VisitaVO;

public class VisitasActivity extends AppCompatActivity {


    private RecyclerView reciclador;
    private VisitaAdapter adaptador;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitas);


        reciclador = (RecyclerView) findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(this);
        reciclador.setLayoutManager(layoutManager);

       // adaptador = new VisitaAdapter(VisitaVO.VISITAS);
       // reciclador.setAdapter(adaptador);
        setToolbar();
       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarvisitas);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitle(R.string.title_activity_visitas);

    }

}
