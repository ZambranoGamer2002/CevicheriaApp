package com.example.mycevicheriaapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mycevicheriaapp.MainActivity;
import com.example.mycevicheriaapp.R;
import com.example.mycevicheriaapp.adapters.HomeCevicheAdapter;
import com.example.mycevicheriaapp.adapters.HomeVerAdapter;
import com.example.mycevicheriaapp.adapters.UpdateVerticalRec;
import com.example.mycevicheriaapp.core.ConexionAPI;
import com.example.mycevicheriaapp.databinding.FragmentHomeBinding;
import com.example.mycevicheriaapp.data.model.HomeHorModel;
import com.example.mycevicheriaapp.data.model.HomeVerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment implements UpdateVerticalRec {

    RecyclerView homeHorizontalRec, homeVerticalRec;
    ArrayList<HomeHorModel> homeHorModelList;
    HomeCevicheAdapter homeCevicheAdapter;

    TextView txtUser;
    RequestQueue requestQueue;

    //Vertical

    ArrayList<HomeVerModel> homeVerModelList;
    HomeVerAdapter homeVerAdapter;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeHorizontalRec = root.findViewById(R.id.home_ceviches_rec);
        homeVerticalRec = root.findViewById(R.id.home_ver_rec);
        txtUser = root.findViewById(R.id.tvUserLogin);


        //Ceviche RecuyclerView
        recyclerViewTypeCeviche();



        loadData();

        return root;
    }

    public void recyclerViewTypeCeviche() {
        homeHorModelList = new ArrayList<>();


        //Colocar la respectiva carta a elegir
        homeHorModelList.add(new HomeHorModel(R.drawable.ceviches_img, "Ceviches"));
        homeHorModelList.add(new HomeHorModel(R.drawable.combos_img, "Combos"));
        homeHorModelList.add(new HomeHorModel(R.drawable.menus_img, "Menus"));
        homeHorModelList.add(new HomeHorModel(R.drawable.bebidas_img, "Bebidas"));


        homeCevicheAdapter = new HomeCevicheAdapter(this, getActivity(), homeHorModelList, new HomeCevicheAdapter.OnItemClickLister() {
            @Override
            public void OnItemClick(String Item) {
                onItemSelected(Item);
            }
        });


        homeHorizontalRec.setAdapter(homeCevicheAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);

        //Vertical RecyclerView
        //Para los ceviches
        homeVerModelList = new ArrayList<>();


        //Este lado era la imagenes de los ceviches

        homeVerAdapter = new HomeVerAdapter(getActivity(), homeVerModelList);
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }

    private void onItemSelected(String item) {

        Toast.makeText(requireActivity(), item, Toast.LENGTH_SHORT).show();
        System.out.print("DelPrada");
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MisDatosUsuario", Context.MODE_PRIVATE);
        txtUser.setText(sharedPreferences.getString("usuario", ""));
    }

    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {

        homeVerAdapter = new HomeVerAdapter(getContext(), list);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);
    }
}