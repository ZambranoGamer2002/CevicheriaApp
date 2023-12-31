package com.example.mycevicheriaapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycevicheriaapp.R;
import com.example.mycevicheriaapp.activities.ReservaActivity;
import com.example.mycevicheriaapp.data.model.HomeMesas;

import java.util.List;

public class HomeMesaAdapter extends RecyclerView.Adapter<HomeMesaAdapter.MyViewHolder>{
    private Context context;
    private List<HomeMesas> mesa;
    private LayoutInflater nInflater;
    public HomeMesaAdapter(Context context, List<HomeMesas> mesa) {
        this.nInflater = LayoutInflater.from(context);
        this.context = context;
        this.mesa = mesa;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = nInflater.inflate(R.layout.listar_las_mesa, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,@SuppressLint("RecyclerView") int position) {
        holder.NameMesa.setText(mesa.get(position).getMesaNumero());
        holder.cantidadPersonas.setText(mesa.get(position).getMesaCantidadAsientos());
        holder.statusMesa.setText("Activo");
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(),"Seleccionaste la mesa" + mesa.get(position).getMesaNumero(),Toast.LENGTH_LONG).show();

                Intent intent = new Intent(holder.itemView.getContext(), ReservaActivity.class);

                intent.putExtra("mesa", mesa.get(position));

                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mesa.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView NameMesa,cantidadPersonas, statusMesa;
        RelativeLayout relativeLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            NameMesa = itemView.findViewById(R.id.txtNameMesa);
            statusMesa = itemView.findViewById(R.id.txtStatusMesa);
            cantidadPersonas = itemView.findViewById(R.id.txtCantidadPersonas);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
