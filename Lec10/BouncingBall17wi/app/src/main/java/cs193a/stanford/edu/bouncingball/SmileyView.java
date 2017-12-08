/*
 * CS 193A, Marty Stepp
 * This custom view draws a few shapes and colors on the screen.
 * This file does NOT use the Stanford Android library, unlike BouncingBallView.
 */

package cs193a.stanford.edu.bouncingball;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.*;

public class SmileyView extends View {
    // required constructor
    public SmileyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /*
     * This method draws on the view.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // drawing code;
        Paint paint = new Paint();
        paint.setARGB(255, 255, 80, 255);   // purple
        paint.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.BOLD));
        paint.setTextSize(50);
        canvas.drawOval(new RectF(50, 80, 200, 300), paint);
        canvas.drawText("CS 193A is great", 100, 400, paint);
    }
}
