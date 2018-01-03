package app.hoot.model;
/**
 * Created by Robert Alexander on 21/10/2017.
 */
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Random;
import java.io.Serializable;

import app.hoot.main.HootApp;

//Core Reference: lab 4a S Drohan
public class Hoot implements Serializable {
    //public Long hootId;
    public String _id;
    public String hootmain;
    public String hashtag;
    public String date;
    public String hooter;
    //public Long userId;
    public String contact;

    //private static final String JSON_HOOT_ID             = "hootId"            ;
    private static final String JSON_HOOT_CONTENT             = "hootmain"            ;
    //private static final String JSON_HASHTAG             = "hashtag"            ;
    private static final String JSON_FULL_DATE          = "date"            ;
    //private static final String JSON_USER_ID             = "userId"            ;
    private static final String JSON_CONTACT = "contact";

    public Hoot() {
        //hootId = unsignedLong();
        //this.date = date;
        this.hootmain = "";
        this.hashtag = "";
        contact = "N/A";
    }

    public Hoot(String hootmain, String date)// String hashtag,
    {
        this.hootmain = hootmain;
        //this.hashtag = hashtag;
        this.date = date;
        hooter = HootApp.getCurrentUser();
        //this.userId = userId;
        //this.hootId = unsignedLong();
    }

    private Long unsignedLong() {
        long rndVal = 0;
        do{
            rndVal = new Random().nextLong();
        }
        while (rndVal <= 0);
        return rndVal;
    }

    public Hoot(JSONObject json) throws JSONException{
       // userId = json.getLong(JSON_USER_ID);
        hootmain = json.getString(JSON_HOOT_CONTENT );
        //hashtag = json.getString(JSON_HASHTAG);
        //date = json.getString(JSON_FULL_DATE);
        //hootId = json.getLong(JSON_HOOT_ID);
        contact = json.getString(JSON_CONTACT);

    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();
        //json.put(JSON_USER_ID, Long.toString(userId));
        json.put(JSON_HOOT_CONTENT , hootmain);
        //json.put(JSON_HASHTAG, hashtag);
        //json.put(JSON_FULL_DATE, String(date));
        //json.put(JSON_HOOT_ID, Long.toString(hootId));
        json.put(JSON_CONTACT, contact);
        Log.i(this.getClass().getSimpleName(), "Hoot is created in JSON ");
        return json;
    }

    @Override
    public String toString() {
        return "Hoot main=" + hootmain
                + ", hashtag =" + hashtag + ", full date=" + date //+ ", userId=" + userId
                + ", hootId =" + "";
    }

    public void setHootContent(String hootmain)
    {
        this.hootmain = hootmain;
    }

    public String getHootContent()
    {
        return hootmain;
    }

    public void setHashtag(String hashtag)
    {
        this.hashtag = hashtag;
    }

    public String getHashtag()
    {
        return hashtag;
    }

    public String getFullDate()
    {
        return "this date";
    }

/*    public String fullDate()
    {
        String dateFormat = "EEE dd MMM yyyy H:mm:ss";
        return android.text.format.DateFormat.format(dateFormat, date).toString();
    }*/

}
