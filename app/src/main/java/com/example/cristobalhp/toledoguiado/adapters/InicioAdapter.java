package com.example.cristobalhp.toledoguiado.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cristobalhp.toledoguiado.R;
import com.example.cristobalhp.toledoguiado.vo.VisitaVO;

import java.util.List;

/**
 * Created by chermida on 23/06/2016.
 */
public class InicioAdapter extends RecyclerView.Adapter<InicioAdapter.ViewHolder>{
    private List<VisitaVO> items;

    @Override
    public InicioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_visita, parent, false);
        return new InicioAdapter.ViewHolder(v);
    }
    public InicioAdapter(List<VisitaVO> items) {
        this.items = items;
    }
    @Override
    public void onBindViewHolder(InicioAdapter.ViewHolder holder, int position) {
        VisitaVO item =items.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(holder.imagen);
        holder.nombre.setText(item.getTitulo());
        holder.precio.setText(item.getPrecio()+"€");
        holder.horario.setText(item.getHorario());
    }

    @Override
    public int getItemCount() {
        return VisitaVO.VISITAS.size();

    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
      /*  public TextView nombre;
        public TextView precio;
        public ImageView imagen;*/
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView horario;
        public TextView descripcion;
        public TextView precio;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_titulo);
            precio = (TextView) v.findViewById(R.id.precio_visita);
            imagen = (ImageView) v.findViewById(R.id.miniatura_visita);
            descripcion = (TextView) v.findViewById(R.id.descripcion);
            horario = (TextView) v.findViewById(R.id.horario);
        }
    }
}
