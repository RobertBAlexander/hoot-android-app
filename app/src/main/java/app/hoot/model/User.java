package app.hoot.model;

/**
 * Created by Robert Alexander on 18/10/2017.
 */
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;
import java.util.Random;

//Core Reference: lab 4a S Drohan
public class User {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public Long userId;

    private static final String JSON_USER_ID             = "userId"            ;
    private static final String JSON_FIRST_NAME             = "firstName"            ;
    private static final String JSON_LAST_NAME             = "lastName"            ;
    private static final String JSON_EMAIL            = "email"            ;
    private static final String JSON_PASSWORD             = "password"            ;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        userId = unsignedLong();
    }

    private Long unsignedLong() {
        long rndVal = 0;
        do{
            rndVal = new Random().nextLong();
        }
        while (rndVal <= 0);
        return rndVal;
    }

    public User(JSONObject json) throws JSONException{
        userId = json.getLong(JSON_USER_ID);
        firstName = json.getString(JSON_FIRST_NAME);
        lastName = json.getString(JSON_LAST_NAME);
        email = json.getString(JSON_EMAIL);
        password = json.getString(JSON_PASSWORD);
    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();
        json.put(JSON_USER_ID, Long.toString(userId));
        json.put(JSON_FIRST_NAME, firstName);
        json.put(JSON_LAST_NAME, lastName);
        json.put(JSON_EMAIL, email);
        json.put(JSON_PASSWORD , password);
        Log.i(this.getClass().getSimpleName(), "User is created in JSON ");
        return json;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

}