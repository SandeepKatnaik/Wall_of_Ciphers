package com.example.ciphershield;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//public class MainActivity extends AppCompatActivity
//{
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // MainActivity to SlideshowActivity
//        // It is explicit intent which specify target component
//        Intent intent = new Intent(MainActivity.this, SlideshowActivity.class);
//        startActivity(intent);
//        finish();
//    }
//
//}


public class MainActivity extends AppCompatActivity
{
    EdActivity edActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new DemoActivity();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    //onclick encrypt and decrypt button
    public void goToEncryption(View view)
    {
        edActivity = new EdActivity();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.container, edActivity);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    // onclick cipher details
    public void open_cipher(View view)
    {
        Intent intent = new Intent(MainActivity.this, CipherActivity.class);
        startActivity(intent);
    }

    // onclick 5 buttons on edactivity
    public void encryptionButtonClick(View view)
    {
        try {
            switch (view.getId()) {
                case R.id.btn_switch:
                    edActivity.switchAlgo(view);
                    break;
                case R.id.btn_encrypt:
                    edActivity.encrypt(view);
                    break;
                case R.id.btn_decrypt:
                    edActivity.decrypt(view);
                    break;
                case R.id.btn_copy:
                    edActivity.copyToClipboard(view);
                    break;
                case R.id.btn_reset:
                    edActivity.reset(view);
                    break;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
