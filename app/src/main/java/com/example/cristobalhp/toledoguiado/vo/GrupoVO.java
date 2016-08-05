package com.example.cristobalhp.toledoguiado.vo;

import com.example.cristobalhp.toledoguiado.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristobal on 2/5/16.
 */
public class GrupoVO {

    public String titulo;
    public String precio;
    public int descripcion;
    private int idDrawable;
    public String duracion;


    public static final List<GrupoVO> GRUPOS = new ArrayList<GrupoVO>();


    public GrupoVO(String titulo, String precio, String duracion , int descripcion, int idDrawable) {
        this.titulo = titulo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.idDrawable = idDrawable;

        this.duracion = duracion;
    }

    static {
       /* VISITAS.add(new VisitaVO("Toledo Imprescindible", "20 €", "JUEVES, VIERNES Y SÁBADOS a las 11:30 / (10:30 en julio y agosto)","Plaza de Zocodover, junto a la Oficina de Turismo","2:30 horas aprox." , R.string.descripcion_toledo_imprescindible,R.drawable.toledo_imprescindible));
        VISITAS.add(new VisitaVO("Toledo subterráneo", "12 €", "JUEVES Y DOMINGOS a las 18:00","Plaza de Zocodover, junto a la Oficina de Turismo","2 horas aprox.",R.string.descripcion_toledo_subterraneo,R.drawable.toledo_subterraneo_bea));
        VISITAS.add(new VisitaVO("Tres culturas", "15 €", "DOMINGOS a las 11:30","Plaza de Zocodover, junto a la Oficina de Turismo","2 horas aprox.",R.string.descripcion_toledo_tresculturas,R.drawable.tres_culturas_bea));
        VISITAS.add(new VisitaVO("Ruta nocturna + dos subterráneos | Leyendas, anécdotas y magia", "10 €", "VIERNES, SÁBADOS  Y VÍSPERAS DE FESTIVOS a las 20:00","\"Plaza de Zocodover, junto a la Oficina de Turismo","2 horas aprox.",R.string.descripcion_toledo_ruta_nocturna,R.drawable.visita_nocturno_bea));*/
        GRUPOS.add(new GrupoVO("Tres Culturas","135 € + IVA (entradas a monumentos no incluidas)","3 horas aprox.",R.string.tres_culturas,R.drawable.tres_culturas_bea));
        GRUPOS.add(new GrupoVO("Toledo Monumental","135 € + IVA (entradas a monumentos no incluidas)","3 horas aprox.",R.string.toledo_monumental,R.drawable.toledo_imprescindible));
        GRUPOS.add(new GrupoVO("Puertas y Murallas","135 € + IVA","2 horas aprox",R.string.toledo_puertas_murallas,R.drawable.puerta_bisagra));
        GRUPOS.add(new GrupoVO("Leyendas, Anécdotas y Magia en Toledo","135 € + IVA","2 horas aprox",R.string.toledo_leyendas_anectodas,R.drawable.visita_nocturno_bea));
        GRUPOS.add(new GrupoVO("Toledo a la Carta","a determinar","por determinar",R.string.toledo_a_carta,R.drawable.a_la_carta));
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(int descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

}
