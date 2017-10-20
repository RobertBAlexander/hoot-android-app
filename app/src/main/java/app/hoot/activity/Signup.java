package app.hoot.activity;
/**
 * Created by Robert Alexander on 18/10/2017.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import app.hoot.main.HootApp;
import app.hoot.R;
import app.hoot.model.User;

public class Signup extends AppCompatActivity {
    //private Button signupRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //signupRegisterButton = (Button) findViewById(R.id.signupRegisterButton);
    }

    public void signupPressed (View view)
    {
        TextView firstName = (TextView)  findViewById(R.id.firstName);
        TextView lastName  = (TextView)  findViewById(R.id.lastName);
        TextView email     = (TextView)  findViewById(R.id.signupEmail);
        TextView password  = (TextView)  findViewById(R.id.signupPassword);

        User user = new User (firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString());

        HootApp app = (HootApp) getApplication();
        app.newUser(user);

        startActivity (new Intent(this, Hoot.class));
    }
}
