/*
package app.hoot.activity;

*/
/**
 * Created by Robert Alexander on 25/10/2017.
 *//*

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import app.hoot.R;
import app.hoot.fragments.ChronologyFragment;
import app.hoot.fragments.HootFragment;
import app.hoot.model.Hoot;

public class Home extends Base{
    TextView recentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_launcher1);

        recentList = (TextView) findViewById(R.id.recentlyAddedListEmpty);

        //this.setupCoffees();
    }

    public void add(View v) {
        goToActivity(this, AddHoot.class, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (hootList.isEmpty()) {
            recentList.setText(getString(R.string.recentlyViewedListEmptyMessage));

        } else {
            recentList.setText("");
        }
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new HootFragment();
            manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();

            //create the fragment
        }

    }

    */
/*public void setupHoots() {
        hootList.add(new Hoot("Standard Black", "Some Shop", 2.5, 1.99, false));
        hootList.add(new Hoot("Regular Joe", "Joe's Place", 3.5, 2.99, true));
        hootList.add(new Hoot("Espresso", "Ardkeen Stores", 4.5, 1.49, true));
    }*//*

}
*/
