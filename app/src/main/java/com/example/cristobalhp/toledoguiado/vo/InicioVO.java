package com.example.cristobalhp.toledoguiado.vo;

import com.example.cristobalhp.toledoguiado.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chermida on 25/07/2016.
 */
public class InicioVO {
    private String name;
    private int imageSource;
    private String subtitulo;
    public static final List<InicioVO> INICIO_VOS_LIST = new ArrayList<InicioVO>();

    public InicioVO (int imageSource, String name) {
        this.name = name;
        this.imageSource = imageSource;
    }


    static {
        INICIO_VOS_LIST.add(new InicioVO(R.drawable.toledo_imprescindible,"Catedral"));
        INICIO_VOS_LIST.add(new InicioVO(R.drawable.condeorgaz_pr,"El entierro del Señor de Orgaz del Greco"));
        INICIO_VOS_LIST.add(new InicioVO(R.drawable.santa_maria_blanca_pr,"Sinagoga de Santamaría la blanca"));
        INICIO_VOS_LIST.add(new InicioVO(R.drawable.tres_culturas_bea,"Mezquita del cristo de la luz"));
        INICIO_VOS_LIST.add(new InicioVO(R.drawable.sinagoga_transito_pr,"Sinagoga del transito"));
        INICIO_VOS_LIST.add(new InicioVO(R.drawable.san_juan_reyes_pr,"San Juan de los reyes"));
        INICIO_VOS_LIST.add(new InicioVO(R.drawable.toledo_subterraneo_bea,"Subterraneos de toledo"));
        INICIO_VOS_LIST.add(new InicioVO(R.drawable.visita_nocturno_bea,"Cobertizos"));
    }


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String getSubtitulo() {
        return subtitulo;
    }
    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public int getImageSource() {
        return imageSource;
    }
    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }
}
