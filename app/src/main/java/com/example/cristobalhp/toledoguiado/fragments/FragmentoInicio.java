package com.example.cristobalhp.toledoguiado.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cristobalhp.toledoguiado.R;
import com.example.cristobalhp.toledoguiado.vo.InicioVO;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class FragmentoInicio extends Fragment {

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.visita_nocturno_bea, R.drawable.tres_culturas_bea,R.drawable.toledo_subterraneo_bea, R.drawable.toledo_imprescindible};
    List<InicioVO> inicioVOList;
    public FragmentoInicio() {
        // Required empty public constructor

    }


    public static FragmentoInicio newInstance(String param1, String param2) {
        FragmentoInicio fragment = new FragmentoInicio();
        Bundle args = new Bundle();

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
       // inflater=inflater;
        View view =  inflater.inflate(R.layout.fragmento_inicio, container, false);

        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
       int tam = InicioVO.INICIO_VOS_LIST.size();
        carouselView.setPageCount(tam);
        carouselView.setViewListener(viewListener);
        //carouselView.setImageListener(imageListener);

       /* ImageView myImageView = (ImageView)view.findViewById(R.id.imagen_inicio);
        Glide.with(this)
                .load(R.drawable.web_verano_2016)
                .centerCrop()
                .into(myImageView);*/

        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
    ViewListener viewListener = new ViewListener() {

        @Override
        public View setViewForPosition(int position) {
            LayoutInflater inflater =getActivity().getLayoutInflater();
            View customView = inflater.inflate(R.layout.inicio_carusel_layout, null);
            //set view attributes here
           InicioVO inicioVO=InicioVO.INICIO_VOS_LIST.get(position);
           ImageView imagen =  (ImageView) customView.findViewById(R.id.image_carusel);
            TextView textoTitulo = (TextView) customView.findViewById(R.id.titulo_carusel);
            textoTitulo.setText(inicioVO.getName());
            imagen.setImageResource(inicioVO.getImageSource());

            return customView;
        }
    };

}
