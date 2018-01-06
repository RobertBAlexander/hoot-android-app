package app.hoot.activity;

/**
 * Created by Robert Alexander on 03/01/2018.
 */


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import app.hoot.main.HootApp;
import app.hoot.model.Hoot;
import app.hoot.model.User;
import app.hoot.settings.SettingsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.hoot.helpers.IntentHelper.navigateUp;
import static app.hoot.helpers.LogHelpers.info;
import static app.hoot.main.HootApp.currentUser;

public class UsersTimeline extends AppCompatActivity  implements Callback<List<User>>, AdapterView.OnItemClickListener {
    private ListView listView;
    private HootApp app;
    private UserAdapter adapter;
    private Button followButton;
    private String selectedItem;
    //private final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronology);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        app = (HootApp) getApplication();

        listView = (ListView) findViewById(R.id.chronology);
        adapter = new UserAdapter(this, app.users);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

/*        Toast toast = Toast.makeText(UsersTimeline.this, "Current User " + currentUser.firstName, Toast.LENGTH_LONG);
        toast.show();*/

        Call<List<User>> call1 = (Call<List<User>>) app.hootService.getAllUsers();
        call1.enqueue(this);

       /* // http://piyushovte.blogspot.ie/2011/03/listview-data-select-and-delete.html
        listView.setOnItemLongClickListener (new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to delete this hoot?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // Due to getting hoots from API issue here with deleting a hoot
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        //adapter.remove(adapter.getItem(i));
                        //Toast.makeText(getApplicationContext(), Integer.toString(i), Toast.LENGTH_SHORT).show();
                        //adapter.notifyDataSetChanged();

                        Toast.makeText(getApplicationContext(), "Unable to delete hoot at this time, sorry!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
                return true;
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chronology, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                navigateUp(this);
                return true;

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
            case R.id.usertimeline:
                startActivity(new Intent(this, UsersTimeline.class));
                Toast.makeText(this, "You have successfully refreshed the User Timeline", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Intent in = new Intent(this, Welcome.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(in, 0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        adapter.users = response.body();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        Toast toast = Toast.makeText(this, "Error retrieving users", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        final User user = adapter.getItem(position);
        String userId = user._id;
        Toast toast = Toast.makeText(UsersTimeline.this, "At least the button works", Toast.LENGTH_LONG);
        toast.show();
        if(app.currentUser.following != null) {
            if(app.currentUser.following.contains(userId)) {
                Call<User> call1 = (Call<User>) app.hootService.unfollow(app.currentUser._id, userId);
                call1.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast toast = Toast.makeText(UsersTimeline.this, "Unfollowed " + user.firstName + " " + user.lastName, Toast.LENGTH_LONG);
                        toast.show();
                        app.currentUser = response.body();
                        startActivity(new Intent(UsersTimeline.this, Timeline.class));
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast toast = Toast.makeText(UsersTimeline.this, "Unable to unfollow " + user.firstName + " " + user.lastName, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            } else if (!app.currentUser.following.contains(userId)) {
                Call<User> call1 = (Call<User>) app.hootService.follow(app.currentUser._id, userId);
                call1.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast toast = Toast.makeText(UsersTimeline.this, "Started following " + user.firstName + " " + user.lastName, Toast.LENGTH_LONG);
                        toast.show();
                        app.currentUser = response.body();
                        startActivity(new Intent(UsersTimeline.this, Timeline.class));
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast toast = Toast.makeText(UsersTimeline.this, "Unable to follow " + user.firstName + " " + user.lastName, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        }
    }

/*    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            *//*case R.id.userButton:
                info(this, "Login Pressed");
                startActivity(new Intent(this, Login.class));
                break;*//*


            case R.id.followButton:
                if (app.currentUser.following != null) {
                    String id = app.currentUser._id;
                    Call<User> cal1 = (Call<User>) app.hootService.getUser(id);
                    cal1.enqueue(this);
                    info(this, "Signup Pressed");
                    startActivity(new Intent(this, Signup.class));
                    break;
                } else {
                }
        }
    }*/

/*    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        adapter.users = response.body();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        Toast toast = Toast.makeText(this, "Error retrieving users", Toast.LENGTH_LONG);
        toast.show();
    }*/
}

class UserAdapter extends ArrayAdapter<User> {
    private Context context;
    private HootApp app;
    public List<User> users = new ArrayList<User>();

    public UserAdapter(Context context, List<User> users) {
        super(context, R.layout.activity_listusers_rows, users);
        this.context = context;
        this.users = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_listusers_rows, parent, false);
        User user = users.get(position);

        TextView fullUserName = (TextView) view.findViewById(R.id.fullUserName);
        fullUserName.setText(user.firstName + " " + user.lastName);

        TextView button = (TextView) view.findViewById(R.id.followButton);
        if(app.currentUser.following != null) {
            if (app.currentUser.following.contains(user._id)) {
                button.setText("Unfollow");
            } else {
                button.setText("Follow");
            }
        } //else {
        //    button.setText("Follow");
        //}

        return view;
    }

    @Override
    public int getCount() {
        return users.size();
    }
}