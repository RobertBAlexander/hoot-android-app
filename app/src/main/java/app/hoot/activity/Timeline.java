package app.hoot.activity;

/**
 * Created by Robert Alexander on 02/01/2018.
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

import static app.hoot.helpers.IntentHelper.navigateUp;

public class Timeline extends AppCompatActivity  implements Callback<List<Hoot>> {
    private ListView listView;
    private HootApp app;
    private HootAdapter adapter;
    private String selectedItem;
    private List<Hoot> holder = new ArrayList<Hoot>();
    private final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronology);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        app = (HootApp) getApplication();

        listView = (ListView) findViewById(R.id.chronology);
        adapter = new HootAdapter(this, app.hoots);
        listView.setAdapter(adapter);

        Call<List<Hoot>> call = (Call<List<Hoot>>) app.hootService.getAllHoots();
        call.enqueue(this);

        // http://piyushovte.blogspot.ie/2011/03/listview-data-select-and-delete.html
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
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chronology, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));

                return true;
            case R.id.clear:
                startActivity(new Intent(this, Timeline.class));
                Toast toast = Toast.makeText(this, "Unable to delete all hoots, please delete them individually", Toast.LENGTH_LONG);
                toast.show();
                return true;
            case R.id.usertimeline:
                startActivity(new Intent(this, UsersTimeline.class));
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
    public void onResponse(Call<List<Hoot>> call, Response<List<Hoot>> response) {
        adapter.hoots = response.body();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<Hoot>> call, Throwable t) {
        Toast toast = Toast.makeText(this, "Error retrieving hoots", Toast.LENGTH_LONG);
        toast.show();
    }
}

class HootAdapter extends ArrayAdapter<Hoot> {
    private Context context;
    public List<Hoot> hoots = new ArrayList<Hoot> ();

    public HootAdapter(Context context, List<Hoot> hoots) {
        super(context, R.layout.hootrow, hoots);
        this.context = context;
        this.hoots = hoots;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.hootrow, parent, false);
        Hoot hoot = hoots.get(position);

        // Sorts hoots with newest first
        // https://stackoverflow.com/questions/2592501/how-to-compare-dates-in-java#answer-21085919
//        Collections.sort(hoots, new Comparator<Hoot>() {
//            @Override
//            public int compare(Hoot h1, Hoot h2) {
//
//                if (h1.date.compareTo(h2.date) > 0) {
//                    return 1;
//                } else if (h1.date.compareTo(h2.date) <0) {
//                    return -1;
//                } else {
//                    return 0;
//                }
//
//            }
//        });
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
        return hoots.size();
    }
}
