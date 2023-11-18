package com.example.mycevicheriaapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycevicheriaapp.R;
import com.example.mycevicheriaapp.adapters.HomeCevicheAdapter;
import com.example.mycevicheriaapp.adapters.HomeVerAdapter;
import com.example.mycevicheriaapp.adapters.UpdateVerticalRec;
import com.example.mycevicheriaapp.databinding.FragmentHomeBinding;
import com.example.mycevicheriaapp.models.HomeCevicheModel;
import com.example.mycevicheriaapp.models.HomeVerModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements UpdateVerticalRec {

    RecyclerView homeHorizontalRec, homeVerticalRec;
    ArrayList<HomeCevicheModel> homeCevicheModelList;
    HomeCevicheAdapter homeCevicheAdapter;

    //Vertical

    ArrayList<HomeVerModel> homeVerModelList;
    HomeVerAdapter homeVerAdapter;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root =inflater.inflate(R.layout.fragment_home, container, false);

        homeHorizontalRec = root.findViewById(R.id.home_ceviches_rec);
        homeVerticalRec = root.findViewById(R.id.home_ver_rec);


        //Ceviche RecuyclerView

        homeCevicheModelList = new ArrayList<>();


        //Colocar la respectiva carta a elegir
        homeCevicheModelList.add(new HomeCevicheModel(R.drawable.ceviches_img,"Ceviches"));
        homeCevicheModelList.add(new HomeCevicheModel(R.drawable.combos_img,"Combos"));
        homeCevicheModelList.add(new HomeCevicheModel(R.drawable.menus_img,"Menus"));
        homeCevicheModelList.add(new HomeCevicheModel(R.drawable.bebidas_img,"Bebidas"));




        homeCevicheAdapter = new HomeCevicheAdapter(this, getActivity(), homeCevicheModelList,new HomeCevicheAdapter.OnItemClickLister(){
            @Override
            public void OnItemClick(String Item) {
                onItemSelected(Item);
            }
        });
        
        
        homeHorizontalRec.setAdapter(homeCevicheAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);

        //Vertical RecyclerView

        //Para los ceviches
        homeVerModelList = new ArrayList<>();

        //Este lado era la imagenes de los ceviches

        homeVerAdapter = new HomeVerAdapter(getActivity(),homeVerModelList);
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));



        return root;
    }

    private void onItemSelected(String item) {

        Toast.makeText(requireActivity(), item, Toast.LENGTH_SHORT).show();
        System.out.printf("DelPrada");
    }

    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {

        homeVerAdapter = new HomeVerAdapter(getContext(), list);
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);
    }
}