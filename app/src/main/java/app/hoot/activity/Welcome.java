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

public class Welcome extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


    }
    public void loginPressed (View view)
    {
        startActivity (new Intent(this, Login.class));
    }
    public void signupPressed(View view) {
        startActivity(new Intent(this, Signup.class));
    }
}