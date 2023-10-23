package com.example.ciphershield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CipherActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cipher);
    }

    public void open_aes(View view)
    {
        Intent intent = new Intent(CipherActivity.this,InfoAES.class);
        startActivity(intent);
    }

    public void open_des(View view)
    {
        Intent intent = new Intent(CipherActivity.this,InfoDES.class);
        startActivity(intent);
    }

    public void open_vig(View view)
    {
        Intent intent = new Intent(CipherActivity.this,InfoVig.class);
        startActivity(intent);
    }

    public void open_caesar(View view)
    {
        Intent intent = new Intent(CipherActivity.this,InfoCaesar.class);
        startActivity(intent);
    }
}