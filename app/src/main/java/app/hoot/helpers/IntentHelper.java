package app.hoot.helpers;

/**
 * Created by Robert Alexander on 20/10/2017.
 */

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;

public class IntentHelper {
    public static void startActivity (Activity parent, Class classname)
    {
        Intent intent = new Intent(parent, classname);
        parent.startActivity(intent);
    }

    public static void startActivityWithData (Activity parent, Class classname, String extraID, Serializable extraData)
    {
        Intent intent = new Intent(parent, classname);
        intent.putExtra(extraID, extraData);
        parent.startActivity(intent);
    }

    public static void startActivityWithDataForResult (Activity parent, Class classname, String extraID, Serializable extraData, int idForResult)
    {
        Intent intent = new Intent(parent, classname);
        intent.putExtra(extraID, extraData);
        parent.startActivityForResult(intent, idForResult);
    }
}
