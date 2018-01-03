package app.hoot.activity;

/**
 * Created by Robert Alexander on 02/11/2017.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import app.hoot.R;
import app.hoot.fragments.ChronologyFragment;
import app.hoot.fragments.HootFragment;
import app.hoot.main.HootApp;
import app.hoot.model.Hoot;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddHoot extends AppCompatActivity implements Callback<Hoot> {
    public ActionBar actionBar;
    private HootApp app;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        actionBar = getSupportActionBar();
        app = (HootApp) getApplication();

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new HootFragment();
            manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }

    @Override
    public void onResponse(Call<Hoot> call, Response<Hoot> response) {
        Toast toast = Toast.makeText(this, "Hoot Accepteed", Toast.LENGTH_SHORT);
        toast.show();
        //app.newHoot(response.body());
        //progressBar.setProgress(app.totalDonated);
        //String totalDonatedStr = "$" + app.totalDonated;
        //amountTotal.setText(totalDonatedStr);
        //amountText.setText("");
        //amountPicker.setValue(0);
    }

    @Override
    public void onFailure(Call<Hoot> call, Throwable t) {
        Toast toast = Toast.makeText(this, "Error making hoot", Toast.LENGTH_LONG);
        toast.show();
    }
}
