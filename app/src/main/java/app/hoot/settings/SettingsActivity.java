package app.hoot.settings;

/**
 * Created by Robert Alexander on 05/11/2017.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import app.hoot.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
        SettingsFragment fragment = new SettingsFragment();
        getFragmentManager().beginTransaction()
                .add(android.R.id.content, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chronology, menu);//could be chronology context
        return true;
    }

}