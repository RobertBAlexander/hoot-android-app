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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity implements Callback<User> {
    //private Button signupRegisterButton;
    //private Chronology chronology;
    public HootApp app;
    //private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Long userId = (Long) (53453458L);
        //Long userId = (Long) getActivity().getIntent().getSerializableExtra(EXTRA_USER_ID);
        app = (HootApp) getApplication();
        //chronology = app.chronology;
        //Removed as may not be needed for future retrieval of user
        //user = chronology.getUser();

        //signupRegisterButton = (Button) findViewById(R.id.signupRegisterButton);
    }

    public void signupPressed (View view)
    {
        String firstName = ((TextView)  findViewById(R.id.firstName)).getText().toString();
        String lastName  = ((TextView)  findViewById(R.id.lastName)).getText().toString();
        String email     = ((TextView)  findViewById(R.id.signupEmail)).getText().toString();
        String password  = ((TextView)  findViewById(R.id.signupPassword)).getText().toString();
        //Long userId = (294274287L);

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
            //app.addUser(user);
            HootApp app = (HootApp) getApplication();
            Call<User> call = (Call<User>) app.hootService.createUser(user);
            call.enqueue(this);
            //user.email = email.toString();
            //Toast.makeText(this, "Successful creation of: " + user.email, Toast.LENGTH_LONG).show();
            //chronology.saveUsers();
            //startActivity(new Intent(this, Welcome.class));
        }
    }

/*    @Override
    protected void onPause() {
        super.onPause();
        app.chronology.saveUsers();
    }*/

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        app.users.add(response.body());
        startActivity(new Intent(this, Welcome.class));
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        app.hootServiceAvailable = false;
        Toast toast = Toast.makeText(this, "Hoot Service Unavailable. Try again later", Toast.LENGTH_LONG);
        toast.show();
        startActivity (new Intent(this, Welcome.class));
    }
}
