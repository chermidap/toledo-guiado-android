package com.example.cristobalhp.toledoguiado.adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.example.cristobalhp.toledoguiado.R;
import com.example.cristobalhp.toledoguiado.fragments.FragmentoGrupos;
import com.example.cristobalhp.toledoguiado.fragments.FragmentoReserva;
import com.example.cristobalhp.toledoguiado.vo.GrupoVO;
import com.example.cristobalhp.toledoguiado.vo.VisitaVO;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by cristobal on 2/5/16.
 */
public class GruposAdapter
        extends RecyclerView.Adapter<GruposAdapter.GrupoViewHolder>  implements View.OnClickListener{


    private final List<GrupoVO> items;
    private Button reserva_button;
    private FragmentManager manager;
    private ImageView img_share;
    private ImageView image;

    @Override
    public void onClick(View v) {
        reserva_button.setTextColor(Color.BLACK);
        LinearLayout padre=(LinearLayout) v.getParent().getParent().getParent();
        TextView titulo = (TextView)padre.findViewById(R.id.nombre_titulo_grupo);
        String tit = (String) titulo.getText();
        FragmentoReserva fragmento_reserva = new FragmentoReserva();
        Bundle bundle=new Bundle();
        bundle.putString("message",tit);
        fragmento_reserva.setArguments(bundle);
        manager.beginTransaction()
                .replace(R.id.contenedor_principal, fragmento_reserva)
                .commit();
    }

    public static class GrupoViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;
        public TextView descripcion;
        public TextView duracion;
        public FragmentManager manager;
        public Button res_boton;

        public GrupoViewHolder(View v,FragmentManager manager) {
            super(v);
            this.manager=manager;
            nombre = (TextView) v.findViewById(R.id.nombre_titulo_grupo);
            precio = (TextView) v.findViewById(R.id.precio_grupo);
            imagen = (ImageView) v.findViewById(R.id.miniatura_visita_grupo);
            descripcion = (TextView) v.findViewById(R.id.descripcion_grupo);
            duracion = (TextView) v.findViewById(R.id.duracion_grupo);
            res_boton = (Button) v.findViewById(R.id.reserva_button);
        }
    }


    public GruposAdapter(List<GrupoVO> items, FragmentManager manager) {
        this.items = items;
        this.manager=manager;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public GrupoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_grupo_new, viewGroup, false);
        reserva_button = (Button) v.findViewById(R.id.reserva_button);
        img_share=(ImageView) v.findViewById(R.id.image_action_share);
        img_share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //  Bitmap b = BitmapFactory.decodeResource(v.getResources(),R.id.miniatura_visita);

                LinearLayout padre=(LinearLayout) v.getParent().getParent().getParent().getParent();
                FrameLayout frame = (FrameLayout) padre.findViewById(R.id.frameGrupo);
                TextView titulo = (TextView) padre.findViewById(R.id.nombre_titulo_grupo);
                image=(ImageView) frame.findViewById(R.id.miniatura_visita_grupo);
                LinearLayout layoutDesc = (LinearLayout) frame.findViewById(R.id.linear_decripcion_grupo);
                TextView desc = (TextView) layoutDesc.findViewById(R.id.descripcion_grupo);


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
        return new GrupoViewHolder(v,manager);
    }

    @Override
    public void onBindViewHolder(GrupoViewHolder viewHolder, int i) {
        GrupoVO item = items.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);

        viewHolder.nombre.setText(item.getTitulo());
        viewHolder.precio.setText(item.getPrecio());
        viewHolder.descripcion.setText(item.getDescripcion());
        viewHolder.duracion.setText(item.duracion);

    }

}
