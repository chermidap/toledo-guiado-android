package com.example.cristobalhp.toledoguiado.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristobalhp.toledoguiado.R;


public class FragmentoContacto extends Fragment {

    Button buttonPhone;
    Button buttonEmail;
    TextView textoPhone;
    TextView textoEmail;
    EditText campoNombreEmail;
    EditText campoMensajeEmail;
    EditText campoAsuntoEmail;

    String emailDestino;
    String phoneNumer;
    String sNombreEmal;
    String sMensajeEmail;
    String sAsuntoEmail;


    public FragmentoContacto() {
        // Required empty public constructor
    }


    public static FragmentoContacto newInstance(String param1, String param2) {
        FragmentoContacto fragment = new FragmentoContacto();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragmento_contacto, container, false);
        buttonPhone = (Button) view.findViewById(R.id.llama_button);
        textoPhone=(TextView) view.findViewById(R.id.contacto_txt_phone);
        phoneNumer = (String)textoPhone.getText();
        buttonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                phoneIntent.setData(Uri.parse("tel:"+phoneNumer));
                try{
                    startActivity(phoneIntent);
                }

                catch (android.content.ActivityNotFoundException ex){
                  //  Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonEmail =(Button) view.findViewById(R.id.contacto_button);
        textoEmail = (TextView) view.findViewById(R.id.contacto_txt_email);
        campoNombreEmail = (EditText) view.findViewById(R.id.campo_contacto_nombre);
        campoAsuntoEmail = (EditText) view.findViewById(R.id.campo_contacto_asunto);
        campoMensajeEmail = (EditText) view.findViewById(R.id.campo_contacto_mensaje);

        emailDestino =(String) textoEmail.getText();


        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                sNombreEmal=(String) campoNombreEmail.getText().toString();
                sMensajeEmail=(String) campoMensajeEmail.getText().toString();
                sAsuntoEmail=(String) campoAsuntoEmail.getText().toString();
                email.setType("message/rfc822");
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{emailDestino});
                email.putExtra(Intent.EXTRA_CC, new String[]{""});
                email.putExtra(Intent.EXTRA_BCC, new String[]{""});
                email.putExtra(Intent.EXTRA_SUBJECT, sAsuntoEmail);
                email.putExtra(Intent.EXTRA_TEXT, sMensajeEmail);

                startActivity(Intent.createChooser(email, "Selecciona un cliente de correo :"));
            }
        });

        return view;
    }


}
