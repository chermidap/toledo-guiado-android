package com.example.cristobalhp.toledoguiado.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cristobalhp.toledoguiado.R;

public class ReservasActivity extends AppCompatActivity {

    private Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });



        spinner1 = (Spinner) findViewById(R.id.visitas_disponibles);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        String firstItem = String.valueOf(spinner1.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spinner1.getSelectedItem()))) {
                // ToDo when first item is selected
            } else {
                Toast.makeText(parent.getContext(),
                        "Has seleccionado : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }

    }

}
