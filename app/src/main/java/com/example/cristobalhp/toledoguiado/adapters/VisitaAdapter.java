package com.example.cristobalhp.toledoguiado.adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.example.cristobalhp.toledoguiado.R;
import com.example.cristobalhp.toledoguiado.fragments.FragmentoReserva;
import com.example.cristobalhp.toledoguiado.fragments.FragmentoVisitas;
import com.example.cristobalhp.toledoguiado.vo.VisitaVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

/**
 * Created by cristobal on 15/4/16.
 */
public class VisitaAdapter extends RecyclerView.Adapter<VisitaAdapter.VisitaViewHolder> implements View.OnClickListener{

    private List<VisitaVO> items;
    private Button reserva_button;
   private FragmentManager manager;
    private ImageView img_share;
    private ImageView image;

    public VisitaAdapter(List<VisitaVO> items, FragmentManager manager) {
        this.items = items;
        this.manager=manager;
    }

    @Override
    public void onClick(View v) {
        reserva_button.setTextColor(Color.BLACK);

        LinearLayout padre=(LinearLayout) v.getParent().getParent().getParent();
        TextView titulo = (TextView)padre.findViewById(R.id.nombre_titulo);
        String tit = (String) titulo.getText();
        FragmentoReserva fragmento_reserva = new FragmentoReserva();
        Bundle bundle=new Bundle();
        bundle.putString("message",tit);
        fragmento_reserva.setArguments(bundle);
        manager.beginTransaction()
                .replace(R.id.contenedor_principal, fragmento_reserva)
                .commit();
    }

    public static class VisitaViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView horario;
        public TextView descripcion;
        public TextView precio;
        public TextView puntoPartida;
        public TextView duracion;
        public Button res_boton;
        public ImageView sh_icon;
        public FragmentManager manager;

        public VisitaViewHolder(View v,FragmentManager manager) {

            super(v);
            this.manager=manager;
            nombre = (TextView) v.findViewById(R.id.nombre_titulo);
            precio = (TextView) v.findViewById(R.id.precio_visita);
            imagen = (ImageView) v.findViewById(R.id.miniatura_visita);
            descripcion = (TextView) v.findViewById(R.id.descripcion);
            horario = (TextView) v.findViewById(R.id.horario);
            puntoPartida = (TextView) v.findViewById(R.id.puntoPartida);
            duracion = (TextView) v.findViewById(R.id.duracion);
            res_boton = (Button) v.findViewById(R.id.reserva_button);
        }
    }


    @Override
    public VisitaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_visita_new, parent, false);
        reserva_button = (Button) v.findViewById(R.id.reserva_button);
        img_share=(ImageView) v.findViewById(R.id.image_action_share);
     //   image=  (ImageView)v.findViewById(R.id.miniatura_visita);


        img_share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              //  Bitmap b = BitmapFactory.decodeResource(v.getResources(),R.id.miniatura_visita);

                LinearLayout padre=(LinearLayout) v.getParent().getParent().getParent().getParent();
                FrameLayout frame = (FrameLayout) padre.findViewById(R.id.frameVisita);
                TextView titulo = (TextView) padre.findViewById(R.id.nombre_titulo);
                image=(ImageView) frame.findViewById(R.id.miniatura_visita);
                LinearLayout layoutDesc = (LinearLayout) frame.findViewById(R.id.linear_decripcion_visita);
                TextView desc = (TextView) layoutDesc.findViewById(R.id.descripcion);


                GlideBitmapDrawable drawable = (GlideBitmapDrawable) image.getDrawable();
                Bitmap b = drawable.getBitmap();

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/*");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(v.getContext().getContentResolver(),
                        b, "Title", null);
                Uri imageUri =  Uri.parse(path);
                share.putExtra(Intent.EXTRA_STREAM, imageUri);
                share.putExtra(Intent.EXTRA_TEXT,titulo.getText().toString()+"\n"+ desc.getText().toString()+"\n"+"www.toledoguiado.es");
                v.getContext().startActivity(Intent.createChooser(share, "Selecciona una aplicación:"));

            }
        });
        reserva_button.setOnClickListener(this);
        return new VisitaViewHolder(v,manager);
    }



    @Override
    public void onBindViewHolder(VisitaViewHolder viewHolder, int position) {

        VisitaVO item =items.get(position);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getTitulo());
        viewHolder.precio.setText(item.getPrecio());
        viewHolder.horario.setText(item.getHorario());
        viewHolder.descripcion.setText(item.getDescripcion());
        viewHolder.puntoPartida.setText(item.puntoPartida);
        viewHolder.duracion.setText(item.duracion);

    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
