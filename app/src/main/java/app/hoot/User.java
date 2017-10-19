package app.hoot;

/**
 * Created by Robert Alexander on 18/10/2017.
 */

public class User {
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}