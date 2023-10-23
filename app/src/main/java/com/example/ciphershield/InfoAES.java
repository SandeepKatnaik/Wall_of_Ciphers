package com.example.ciphershield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class InfoAES extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_aes);

        TextView textView = findViewById(R.id.btn_aes);
        String text = "AES is symmetric block cipher.\n" +
                "Key size: (128/192/256) Bits\n" +
                "Block size: 128 Bits\n" +
                "Number of Rounds: (10/12/14)\n" +
                "For more details visit: http://surl.li/hgrqh";

        SpannableString spannableString = new SpannableString(text);

        // Find the start and end index of the link within the text
        int startIndex = text.indexOf("http://surl.li/hgrqh");
        int endIndex = startIndex + "http://surl.li/hgrqh".length();

        // Create a clickable span for the link
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view)
            {
                String url = "http://surl.li/hgrqh";
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
