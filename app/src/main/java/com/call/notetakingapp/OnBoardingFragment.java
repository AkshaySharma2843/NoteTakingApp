package com.call.notetakingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class OnBoardingFragment extends Fragment {

    private ArrayList<String> text;
    private ArrayList<Integer> images;
    TextView textView;
    ImageView imageView;
    Button button;
    private int position;


    public OnBoardingFragment(int position) {
        this.position = position;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_on_boarding, container, false);
        return view;
    }

    private void createData() {
        if (text == null && images == null) {
                //Strings Defined
                text = new ArrayList<String>();
                text.add("First Page");
                text.add("Second Page");
                text.add("Third Page");

                //Images Defined
                images = new ArrayList<>();
                images.add(R.drawable.undraw_notes1_cf55);
                images.add(R.drawable.undraw_add_notes_re_ln36);
                images.add(R.drawable.undraw_taking_notes_re_bnaf);
            }
    }

    private void addElementsAccToPosition() {
        if(text!=null && images!=null){
            String textToDraw = text.get(position);
            Integer imageToDraw = images.get(position);

            textView.setText(textToDraw);
            imageView.setImageResource(imageToDraw);
        }
        setButtonVisibility();

    }

    private void initViews(View view) {
        textView = view.findViewById(R.id.tv_viewText);
        imageView = view.findViewById(R.id.iv_viewImage);
        button = view.findViewById(R.id.btn_next);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createData();
        initViews(view);
        addElementsAccToPosition();

    }

    private void setButtonVisibility() {
        if(position==2){
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(view -> {
                Intent intent = new Intent(getContext(),Login.class);
                startActivity(intent);
            });
        }
        else{
            button.setVisibility(View.GONE);

        }

    }
}