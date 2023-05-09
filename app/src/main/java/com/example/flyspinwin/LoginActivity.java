package com.example.flyspinwin;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 42;

    TextView welcometxtview;
    EditText emailTxt, passwordTxt, usernameTxt;
    ImageButton bird_icon_btn;
    MediaPlayer mediaPlayer;
    ImageView spriteView;

    ImageView spriteView1;
    ImageView spriteView2;
    AnimationDrawable animation;
    AnimationDrawable animation1;
    AnimationDrawable animation2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTxt = findViewById(R.id.emailtxt);
        passwordTxt = findViewById(R.id.passwordtxt);
        usernameTxt = findViewById(R.id.usernametxt);
        bird_icon_btn = findViewById(R.id.bird_icon_btn);
        welcometxtview = findViewById(R.id.welcometxtview);
        mediaPlayer = MediaPlayer.create(this, R.raw.birdistheword);

        spriteView = findViewById(R.id.animationview);
        spriteView1 = findViewById(R.id.animationview1);


        animation = (AnimationDrawable) spriteView.getDrawable();
        animation1 = (AnimationDrawable) spriteView1.getDrawable();

        spriteView.setVisibility(View.GONE);
        animation.start();
        animation1.start();


        // Set a click listener on the ImageView
        spriteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Create animation for animation view
                ObjectAnimator animX = ObjectAnimator.ofFloat(spriteView, "translationX", 0f, 400f);
                animX.setDuration(5000);
                animX.setRepeatMode(ValueAnimator.REVERSE);
                animX.setRepeatCount(ValueAnimator.INFINITE);

                ObjectAnimator animY = ObjectAnimator.ofFloat(spriteView, "translationY", 0f, 400f);
                animY.setDuration(5000);
                animY.setRepeatMode(ValueAnimator.REVERSE);
                animY.setRepeatCount(ValueAnimator.INFINITE);

                AnimatorSet animSetXY = new AnimatorSet();
                animSetXY.playTogether(animX, animY);
                animSetXY.start();


            }
        });


        spriteView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Create animation for second animation view
                ObjectAnimator animX2 = ObjectAnimator.ofFloat(spriteView1, "translationX", 0f, 800f);
                animX2.setDuration(5000);
                animX2.setRepeatMode(ValueAnimator.REVERSE);
                animX2.setRepeatCount(ValueAnimator.INFINITE);

                ObjectAnimator animY2 = ObjectAnimator.ofFloat(spriteView1, "translationY", 0f, 800f);
                animY2.setDuration(5000);
                animY2.setRepeatMode(ValueAnimator.REVERSE);
                animY2.setRepeatCount(ValueAnimator.INFINITE);

                AnimatorSet animSetXY2 = new AnimatorSet();
                animSetXY2.playTogether(animX2, animY2);
                animSetXY2.start();


            }
        });








// Start the animation


        welcometxtview.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = true;

            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    welcometxtview.setTextColor(ContextCompat.getColor(LoginActivity.this, R.color.blue));
                } else {
                    welcometxtview.setTextColor(ContextCompat.getColor(LoginActivity.this, R.color.coral));
                }
                isClicked = !isClicked;
            }
        });


        welcometxtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);

                } else {
                    mediaPlayer.start();
                    spriteView.setVisibility(View.VISIBLE);

                }
            }
        });


        bird_icon_btn.setOnClickListener(this::onClick2);
    }

    private void onClick2(View view) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);


            animation.setEnterFadeDuration(200);
            animation.setExitFadeDuration(6000);

        }





    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
            } else {
                // Permission denied
                {
                    super.onStop();
                    if (mediaPlayer != null) {
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                }
            }
        }
    }
}


