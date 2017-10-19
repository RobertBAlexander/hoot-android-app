package app.hoot;
/**
 * Created by Robert Alexander on 18/10/2017.
 */
import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.hoot.Hoot;
import app.hoot.User;

public class HootApp extends Application {
    public List<User> users = new ArrayList<>();

    public void newUser(User user)
    {
        users.add(user);
    }

    public boolean validUser (String email, String password) {
                return true;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("Donate", "Donation App Started");
    }




}
