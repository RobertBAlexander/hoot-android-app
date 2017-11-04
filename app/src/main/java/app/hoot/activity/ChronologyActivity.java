package app.hoot.activity;

/**
 * Created by Robert Alexander on 02/11/2017.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import app.hoot.R;
import app.hoot.fragments.ChronologyFragment;

public class ChronologyActivity extends AppCompatActivity {

    //private ListView listView;
    //private Chronology chronology;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new ChronologyFragment();
            manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }


}
