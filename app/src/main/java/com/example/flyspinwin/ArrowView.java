package com.example.flyspinwin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class ArrowView extends View {

    private Paint paint;
    private float arrowSize, arrowOffset, rotation;

    public ArrowView(Context context) {
        super(context);
        init();
    }

    public ArrowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArrowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        arrowSize = 75;
        arrowOffset = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Calculate the size and center of the view
        float size = Math.min(getWidth(), getHeight());
        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;

        // Draw the arrow
        Path path = new Path();
        path.moveTo(centerX, centerY - size / 2f + arrowOffset);
        path.lineTo(centerX + arrowSize / 2f, centerY - size / 2f + arrowOffset + arrowSize);
        path.lineTo(centerX - arrowSize / 2f, centerY - size / 2f + arrowOffset + arrowSize);
        path.close();
        canvas.rotate(rotation, centerX, centerY);
        canvas.drawPath(path, paint);
    }
}

