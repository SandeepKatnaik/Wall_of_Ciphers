package com.example.ciphershield;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.nio.charset.StandardCharsets;

public class EdActivity extends Fragment
{
    public String message;        // It stores output of encryption and decryption
    public String key;            // It stores key value
    public Button btn_switch;     // btn_switch (4 algorithms switches button)
    public Button btn_encrypt;    // btn_encrypt (Encrypt button)
    public Button btn_decrypt;    // btn_decrypt (Decrypt button)
    private TextView text_ans;     // text_ans    (textview which display output text )
    public EditText edit_input;   // edit_input  (edit view for input text)
    public EditText edit_key;     // edit_key    (edittext for input key)

    public View view;             // view object and view class


    @SuppressLint("UseRequireInsteadOfGet")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //Layout Inflation  is present for each activity by default and use eto convert (.xml) to java usable format
        view = inflater.inflate(R.layout.activity_ed, container, false);
        // To hide tool bar on android app
        //((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().hide();
        // For activity layout that should be displayed on screen
        //Objects.requireNonNull(getActivity()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btn_switch = view.findViewById(R.id.btn_switch);
        btn_encrypt= view.findViewById(R.id.btn_encrypt);
        btn_decrypt= view.findViewById(R.id.btn_decrypt);
        text_ans= view.findViewById(R.id.text_ans);  //?
        edit_input= view.findViewById(R.id.edit_input);
        edit_key= view.findViewById(R.id.edit_key);

