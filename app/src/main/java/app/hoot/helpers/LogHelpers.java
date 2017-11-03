package app.hoot.helpers;

/**
 * Created by Robert Alexander on 25/10/2017.
 */

import android.util.Log;

public class LogHelpers {
    public static void info(Object parent, String message)
    {
        Log.i(parent.getClass().getSimpleName(), message);
    }
}
