package com.example.mycevicheriaapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.mycevicheriaapp.R;

public class CanastaFragment extends Fragment {


    private RadioGroup radioGroup;
    private ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.fragment_canasta, container, false);

        radioGroup = view.findViewById(R.id.radioGroup);
        RadioButton radioYape = view.findViewById(R.id.radioYape);
        RadioButton radioPlin = view.findViewById(R.id.radioPlin);
        imageView = view.findViewById(R.id.imagenPago);

        // Configurar listener para el RadioGroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Mostrar la imagen correspondiente según el botón seleccionado
                if (checkedId == R.id.radioYape) {
                    imageView.setImageResource(R.drawable.yape_image);
                } else if (checkedId == R.id.radioPlin) {
                    imageView.setImageResource(R.drawable.plin_image);
                }

                // Hacer visible la ImageView
                imageView.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

}