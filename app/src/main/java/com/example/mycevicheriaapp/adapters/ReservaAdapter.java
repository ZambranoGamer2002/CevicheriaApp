package com.example.mycevicheriaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycevicheriaapp.R;
import com.example.mycevicheriaapp.data.model.ReservaModel;

import java.util.ArrayList;
import java.util.List;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.MyViewHolder> {

    private Context context;
    private List<ReservaModel> reservaModel;
    private LayoutInflater nInflater;

    public ReservaAdapter(Context context, List<ReservaModel> reservaModel){
        this.nInflater = LayoutInflater.from(context);
        this.context = context;
        this.reservaModel = reservaModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = nInflater.inflate(R.layout.listar_mis_reservas, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaAdapter.MyViewHolder holder, int position) {
        holder.nameMesa.setText(reservaModel.get(position).getReseNumPerso());
        holder.reservaFecha.setText(reservaModel.get(position).getReseFecha());
        holder.reservaHora.setText(reservaModel.get(position).getReseHora());
    }

    @Override
    public int getItemCount() {
        return reservaModel.size();
    }
    public void filtrar(ArrayList<ReservaModel>listaFiltrada){
        reservaModel.clear();
        reservaModel.addAll(listaFiltrada);
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameMesa, reservaFecha, reservaHora;
        RelativeLayout relativeLayout;

        public MyViewHolder (View intemView){
            super(intemView);
            nameMesa = itemView.findViewById(R.id.txtMisReservas);
            reservaFecha = itemView.findViewById(R.id.txtReservaDescrip);
            reservaHora = itemView.findViewById(R.id.txtHoraReserva);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
