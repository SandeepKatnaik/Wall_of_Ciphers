package com.example.ciphershield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;




public class DemoActivity extends Fragment
{


    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_demo, container, false);

       // ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        //getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        return view;
    }


}