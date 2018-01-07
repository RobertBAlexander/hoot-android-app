package app.hoot.main;
/**
 * Created by Robert Alexander on 18/10/2017.
 */
import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.hoot.model.User;
import app.hoot.model.Hoot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.hoot.model.User;
import app.hoot.model.Hoot;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import app.hoot.model.Chronology;
import app.hoot.model.ChronologySerializer;

import static app.hoot.helpers.LogHelpers.info;

public class HootApp extends Application {
    //public List<User> users = new ArrayList<User>();
    //public List<Hoot> hootList = new ArrayList<Hoot>();
    public HootService hootService;
    public boolean         hootServiceAvailable = false;
    public String          service_url  = "https://hoothoot-web.herokuapp.com/";   // Standard Emulator IP Address
    //public String          service_url  = "https://twitter-web.herokuapp.com/";   // Standard Emulator IP Address

    public static User             currentUser;
    public List <Hoot>  hoots    = new ArrayList<Hoot>();
    public List <User>      users        = new ArrayList<User>();

    protected static HootApp app;

    private static final String FILENAMEU = "users.json";
    private static final String FILENAMEH = "hoots.json";
    public Chronology chronology;

    @Override
    public void onCreate()
    {
        super.onCreate();
        Gson gson = new GsonBuilder().create();
        //app = this;
        ChronologySerializer serializer = new ChronologySerializer(this, FILENAMEU, FILENAMEH);
        chronology = new Chronology(serializer);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(service_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        hootService = retrofit.create(HootService.class);


        Log.v("Hoot", "Hoot App Started");
    }


/*    @Override
    public void onCreate()
    {
        super.onCreate();
        app = this;
        ChronologySerializer serializer = new ChronologySerializer(this, FILENAMEU, FILENAMEH);
        chronology = new Chronology(serializer);
        info(this, "Hoot Hoot App Started");
    }*/

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
        for (User user : users)
        {
            if (user.email.equals(email) && user.password.equals(password))
            {
                currentUser = user;
                return true;
            }

        }
        return false;
    }

    public static HootApp getApp() {
        return app;
    }

    public static String getCurrentUser()
    {
        return currentUser._id;
    }

}
