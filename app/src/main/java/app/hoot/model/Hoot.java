package app.hoot.model;
/**
 * Created by Robert Alexander on 21/10/2017.
 */
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Random;
import java.io.Serializable;

//Core Reference: lab 4a S Drohan
public class Hoot implements Serializable {
    public Long hootId;
    public String hootContent;
    public String hashtag;
    public Long date;
    public Long userId;
    public String contact;

    private static final String JSON_HOOT_ID             = "hootId"            ;
    private static final String JSON_HOOT_CONTENT             = "hootContent"            ;
    private static final String JSON_HASHTAG             = "hashtag"            ;
    private static final String JSON_FULL_DATE          = "date"            ;
    private static final String JSON_USER_ID             = "userId"            ;
    private static final String JSON_CONTACT = "contact";

    public Hoot() {
        hootId = unsignedLong();
        date = new Date().getTime();
        contact = "N/A";
    }

/*    public Hoot(String hootContent, String hashtag, String fullDate, Long userId)
    {
        this.hootContent = hootContent;
        this.hashtag = hashtag;
        this.fullDate = fullDate;
        this.userId = userId;
        this.hootId = unsignedLong();
    }*/

    private Long unsignedLong() {
        long rndVal = 0;
        do{
            rndVal = new Random().nextLong();
        }
        while (rndVal <= 0);
        return rndVal;
    }

    public Hoot(JSONObject json) throws JSONException{
        userId = json.getLong(JSON_USER_ID);
        hootContent = json.getString(JSON_HOOT_CONTENT );
        hashtag = json.getString(JSON_HASHTAG);
        date = json.getLong(JSON_FULL_DATE);
        hootId = json.getLong(JSON_HOOT_ID);
        contact = json.getString(JSON_CONTACT);

    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();
        json.put(JSON_USER_ID, Long.toString(userId));
        json.put(JSON_HOOT_CONTENT , hootContent);
        json.put(JSON_HASHTAG, hashtag);
        json.put(JSON_FULL_DATE, date);
        json.put(JSON_HOOT_ID, hootId);
        json.put(JSON_CONTACT, contact);
        return json;
    }

    @Override
    public String toString() {
        return "Hoot [content=" + hootContent
                + ", hashtag =" + hashtag + ", full date=" + date + ", userId=" + userId
                + ", hootId =" + hootId + "]";
    }

    public void setHootContent(String hootContent)
    {
        this.hootContent = hootContent;
    }

    public String getHootContent()
    {
        return hootContent;
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
        return fullDate();
    }

    public String fullDate()
    {
        String dateFormat = "EEE dd MMM yyyy H:mm:ss";
        return android.text.format.DateFormat.format(dateFormat, date).toString();
    }

}
