
package app.hoot.activity;

/**
 * Created by Robert Alexander on 25/10/2017.
 */


import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import app.hoot.R;
import app.hoot.activity.HootOut;
import app.hoot.activity.Timeline;
import app.hoot.main.HootApp;
import app.hoot.model.Hoot;
import app.hoot.settings.SettingsActivity;

import retrofit2.Call;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    private HootApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        app = (HootApp) getApplication();
/*        Call<List<Hoot>> call = (Call<List<Hoot>>) app.hootService.getAllHoots();
        call.enqueue(this);*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Information", Snackbar.LENGTH_LONG)
                        .setAction("More Info...", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                openInfoDialog(Home.this);
                            }
                        }).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft = getFragmentManager().beginTransaction();

/*        CoffeeFragment fragment = CoffeeFragment.newInstance();
        ft.replace(R.id.homeFrame, fragment);
        ft.commit();*/

        //app.dbManager.setupList();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        // http://stackoverflow.com/questions/32944798/switch-between-fragments-with-onnavigationitemselected-in-new-navigation-drawer

        int id = item.getItemId();
        Fragment fragment;
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        if (id == R.id.nav_home) {
            startActivity(new Intent(this, Timeline.class));

        } else if (id == R.id.nav_add) {
            Call<Hoot> hoot = app.hootService.createHoot(new Hoot("Hello",  "date"));//"hashtag",

            startActivity(new Intent(this, HootOut.class));

        } else if (id == R.id.nav_favourites) {
            startActivity(new Intent(this, SettingsActivity.class));

        } else if (id == R.id.nav_search) {
            startActivity(new Intent(this, SettingsActivity.class));

        } else if (id == R.id.nav_share) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void openInfoDialog(Activity current) {
        Dialog dialog = new Dialog(current);
        dialog.setTitle("About HootHoot");
        dialog.setContentView(R.layout.info);

        TextView currentVersion = (TextView) dialog
                .findViewById(R.id.versionTextView);
        currentVersion.setText("1.0.0");

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

/*    @Override
    public void toggle(View v) {
        EditFragment editFrag = (EditFragment) getFragmentManager().findFragmentById(R.id.homeFrame);
        if (editFrag != null) {
            editFrag.toggle(v);
        }
    }

    @Override
    public void update(View v) {
        EditFragment editFrag = (EditFragment) getFragmentManager().findFragmentById(R.id.homeFrame);
        if (editFrag != null) {
            editFrag.update(v);
        }
    }*/
}



























        /*

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
