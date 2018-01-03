package app.hoot.activity;
/**
 * Created by Robert Alexander on 18/10/2017.
 */

import app.hoot.R;
import app.hoot.activity.Login;
import app.hoot.activity.Signup;
import app.hoot.main.HootApp;
import app.hoot.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import static app.hoot.helpers.LogHelpers.info;

public class Welcome extends AppCompatActivity implements View.OnClickListener, Callback<List<User>> {
    private HootApp app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        app = (HootApp) getApplication();

        //Removed on click listener temporarily in order to use login service check method
/*        final Button loginButton = (Button) findViewById(R.id.welcomeLogin);
        final Button signupButton = (Button) findViewById(R.id.welcomeSignup);

        loginButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);*/

    }

    @Override
    public void onResume()
    {
        super.onResume();
        app.currentUser = null;
        Call<List<User>> call1 = (Call<List<User>>) app.hootService.getAllUsers();
        call1.enqueue(this);

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

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        serviceAvailableMessage();
        app.users = response.body();
        app.hootServiceAvailable = true;
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        app.hootServiceAvailable = false;
        serviceUnavailableMessage();
    }
    public void loginPressed (View view)
    {
        if (app.hootServiceAvailable)
        {
            startActivity (new Intent(this, Login.class));
        }
        else
        {
            serviceUnavailableMessage();
        }
    }

    public void signupPressed (View view)
    {
        if (app.hootServiceAvailable)
        {
            startActivity (new Intent(this, Signup.class));
        }
        else
        {
            serviceUnavailableMessage();
        }
    }

    void serviceUnavailableMessage()
    {
        Toast toast = Toast.makeText(this, "Donation Service Unavailable. Try again later", Toast.LENGTH_LONG);
        toast.show();
    }

    void serviceAvailableMessage()
    {
        Toast toast = Toast.makeText(this, "Donation Contacted Successfully", Toast.LENGTH_LONG);
        toast.show();
    }

}