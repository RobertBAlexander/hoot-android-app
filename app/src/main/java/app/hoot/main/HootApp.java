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

import app.hoot.model.Chronology;
import app.hoot.model.ChronologySerializer;

import static app.hoot.helpers.LogHelpers.info;

public class HootApp extends Application {
    //public List<User> users = new ArrayList<User>();
    //public List<Hoot> hootList = new ArrayList<Hoot>();
    protected static HootApp app;

    private static final String FILENAMEU = "users.json";
    private static final String FILENAMEH = "hoots.json";
    public Chronology chronology;


    @Override
    public void onCreate()
    {
        super.onCreate();
        app = this;
        ChronologySerializer serializer = new ChronologySerializer(this, FILENAMEU, FILENAMEH);
        chronology = new Chronology(serializer);
        info(this, "Hoot Hoot App Started");
    }

    public void newUser(User user)
    {
        chronology.users.add(user);
    }

    public void addUser(User user) {
        chronology.users.add(user);
        Log.v("i/o", "User added: " + user);
    }

    public void addHoot(Hoot hoot) {
        chronology.hoots.add(hoot);
        Log.v("i/o", "Hoot added: " + hoot);
    }


    public boolean validUser (String email, String password) {
        for (User user : chronology.users)
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
