package com.example.flyspinwin;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class SecondAnimation extends AppCompatActivity implements View.OnClickListener {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        AnimationDrawable animation1;

    }

    public void onClick(View view) {
        if (view.getId() == R.id.animationview) {
            // Create animation for second animation view
            ImageView animation1 = new ImageView(this);
            animation1.setImageResource(R.drawable.boomsprite_animation2);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            animation1.setLayoutParams(layoutParams);


            ObjectAnimator animX = ObjectAnimator.ofFloat(animation1, "translationX", 0f, 400f);
            animX.setDuration(5000);
            animX.setRepeatMode(ValueAnimator.REVERSE);
            animX.setRepeatCount(ValueAnimator.INFINITE);
            ObjectAnimator animY = ObjectAnimator.ofFloat(animation1, "translationY", 0f, 400f);
            animY.setDuration(5000);
            animY.setRepeatMode(ValueAnimator.REVERSE);
            animY.setRepeatCount(ValueAnimator.INFINITE);
            AnimatorSet animSetXY = new AnimatorSet();
            animSetXY.playTogether(animX, animY);
            animSetXY.start();
        }
    }
}
