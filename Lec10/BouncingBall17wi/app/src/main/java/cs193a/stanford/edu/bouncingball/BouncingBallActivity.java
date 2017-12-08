/*
 * CS 193A, Marty Stepp
 * This activity is basically just an empty shell to hold the BouncingBallView.
 */

package cs193a.stanford.edu.bouncingball;

import android.os.Bundle;
import stanford.androidlib.SimpleActivity;

public class BouncingBallActivity extends SimpleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BouncingBallView ball = new BouncingBallView(this, null);
        setContentView(ball);
    }
}
