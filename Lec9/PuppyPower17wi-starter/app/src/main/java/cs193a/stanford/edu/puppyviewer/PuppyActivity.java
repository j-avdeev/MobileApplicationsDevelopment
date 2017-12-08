/*
 * CS 193A, Marty Stepp
 * This activity class demonstrates several useful libraries shown in class today.
 */

package cs193a.stanford.edu.puppyviewer;

import android.os.*;
import android.view.*;
import android.widget.*;
import stanford.androidlib.*;

public class PuppyActivity extends SimpleActivity {
    // constant for base web URL where image files are found
    private static final String WEBSITE_DIRECTORY = "http://www.martystepp.com/dogs/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puppy);
    }
}
