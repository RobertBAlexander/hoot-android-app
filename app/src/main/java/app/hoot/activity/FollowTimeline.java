package app.hoot.activity;

/**
 * Created by Robert Alexander on 06/01/2018.
 */


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import app.hoot.R;
import app.hoot.main.HootService;
import app.hoot.model.Hoot;
import app.hoot.main.HootApp;
import app.hoot.settings.SettingsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.support.design.widget.NavigationView;
import static app.hoot.helpers.IntentHelper.navigateUp;
import static app.hoot.main.HootApp.currentUser;

public class FollowTimeline extends AppCompatActivity  implements Callback<List<Hoot>> {
    private ListView listView;
    private HootApp app;
    private HootAdapter adapter;
    private String selectedItem;
    //private List<Hoot> holder = new ArrayList<Hoot>();
    private final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronology);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        app = (HootApp) getApplication();

/*        Toast toast = Toast.makeText(Timeline.this, "Current User " + currentUser.firstName, Toast.LENGTH_LONG);
        toast.show();*/

        listView = (ListView) findViewById(R.id.chronology);
        adapter = new HootAdapter(this, app.hoots);
        listView.setAdapter(adapter);
/*        Toast.makeText(getApplicationContext(), "Current user is " + app.currentUser._id, Toast.LENGTH_SHORT).show();*/

        Call<List<Hoot>> call = (Call<List<Hoot>>) app.hootService.getFollowedHoots(app.currentUser._id);
        call.enqueue(this);



/*        Toolbar toolbar = (Toolbar) findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.follow_chronology, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_hoot:
                //Toast.makeText(getActivity(), "Button pressed", Toast.LENGTH_SHORT).show();
                //TODO: Remove dependancies on hoots and ids, and instead just link straight to
                //creation of a hoot. Comment out rest of this button, and see how I get on.
                Call<Hoot> hoot = app.hootService.createHoot(new Hoot("Hello",  "date"));//"hashtag",

                startActivity(new Intent(this, HootOut.class));
                return true;

            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            case R.id.map:
                startActivity(new Intent(this, Map.class));
                return true;
            case R.id.hoottimeline:
                startActivity(new Intent(this, Timeline.class));
                break;
            case R.id.followtimeline:
                startActivity(new Intent(this, UsersTimeline.class));
                Toast.makeText(this, "You have successfully refreshed the Follow Timeline", Toast.LENGTH_SHORT).show();
                break;
            case R.id.usertimeline:
                startActivity(new Intent(this, UsersTimeline.class));
                break;
            case R.id.logout:
                Intent in = new Intent(this, Welcome.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(in, 0);
                return true;
            case android.R.id.home:
                navigateUp(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(Call<List<Hoot>> call, Response<List<Hoot>> response) {
        adapter.hoots = response.body();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<Hoot>> call, Throwable t) {
        Toast toast = Toast.makeText(this, "Error retrieving hoots", Toast.LENGTH_LONG);
        toast.show();
    }

/*    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }*/
}

class FollowTimelineAdapter extends ArrayAdapter<Hoot> {
    private Context context;
    public List<Hoot> hoots = new ArrayList<Hoot> ();

    public FollowTimelineAdapter(Context context, List<Hoot> hoots) {
        super(context, R.layout.hootrow, hoots);
        this.context = context;
        this.hoots = hoots;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.hootrow, parent, false);
        Hoot hoot = hoots.get(position);


        TextView hootView = (TextView) view.findViewById(R.id.chronology_item_hoot);
        TextView hootDate = (TextView) view.findViewById(R.id.chronology_item_dateTextView);
        //TextView hootHashtag = (TextView) view.findViewById(R.id.chronology_item_hashtagTextView);

        hootView.setText(hoot.hootmain);
        hootDate.setText(hoot.date);
        //hootHashtag.setText(hoot.hashtag);

        return view;
    }

    @Override
    public int getCount() {
        if (hoots != null) {
            return hoots.size();
        }
        else {
            return 0;
        }
    }
}