        return view;
    }


    // Encryption function
    public void encrypt(View view) throws Exception
    {

        // If user is not putting any text for encryption
        if (edit_input.length() == 0)   //input data
        {
            Toast.makeText(view.getContext(), "Enter a message to Encrypt", Toast.LENGTH_SHORT).show();
            return;
        }
        message = String.valueOf(edit_input.getText());  //message is used to store input
        key = String.valueOf(edit_key.getText());  //key is used to store input
        String Algorithm = String.valueOf(btn_switch.getText()); // switch is button
        switch (Algorithm)
        {
            case "Advanced Encryption Standard":
                com.example.ciphershield.AES aes = new com.example.ciphershield.AES();  // AES class
                // AES Encrypt is method from AES class
                String enc = aes.AESencrypt(key.getBytes(StandardCharsets.UTF_16LE), message.getBytes(StandardCharsets.UTF_16LE));
                // answer=output textview
                text_ans.setText(enc);
                break;

            case "Triple Data Encryption Standard":
                com.example.ciphershield.DES des = new com.example.ciphershield.DES();
                String encData = des.encrypt(key.getBytes(StandardCharsets.UTF_16LE), message.getBytes(StandardCharsets.UTF_16LE));
                text_ans.setText(encData);
                break;

            case "Caesar Cipher":
                if (key.isEmpty())
                {
                    Toast.makeText(view.getContext(), "Enter a key to Encrypt", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (key.length() > 26)
                {
                    Toast.makeText(view.getContext(), "The Key must be less than 26 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                com.example.ciphershield.Caesarcipher c = new com.example.ciphershield.Caesarcipher();
                text_ans.setText(c.caesarcipherEnc(message, Integer.parseInt(key)));
                break;

            case "Vigenere Cipher":
                if (edit_key.length() == 0)
                {
                    Toast.makeText(view.getContext(), "Enter a key to Encrypt", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (char i : message.toUpperCase().toCharArray())
                {
                    if (i < 'A' || i > 'Z') {
                        Toast.makeText(view.getContext(), "Only Letters are allowed here", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                for (char i : key.toUpperCase().toCharArray()) {
                    if (i < 'A' || i > 'Z') {
                        Toast.makeText(view.getContext(), "Only Letters are allowed here", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                com.example.ciphershield.Vigenere v = new com.example.ciphershield.Vigenere();
                text_ans.setText(v.Vigenereencrypt(message, key));
                break;
        }
    }

    // Decryption function
    public void decrypt(View view) {
        if (edit_input.length() == 0)
        {
            Toast.makeText(view.getContext(), "Enter a message to Decrypt", Toast.LENGTH_SHORT).show();
            return;
        }
        message = String.valueOf(edit_input.getText());
        key = String.valueOf(edit_key.getText());
        String SwitchValue = btn_switch.getText().toString();
        switch (SwitchValue)
        {
            case "Advanced Encryption Standard":
                com.example.ciphershield.AES aes = new com.example.ciphershield.AES();
                try {
                    String decData = aes.AESdecrypt(key, Base64.decode(message.getBytes(StandardCharsets.UTF_16LE), Base64.DEFAULT));
                    text_ans.setText(decData);
                } catch (Exception e) {
                    Toast.makeText(view.getContext(), "Your key is wrong", Toast.LENGTH_SHORT).show();
                }
                break;

            case "Data Encryption Standard":
                com.example.ciphershield.DES des = new com.example.ciphershield.DES();
                try {
                    String decData = des.decrypt(key, Base64.decode(message.getBytes(StandardCharsets.UTF_16LE), Base64.DEFAULT));
                    text_ans.setText(decData);
                } catch (Exception e) {
                    Toast.makeText(view.getContext(), "Your key is wrong", Toast.LENGTH_SHORT).show();
                }
                break;

            case "Caesar Cipher":
                if (edit_key.length() == 0) {
                    Toast.makeText(view.getContext(), "Enter a key", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(key) >= 26) {
                    Toast.makeText(view.getContext(), "The Key must be less than 26 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                com.example.ciphershield.Caesarcipher c = new com.example.ciphershield.Caesarcipher();
                text_ans.setText(c.caesarcipherDec(message, Integer.parseInt(key)));
                break;

            case "Vigenere Cipher":
                if (edit_key.length() == 0) {
                    Toast.makeText(view.getContext(), "Enter a key to Decrypt", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (char i : message.toUpperCase().toCharArray()) {
                    if (i < 'A' || i > 'Z') {
                        Toast.makeText(view.getContext(), "Only Letters are allowed here", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                for (char i : key.toUpperCase().toCharArray()) {
                    if (i < 'A' || i > 'Z') {
                        Toast.makeText(view.getContext(), "Only Letters are allowed here", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                com.example.ciphershield.Vigenere v = new com.example.ciphershield.Vigenere();
                text_ans.setText(v.Vigeneredecrypt(message, key));
                break;

        }

    }

    // Funtion to switch algorithms
    @SuppressLint("SetTextI18n")
    public void switchAlgo(View view)
    {
        reset(null);
        String SwitchValue = btn_switch.getText().toString();
        switch (SwitchValue)
        {
            case "Advanced Encryption Standard":
                btn_switch.setText("Triple Data Encryption Standard");
                break;
            case "Triple Data Encryption Standard":
                edit_key.setInputType(InputType.TYPE_CLASS_NUMBER);
                btn_switch.setText("Caesar Cipher");
                break;
            case "Caesar Cipher":
                edit_key.setInputType(InputType.TYPE_CLASS_TEXT);
                btn_switch.setText("Vigenere Cipher");
                break;
            case "Vigenere Cipher":
                edit_key.setVisibility(View.VISIBLE);
                text_ans.setVisibility(View.GONE);
                btn_switch.setText("Advanced Encryption Standard");
                break;
        }
    }


    // function to reset
    @SuppressLint("SuspiciousIndentation")
    public void reset(View view)
    {
        edit_input.setText("");
        edit_key.setText("");
        text_ans.setText("");
        if(view!=null)
            Toast.makeText(view.getContext(), "All data has been deleted", Toast.LENGTH_SHORT).show();
    }

    // function to copy
    public void copyToClipboard(View view)
    {
        String copyText = String.valueOf(text_ans.getText());
        if (text_ans.length() == 0) {
            Toast.makeText(view.getContext(), R.string.message_no_message_to_copy, Toast.LENGTH_SHORT).show();
            return;
        }

        android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData
                .newPlainText("Your message :", copyText);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(view.getContext(), R.string.message_copied, Toast.LENGTH_SHORT).show();
    }


}
