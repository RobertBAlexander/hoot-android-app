package app.hoot.model;

/**
 * Created by Robert Alexander on 25/10/2017.
 */
import java.util.ArrayList;
import android.util.Log;
import static app.hoot.helpers.LogHelpers.info;

public class Chronology {
    public ArrayList<Hoot> hoots;
    private ChronologySerializer serializer;

    public Chronology(ChronologySerializer serializer)
    {
        this.serializer = serializer;
        try
        {
            hoots = serializer.loadHoots();
        }
        catch (Exception e)
        {
            info(this, "Error loading hoots: " + e.getMessage());
            hoots = new ArrayList<Hoot>();
        }
    }

    public void addHoot(Hoot hoot)
    {
        hoots.add(hoot);
    }

    //How should id work?
    public Hoot getHoot(Long id) {
        Log.i(this.getClass().getSimpleName(), "Long parameter id: " + id);
        for (Hoot hoot : hoots) {
            if (id.equals(hoot.hootId)) {
                return hoot;
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

    public void deleteHoot(Hoot hoot) {
        hoots.remove(hoot);
        saveHoots();
    }
}
