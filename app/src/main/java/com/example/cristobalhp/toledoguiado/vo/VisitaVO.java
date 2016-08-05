package com.example.cristobalhp.toledoguiado.vo;

import com.example.cristobalhp.toledoguiado.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristobal on 15/4/16.
 */
public class VisitaVO {

    public String titulo;
    public String precio;
    public String horario;
    public int descripcion;
    private int idDrawable;
    public String puntoPartida;
    public String duracion;

    public static final List<VisitaVO> VISITAS = new ArrayList<VisitaVO>();
    public static final List<VisitaVO> GRUPOS = new ArrayList<>();

    public VisitaVO(String titulo, String precio, String horario,String puntoPartida,String duracion ,int descripcion,int idDrawable) {
        this.titulo = titulo;
        this.precio = precio;
        this.horario = horario;
        this.descripcion = descripcion;
        this.idDrawable = idDrawable;
        this.puntoPartida =puntoPartida;
        this.duracion = duracion;
    }

    static {
        VISITAS.add(new VisitaVO("Toledo Imprescindible", "20 €", "JUEVES, VIERNES Y SÁBADOS a las 11:30 / (10:30 en julio y agosto)","Plaza de Zocodover, junto a la Oficina de Turismo","2:30 horas aprox." ,R.string.descripcion_toledo_imprescindible,R.drawable.toledo_imprescindible));
        VISITAS.add(new VisitaVO("Toledo subterráneo", "12 €", "JUEVES Y DOMINGOS a las 18:00","Plaza de Zocodover, junto a la Oficina de Turismo","2 horas aprox.",R.string.descripcion_toledo_subterraneo,R.drawable.toledo_subterraneo_bea));
        VISITAS.add(new VisitaVO("Tres culturas", "15 €", "DOMINGOS a las 11:30","Plaza de Zocodover, junto a la Oficina de Turismo","2 horas aprox.",R.string.descripcion_toledo_tresculturas,R.drawable.tres_culturas_bea));
        VISITAS.add(new VisitaVO("Ruta nocturna + dos subterráneos | Leyendas, anécdotas y magia", "10 €", "VIERNES, SÁBADOS  Y VÍSPERAS DE FESTIVOS a las 20:00","Plaza de Zocodover, junto a la Oficina de Turismo","2 horas aprox.",R.string.descripcion_toledo_ruta_nocturna,R.drawable.visita_nocturno_bea));
       /* GRUPOS.add(new VisitaVO("Tres Culturas","135","3 horas aprox.",R.string.tres_culturas,R.drawable.cristo_de_la_luz));
        GRUPOS.add(new VisitaVO("Toledo Monumental","135","3 horas aprox.",R.string.toledo_monumental,R.drawable.catedral_toledo_peq));
        GRUPOS.add(new VisitaVO("Leyendas, Anécdotas y Magia en Toledo","135","2 horas aprox",R.string.toledo_leyendas_anectodas,R.drawable.cobertizo_toledo_peq));
        GRUPOS.add(new VisitaVO("Puertas y Murallas","135","2 horas aprox",R.string.toledo_puertas_murallas,R.drawable.puertas_y_murallas_de_toledo));
        GRUPOS.add(new VisitaVO("Toledo a la Carta","a determinar","por determinar",R.string.toledo_a_carta,R.drawable.a_la_carta));*/
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
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
}
