package app.hoot.activity;

/**
 * Created by Robert Alexander on 02/11/2017.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import app.hoot.R;
import app.hoot.fragments.ChronologyFragment;
import app.hoot.fragments.HootFragment;

public class AddHoot extends AppCompatActivity {
    public ActionBar actionBar;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        actionBar = getSupportActionBar();

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new HootFragment();
            manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }

}
