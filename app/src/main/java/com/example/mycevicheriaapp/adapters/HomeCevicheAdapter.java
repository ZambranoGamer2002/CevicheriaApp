package com.example.mycevicheriaapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.telephony.BarringInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycevicheriaapp.R;
import com.example.mycevicheriaapp.models.HomeCevicheModel;
import com.example.mycevicheriaapp.models.HomeVerModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeCevicheAdapter extends RecyclerView.Adapter<HomeCevicheAdapter.ViewHolder> {

    UpdateVerticalRec updateVerticalRec;
    Activity activity;
    ArrayList<HomeCevicheModel> list;
    int row_index = -1;

    public HomeCevicheAdapter(UpdateVerticalRec updateVerticalRec, Activity activity, ArrayList<HomeCevicheModel> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_platos, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        // Lógica de selección de fondo
        if (row_index == position) {
            holder.cardView.setBackgroundResource(R.drawable.seleccioncolor_bg);
        } else {
            holder.cardView.setBackgroundResource(R.drawable.default_bg);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();

                // Obtén el elemento actual de la lista
                HomeCevicheModel selectedItem = list.get(position);


                // Lógica para determinar qué tipo de plato fue seleccionado
                if (selectedItem.getName().equals("Ceviches")) {

                    //Aca quiero llamar mi base de datos para mostrar mis Ceviches
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                    //homeVerModels.add(new HomeVerModel(R.drawable.plato1, "Ceviche Clásic", "Ceviche de pesacado", "30","2","url"));

                    updateVerticalRec.callBack(position, homeVerModels);
                } else if (selectedItem.getName().equals("Combos")) {

                    //Aca quiero llamar mi base de datos para mostrar mis Combos
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                    //homeVerModels.add(new HomeVerModel(R.drawable.plato9,"COMBO I", "Ceviche mas Chicharon", "30"));

                    updateVerticalRec.callBack(position, homeVerModels);
                } else if (selectedItem.getName().equals("Menus")) {

                    //Aca quiero llamar mi base de datos para mostrar mis Menus
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    //homeVerModels.add(new HomeVerModel(R.drawable.plato19,"Lomo Saltado","Lomo Saltado", "20"));

                    updateVerticalRec.callBack(position, homeVerModels);

                } else if (selectedItem.getName().equals("Bebidas")) {

                    //Aca quiero llamar mi base de datos para mostrar mis bebidas
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();

                    //homeVerModels.add(new HomeVerModel(R.drawable.bebida3,"Cicha Morada","1 litro", "12"));

                    updateVerticalRec.callBack(position, homeVerModels);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.platosCevicheIMG);
            name = itemView.findViewById(R.id.platosCevicheTXT);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }


}
