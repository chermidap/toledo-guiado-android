package com.example.cristobalhp.toledoguiado.fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cristobalhp.toledoguiado.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoReserva extends Fragment {

    private EditText dateEtxt;
    private Button buttonReserva;
    private DatePickerDialog fromDatePickerDialog;
    private EditText campo_nombre;
    private EditText campo_telefono;
    private EditText campo_correo;
    private EditText campo_num_personas;
    private EditText campo_observaciones;
    private Spinner visitas_disponibles;

    private String sCampo_nombre;
    private String sCampo_telefono;
    private String sCampo_correo;
    private String sCampo_num_personas;
    private String sCampo_observaciones;
    private String selectedVisitas_disponibles;
    private String sDateEtxt;
    private  String visitaSelect;


    private SimpleDateFormat dateFormatter;

    public FragmentoReserva() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =inflater.inflate(R.layout.fragmento_reserva, container, false);
        visitas_disponibles= (Spinner)v.findViewById(R.id.visitas_disponibles);
        if(getArguments()!=null){
            visitaSelect=getArguments().getString("message");
            if(visitaSelect!=null){
                visitas_disponibles.setEnabled(false);
                visitas_disponibles.setClickable(false);
                selectedVisitas_disponibles=visitaSelect;
                selectSpinnerValue(visitas_disponibles, selectedVisitas_disponibles);
            }
        }
       campo_nombre = (EditText)v.findViewById(R.id.campo_nombre);
       campo_telefono= (EditText)v.findViewById(R.id.campo_telefono);
       campo_correo= (EditText)v.findViewById(R.id.campo_correo);
       campo_num_personas= (EditText)v.findViewById(R.id.campo_num_personas);
       campo_observaciones= (EditText)v.findViewById(R.id.campo_observaciones);


        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        buttonReserva = (Button)v.findViewById(R.id.reserva_button_fragment);
        dateEtxt = (EditText)v.findViewById(R.id.campo_fecha);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dateEtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDatePickerDialog.show();
            }
        });

        buttonReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);

                sCampo_nombre =(String) campo_nombre.getText().toString();
                sCampo_telefono=(String) campo_telefono.getText().toString();
                sCampo_correo=(String)campo_correo .getText().toString();
                sCampo_num_personas =(String)campo_num_personas.getText().toString();
                sCampo_observaciones=(String) campo_observaciones.getText().toString();
                selectedVisitas_disponibles = (String)visitas_disponibles.getSelectedItem().toString();
                sDateEtxt=(String)dateEtxt.getText().toString();

               if( validarDatos()){
                   String bodyEmail= creaCuerpoSolicitud();

                   email.setType("message/rfc822");
                   email.putExtra(Intent.EXTRA_EMAIL,new String[]{"info@toledoguiado.es"});
                   email.putExtra(Intent.EXTRA_CC, new String[]{""});
                   email.putExtra(Intent.EXTRA_BCC, new String[]{""});
                   email.putExtra(Intent.EXTRA_SUBJECT, "Reserva");
                   email.putExtra(Intent.EXTRA_TEXT, bodyEmail);

                   startActivity(Intent.createChooser(email, "Selecciona un cliente de correo :"));
                }else{
                   Toast.makeText(getActivity() ,"No se ha podido realizar la reserva", Toast.LENGTH_LONG).show();

               }

            }
        });


        return v;
    }

    private String creaCuerpoSolicitud(){

        StringBuilder cuerpoReserva = new StringBuilder();
        cuerpoReserva.append("Nombre:").append(sCampo_nombre).append("\n");
        cuerpoReserva.append("Visita a realizar:").append(selectedVisitas_disponibles).append("\n");
        cuerpoReserva.append("Número de personas:").append(sCampo_num_personas).append("\n");
        cuerpoReserva.append("Fecha:").append(sDateEtxt).append("\n");
        cuerpoReserva.append("Telefono:").append(sCampo_telefono).append("\n");
        cuerpoReserva.append("Email:").append(sCampo_correo).append("\n");
        cuerpoReserva.append("Observaciones:").append(sCampo_observaciones).append("\n");

        return cuerpoReserva.toString();
    }

    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            campo_nombre.setError("Nombre inválido");
            return false;
        } else {
            campo_nombre.setError(null);
        }

        return true;
    }

    private boolean esTelefonoValido(String telefono) {
        if (!Patterns.PHONE.matcher(telefono).matches()) {
            campo_telefono.setError("Teléfono inválido");
            return false;
        } else {
            campo_telefono.setError(null);
        }

        return true;
    }

    private boolean esCorreoValido(String correo) {
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            campo_correo.setError("Correo electrónico inválido");
            return false;
        } else {
            campo_correo.setError(null);
        }

        return true;
    }
    private boolean esFechaValida(String fecha) {
        if (!fecha.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
            dateEtxt.setError("Formato de fecha incorrecto");
            return false;
        }
        else {
            dateEtxt.setError(null);
        }

        return true;
    }

    private void selectSpinnerValue(Spinner spinner, String myString)
    {
        int index = 0;
        for(int i = 0; i < spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).toString().equals(myString)){
                spinner.setSelection(i);
                break;
            }
        }
    }

    private boolean validarDatos() {
       boolean resultado = false;
        boolean a = esNombreValido(sCampo_nombre);
        boolean b = esTelefonoValido(sCampo_telefono);
        boolean c = esCorreoValido(sCampo_correo);
        boolean d = esFechaValida(sDateEtxt);

        if (a && b && c && d) {
            resultado=true;
        }
        return resultado;
    }


}
