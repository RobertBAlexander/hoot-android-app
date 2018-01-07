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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginButtonPressed (View view)
    {
        final HootApp app = (HootApp) getApplication();

        TextView email     = (TextView)  findViewById(R.id.loginEmail);
        TextView password  = (TextView)  findViewById(R.id.loginPassword);

        String userEmail = email.getText().toString();
        String userPassword = email.getText().toString();
        if(userPassword.length() == 0 || userEmail.length() == 0) {
            Toast toast = Toast.makeText(Login.this, "Email and Password must contain characters", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {



        User user = new User(null, null, email.getText().toString(), password.getText().toString());

        Call<User> call = (Call<User>) app.hootService.authenticate(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                // Currently logs in a User but issue with
                // java.lang.NullPointerException: Attempt to read from field 'java.lang.String coady.mytweetapp.model.User.email' on a null object reference
                // when user enters correct email but wrong password
                if (app.validUser(user.email, user.password)) {
                    startActivity(new Intent(Login.this, Timeline.class));
                } else {
                    Toast toast = Toast.makeText(Login.this, "Invalid Credentials 1", Toast.LENGTH_SHORT);
                    toast.show();
                }
                //startActivity (new Intent(Login.this, Welcome.class));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast toast = Toast.makeText(Login.this, "Invalid Credentials: Incorrect e-mail", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        }

/*        if (app.validUser(email.getText().toString(), password.getText().toString()))
        {
            startActivity (new Intent(this, Timeline.class));
        }
        else
        {
            Toast toast = Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT);
            toast.show();
        }*/
    }
}