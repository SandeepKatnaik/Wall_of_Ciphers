package com.example.ciphershield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class InfoCaesar extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_aes);

        TextView textView = findViewById(R.id.btn_aes);
        String text = "Caesar is monoalphabetic cipher.\n" +
                "Key size: 3/n shifts \n" +
                "It is stream cipher\n" +
                "It is version of shift cipher\n" +
                "For more details visit: http://surl.li/hgtup";

        SpannableString spannableString = new SpannableString(text);

        // Find the start and end index of the link within the text
        int startIndex = text.indexOf("http://surl.li/hgtup");
        int endIndex = startIndex + "http://surl.li/hgtup".length();

        // Create a clickable span for the link
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view)
            {
                String url = "http://surl.li/hgtup";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        };

        // Set the clickable span to the specific portion of the text
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the modified SpannableString to the TextView
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.TRANSPARENT);
    }

}