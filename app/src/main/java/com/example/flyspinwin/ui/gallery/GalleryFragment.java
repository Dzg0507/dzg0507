package com.example.flyspinwin.ui.gallery;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.flyspinwin.ArrowView;
import com.example.flyspinwin.R;

import java.util.Random;

public class GalleryFragment extends Fragment {

    private View root;

    ArrowView arrowView;
    private com.example.flyspinwin.SpinToWinView spinToWinView;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_gallary, container, false);

        spinToWinView = (com.example.flyspinwin.SpinToWinView)


                root.findViewById(R.id.spin_to_win_view);




        // Add the arrow view to the layout
        FrameLayout spinToWinLayout = root.findViewById(R.id.spin_to_win_layout);
        arrowView = new ArrowView(getContext());
        // Set the layout parameters for the arrow view
        FrameLayout.LayoutParams arrowLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        arrowLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        arrowLayoutParams.topMargin = 30; // Adjust this value to position the arrow above the wheel
        arrowView.setLayoutParams(arrowLayoutParams);
        spinToWinLayout.addView(arrowView);

        Button spinButton = root.findViewById(R.id.spin_button);
        spinButton.setOnClickListener(view -> spinToWinView.startSpinning());

        return root;
    }

    private static class SpinToWinView extends View {

        // Define the colors for the slices of the wheel
        private final int[] sliceColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.CYAN};

        // Define the number of slices in the wheel
        private final int numSlices = sliceColors.length;

        // Define the angle of each slice in the wheel
        private final float sliceAngle = 360f / numSlices;

        // Define the current rotation of the wheel
        private float rotation = 0f;

        // Define the arrow view
        private ArrowView arrowView;

        public SpinToWinView(Context context) {
            super(context);
            init();
        }

        public SpinToWinView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public SpinToWinView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init();
        }

        private void init() {
            arrowView = new ArrowView(getContext());
            arrowView.setVisibility(View.GONE);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Calculate the center of the view
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            // Calculate the radius of the wheel
            int radius = Math.min(centerX, centerY);

            // Draw each slice of the wheel
            for (int i = 0; i < numSlices; i++) {
                Paint paint = new Paint();
                paint.setColor(sliceColors[i]);
                canvas.drawArc(centerX - radius, centerY - radius, centerX + radius, centerY + radius,
                        rotation + i * sliceAngle, sliceAngle, true, paint);
            }
        }

        public void startSpinning() {
            // Generate a random rotation value
            Random random = new Random();
            float randomRotation = 2520f + 360f * random.nextFloat();

            // Add "bumps" to the rotation
            float bump = 5f * (random.nextBoolean() ? 1 : -1);
            randomRotation += bump;

            // Animate the rotation of the wheel
            ObjectAnimator animator = ObjectAnimator.ofFloat(this, "rotation", rotation, rotation + randomRotation);
            animator.setDuration(6000);
            animator.setInterpolator(new DecelerateInterpolator());

            float finalRandomRotation = randomRotation;
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    // Update the value of rotation
                    rotation += finalRandomRotation;

                    // Calculate the current slice
                    int currentSlice = (int) (((rotation + sliceAngle / 2f + 60f) % 360) / sliceAngle);

                    // Display a message indicating which color won
                    String winningColor = "";
                    switch (currentSlice) {
                        case 0:
                            winningColor = "Red";
                            break;
                        case 1:
                            winningColor = "Blue";
                            break;
                        case 2:
                            winningColor = "Green";
                            break;
                        case 3:
                            winningColor = "Yellow";
                            break;
                        case 4:
                            winningColor = "Magenta";
                            break;
                        case 5:
                            winningColor = "Cyan";
                            break;
                    }
                    Toast.makeText(getContext(), "You won " + winningColor + "!!", Toast.LENGTH_SHORT).show();
                }
            });
            animator.start();
        }
    }
}





