package com.example.mycevicheriaapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycevicheriaapp.R;
import com.example.mycevicheriaapp.models.HomeVerModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeVerAdapter extends RecyclerView.Adapter <HomeVerAdapter.ViewHolder> {

    Context context;

    private List<HomeVerModel> homeVerModels;
    private LayoutInflater nInflater;

    public HomeVerAdapter(Context context, List<HomeVerModel> homeVerModels) {
        this.context = context;
        this.nInflater = LayoutInflater.from(context);
        this.homeVerModels = homeVerModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_plato, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeVerAdapter.ViewHolder holder, int position) {

        HomeVerModel homeVerModel = homeVerModels.get(position);
        holder.bind(homeVerModel);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(context, HomeCevicheAdapter.class);

                //Poner detalles de los platos como extras en el Intent

                intent.putExtra("platoId", homeVerModel.getPlatoId());
                intent.putExtra("namePlato", homeVerModel.getNamePlato());
                intent.putExtra("descripcionPlato", homeVerModel.getDescripcionPlato());
                intent.putExtra("precioPlato", homeVerModel.getPrecioPlato());
                intent.putExtra("tipoPlatoId", homeVerModel.getTipoPlatoId());
                intent.putExtra("imagePlato", homeVerModel.getImagePlato());



                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return homeVerModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView platoid ,plato, descripcion, precio, platotipoId;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            platoid = itemView.findViewById(R.id.txtID);
            plato = itemView.findViewById(R.id.nombrePlato1);
            descripcion = itemView.findViewById(R.id.plato1descri);
            precio = itemView.findViewById(R.id.precioPlato1);
            platotipoId = itemView.findViewById(R.id.tpPlatoId1);
            imageView = itemView.findViewById(R.id.imagenPlato1);


        }

        public void bind(HomeVerModel homeVerModel) {

            platoid.setText("ID: " + homeVerModel.getPlatoId());
            plato.setText(homeVerModel.getNamePlato());
            descripcion.setText("Descripción: "+ homeVerModel.getDescripcionPlato());
            precio.setText(homeVerModel.getPrecioPlato());
            platotipoId.setText(homeVerModel.getTipoPlatoId());
            String url = "https://cevicherias.informaticapp.com/public/ImagenesCevicheria/" + homeVerModel.getImagePlato();
            Picasso.get().load(url).into(imageView);
        }
    }
}
