package app.hoot.model;

/**
 * Created by Robert Alexander on 23/10/2017.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;

//Core Reference: lab 4a S Drohan
public class ChronologySerializer {
    private Context mContext;
    private String mFilenameU;
    private String mFilenameH;

    public ChronologySerializer(Context context, String filenameU, String filenameH)
    {
        mContext = context;
        mFilenameU = filenameU;
        mFilenameH = filenameH;
    }

    public void saveHoots(ArrayList<Hoot> hoots) throws JSONException, IOException
    {
        // build an array in JSON
        JSONArray array = new JSONArray();
        for (Hoot hoot : hoots)
            array.put(hoot.toJSON());

        // write the file to disk
        Writer writer = null;
        try
        {
            OutputStream out = mContext.openFileOutput(mFilenameH, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        }
        finally
        {
            if (writer != null)
                writer.close();
        }
    }

    public void saveUsers(ArrayList<User> users) throws JSONException, IOException
    {
        // build an array in JSON
        JSONArray array = new JSONArray();
        for (User user : users) {
            array.put(user.toJSON());
        }

        // write the file to disk
        Writer writer = null;
        try
        {
            OutputStream out = mContext.openFileOutput(mFilenameU, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        }
        finally
        {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<Hoot> loadHoots() throws IOException, JSONException
    {
        ArrayList<Hoot> hoots = new ArrayList<Hoot>();
        BufferedReader reader = null;
        try
        {
            // open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(mFilenameH);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                // read file into a string
                // line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            // parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // build the array of residences from JSONObjects
            for (int i = 0; i < array.length(); i++)
            {
                hoots.add(new Hoot(array.getJSONObject(i)));
            }
        }
        catch (FileNotFoundException e)
        {
            // we will ignore this one, since it happens when we start fresh
        }
        finally
        {
            if (reader != null)
                reader.close();
        }
        return hoots;
    }

    public ArrayList<User> loadUsers() throws IOException, JSONException
    {
        ArrayList<User> users = new ArrayList<User>();
        BufferedReader reader = null;
        try
        {
            // open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(mFilenameU);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                // read file into a string
                // line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            // parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // build the array of residences from JSONObjects
            for (int i = 0; i < array.length(); i++)
            {
                users.add(new User(array.getJSONObject(i)));
            }
        }
        catch (FileNotFoundException e)
        {
            // we will ignore this one, since it happens when we start fresh
        }
        finally
        {
            if (reader != null)
                reader.close();
        }
        return users;
    }

}
