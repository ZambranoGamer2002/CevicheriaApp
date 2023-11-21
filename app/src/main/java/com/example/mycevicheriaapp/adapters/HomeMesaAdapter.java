package com.example.mycevicheriaapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycevicheriaapp.R;
import com.example.mycevicheriaapp.activities.ReservaActivity;
import com.example.mycevicheriaapp.data.model.HomeMesas;
import com.example.mycevicheriaapp.fragments.ReservasFragment;

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
        //View view = LayoutInflater.from(context).inflate(R.layout.list_element, parent, false);
        View view = nInflater.inflate(R.layout.list_mesa, null);
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

                /*Intent intent = new Intent(holder.itemView.getContext(), ReservasFragment.class);

                intent.putExtra("mesa", mesa.get(position));

                holder.itemView.getContext().startActivity(intent);*/
                // Crear un Bundle para los datos a enviar al fragmento
                Bundle bundle = new Bundle();
                bundle.putSerializable("mesa", mesa.get(position)); // Aquí asumo que "mesa" es Serializable

// Crear una instancia del fragmento y asignarle los datos
                ReservasFragment fragment = new ReservasFragment();
                fragment.setArguments(bundle);

// Iniciar una transacción de fragmento y mostrar el fragmento
                FragmentManager fragmentManager = ((FragmentActivity) holder.itemView.getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment_content_main, fragment); // R.id.contenedor_fragment es el ID del contenedor donde se mostrará el fragmento
                transaction.addToBackStack(null); // Opcional: permite agregar a la pila de retroceso
                transaction.commit();

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
