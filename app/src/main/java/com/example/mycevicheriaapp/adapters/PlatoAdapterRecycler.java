package com.example.mycevicheriaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.mycevicheriaapp.R;
import com.example.mycevicheriaapp.data.model.PlatoModel;

import java.util.List;

public class PlatoAdapterRecycler extends RecyclerView.Adapter<PlatoAdapterRecycler.ViewHolder>  {
    Context context;
    List<PlatoModel> listaPlatoes;

    public PlatoAdapterRecycler(List<PlatoModel> listaPlatoes){
        this.listaPlatoes = listaPlatoes;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_plato, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoAdapterRecycler.ViewHolder holder, int position) {
        holder.nombrePlato.setText(listaPlatoes.get(position).getPlatoNombre());
        holder.descripPlato.setText(listaPlatoes.get(position).getPedidoDescrip());
        holder.precioPlato.setText("S/. " + listaPlatoes.get(position).getPlatoPrecio());
        String imageUrl = listaPlatoes.get(position).getPlatoFoto();

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.imagePlato);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(holder.itemView.getContext(), ProductoDetallesActivity.class);

                intent.putExtra("producto", listaPlatos.get(position));

                holder.itemView.getContext().startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPlatoes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombrePlato, descripPlato, precioPlato;
        ImageView imagePlato;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombrePlato = itemView.findViewById(R.id.nombrePlato1);
            descripPlato = itemView.findViewById(R.id.plato1descri);
            precioPlato = itemView.findViewById(R.id.precioPlato1);
            imagePlato = itemView.findViewById(R.id.imagenPlato1);
        }
    }
}
