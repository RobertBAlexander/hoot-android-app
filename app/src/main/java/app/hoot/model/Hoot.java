package app.hoot.model;
/**
 * Created by Robert Alexander on 21/10/2017.
 */
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.io.Serializable;

//Core Reference: lab 4a S Drohan
public class Hoot implements Serializable {
    public Long hootId;
    public String hootContent;
    public String hashtag;
    public String fullDate;
    public Long userId;

    private static final String JSON_HOOT_ID             = "hootId"            ;
    private static final String JSON_HOOT_CONTENT             = "hootContent"            ;
    private static final String JSON_HASHTAG             = "hashtag"            ;
    private static final String JSON_FULL_DATE          = "fullDate"            ;
    private static final String JSON_USER_ID             = "userId"            ;

    public Hoot() {}

    public Hoot(String hootContent, String hashtag, String fullDate, Long userId)
    {
        this.hootContent = hootContent;
        this.hashtag = hashtag;
        this.fullDate = fullDate;
        this.userId = userId;
        this.hootId = unsignedLong();
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
        userId = json.getLong(JSON_USER_ID);
        hootContent = json.getString(JSON_HOOT_CONTENT );
        hashtag = json.getString(JSON_HASHTAG);
        fullDate = json.getString(JSON_FULL_DATE);
        hootId = json.getLong(JSON_HOOT_ID);
    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();
        json.put(JSON_USER_ID, Long.toString(userId));
        json.put(JSON_HOOT_CONTENT , hootContent);
        json.put(JSON_HASHTAG, hashtag);
        json.put(JSON_FULL_DATE, fullDate);
        json.put(JSON_HOOT_ID, hootId);
        return json;
    }

    @Override
    public String toString() {
        return "Hoot [content=" + hootContent
                + ", hashtag =" + hashtag + ", full date=" + fullDate + ", userId=" + userId
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

    public void setFullDate(String fullDate)
    {
        this.fullDate = fullDate;
    }

    public String getFullDate()
    {
        return fullDate;
    }

}
