package com.example.jenek.smileyface16sp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jenek on 07.12.17.
 */

public class FaceView extends View {

    public FaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);

        Paint black = new Paint();
        black.setTypeface(font);
        black.setTextSize(100);

        Paint forestGreen = new Paint();
        forestGreen.setARGB(255,0,200,0);
        forestGreen.setStyle(Paint.Style.STROKE);
        forestGreen.setStrokeWidth(10);

        canvas.drawRect(10,20,90,270, black);
        canvas.drawOval(new RectF(80,10, 280,210),forestGreen);
        canvas.drawText("I like 193a", 150, 70, black);
    }
}
