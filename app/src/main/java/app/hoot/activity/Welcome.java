package app.hoot.activity;
/**
 * Created by Robert Alexander on 18/10/2017.
 */

import app.hoot.R;
import app.hoot.activity.Login;
import app.hoot.activity.Signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static app.hoot.helpers.LogHelpers.info;

public class Welcome extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final Button loginButton = (Button) findViewById(R.id.welcomeLogin);
        final Button signupButton = (Button) findViewById(R.id.welcomeSignup);

        loginButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.welcomeLogin:
                info(this, "Login Pressed");
                startActivity(new Intent(this, Login.class));
                break;


            case R.id.welcomeSignup:
                info(this, "Signup Pressed");
                startActivity(new Intent(this, Signup.class));
                break;
        }
    }
}