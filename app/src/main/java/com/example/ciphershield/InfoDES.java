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

public class InfoDES extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_aes);

        TextView textView = findViewById(R.id.btn_aes);
        String text = "DES is symmetric block cipher.\n" +
                "Key size: 64 Bits\n" +
                "Block size: 64 Bits\n" +
                "Number of Rounds: 16\n" +
                "For more details visit: http://surl.li/hgtej";

        SpannableString spannableString = new SpannableString(text);

        // Find the start and end index of the link within the text
        int startIndex = text.indexOf("http://surl.li/hgtej");
        int endIndex = startIndex + "http://surl.li/hgtej".length();

        // Create a clickable span for the link
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view)
            {
                String url = "http://surl.li/hgtej";
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
