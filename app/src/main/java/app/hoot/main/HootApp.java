package app.hoot.main;
/**
 * Created by Robert Alexander on 18/10/2017.
 */
import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.hoot.model.User;
import app.hoot.model.Hoot;

public class HootApp extends Application {
    public List<User> users = new ArrayList<>();
    public List<Hoot> hootList = new ArrayList<Hoot>();
    protected static HootApp app;


    @Override
    public void onCreate()
    {
        super.onCreate();
        app = this;
        Log.v("Hoot", "Hoot Hoot App Started");
    }

    public void newUser(User user)
    {
        users.add(user);
    }


    public boolean validUser (String email, String password) {
        for (User user : users)
        {
            if (user.email.equals(email) && user.password.equals(password))
            {
                return true;
            }
        }
        return false;
    }

    public static HootApp getApp() {
        return app;
    }

}
