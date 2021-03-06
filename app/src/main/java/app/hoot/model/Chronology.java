package app.hoot.model;

/**
 * Created by Robert Alexander on 25/10/2017.
 */
import java.util.ArrayList;
import android.util.Log;
import static app.hoot.helpers.LogHelpers.info;

public class Chronology {
    public ArrayList<Hoot> hoots;
    public ArrayList<User> users;
    private ChronologySerializer serializer;

    public Chronology(ChronologySerializer serializer)
    {
        this.serializer = serializer;
        try
        {
            hoots = serializer.loadHoots();
            users = serializer.loadUsers();
        }
        catch (Exception e)
        {
            info(this, "Error loading users: " + e.getMessage());
            hoots = new ArrayList<Hoot>();
            users = new ArrayList<User>();
        }
    }

    public void addHoot(Hoot hoot)
    {
        hoots.add(hoot);
        this.saveHoots();
    }

    public void addUser(User user)
    {
        users.add(user);
        this.saveUsers();
    }

    //How should id work?
    public Hoot getHoot(String id) {
        Log.i(this.getClass().getSimpleName(), "Long parameter id: " + id);
        for (Hoot hoot : hoots) {
            if (id.equals(hoot._id)) {
                return hoot;
            }
        }
        return null;
    }

    public User getUser(String id) {
        Log.i(this.getClass().getSimpleName(), "Long parameter id: " + id);
        for (User user : users) {
            if (id.equals(user._id)) {
                return user;
            }
        }
        return null;
    }


    public boolean saveHoots()
    {
        try
        {
            serializer.saveHoots(hoots);
            info(this, "Hoots saved to file");
            return true;
        }
        catch (Exception e)
        {
            info(this, "Error saving hoots: " + e.getMessage());
            return false;
        }
    }

    public boolean saveUsers()
    {
        try
        {
            serializer.saveUsers(users);
            info(this, "Users saved to file");
            return true;
        }
        catch (Exception e)
        {
            info(this, "Error saving users: " + e.getMessage());
            return false;
        }
    }

    public void deleteHoot(Hoot hoot) {
        hoots.remove(hoot);
        saveHoots();
    }

    public void deleteUser(User user) {
        users.remove(user);
        saveUsers();
    }
}
