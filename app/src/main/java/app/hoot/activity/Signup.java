package app.hoot.activity;
/**
 * Created by Robert Alexander on 18/10/2017.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.hoot.main.HootApp;
import app.hoot.R;
import app.hoot.model.User;
import app.hoot.model.Chronology;

public class Signup extends AppCompatActivity {
    //private Button signupRegisterButton;
    //private Chronology chronology;
    public HootApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //signupRegisterButton = (Button) findViewById(R.id.signupRegisterButton);
    }

    public void signupPressed (View view)
    {
        String firstName = ((TextView)  findViewById(R.id.firstName)).getText().toString();
        String lastName  = ((TextView)  findViewById(R.id.lastName)).getText().toString();
        String email     = ((TextView)  findViewById(R.id.signupEmail)).getText().toString();
        String password  = ((TextView)  findViewById(R.id.signupPassword)).getText().toString();

        HootApp app = (HootApp) getApplication();

        if(email.isEmpty())
        {
            Toast.makeText(this, "You must provide a valid e-mail!", Toast.LENGTH_SHORT).show();
        }
        else if(password.isEmpty())
        {
            Toast.makeText(this, "You must have a password!", Toast.LENGTH_SHORT).show();
        }
        else {
            User user = new User(firstName, lastName, email, password);
            app.addUser(user);
            startActivity(new Intent(this, Welcome.class));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //app.chronology.saveUsers();
    }

}
