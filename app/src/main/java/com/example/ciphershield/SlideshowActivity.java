package com.example.ciphershield;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SlideshowActivity extends AppCompatActivity
{

    private int Splash_Screen = 3500;
    private ImageView BgImg;
    private Animation topAnimation, leftAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);


        BgImg = findViewById(R.id.bg1);



        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        leftAnimation = AnimationUtils.loadAnimation(this, R.anim.left_aimation);

        BgImg.setAnimation(topAnimation);

        TextView textView = findViewById(R.id.txt1);
        // In res->anim-> (Slide text_from_left.xml) is present for animation
        Animation slideTextAnimation = AnimationUtils.loadAnimation(this, R.anim.slidetext_from_left);
        textView.startAnimation(slideTextAnimation);


        // animation listener to stop animation in centre of activity
        slideTextAnimation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                // Animation start
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                // Animation end at centre of activity
                int parentWidth = getWindow().getDecorView().getWidth();
                int textViewWidth = textView.getWidth();
                int textViewPadding = textView.getPaddingLeft() + textView.getPaddingRight();
                float centerTranslation = (parentWidth - textViewWidth - textViewPadding) / 2f;
                textView.setX(centerTranslation);

            }
            @Override
            public void onAnimationRepeat(Animation animation)
            {
                // Animation repeat
            }
        });

        //splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the MainActivity
                Intent intent = new Intent(SlideshowActivity.this, MainActivity.class);
                startActivity(intent);

                // Close the SplashActivity
                finish();
            }
        }, Splash_Screen);
    }

//    // SlideshowActivity to DemoActivity
//    public void go_to_demo(View view)
//    {
//        Intent intent = new Intent(SlideshowActivity.this, DemoActivity.class);
//        startActivity(intent);
//    }
}