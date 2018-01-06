package app.hoot.settings;

/**
 * Created by Robert Alexander on 05/11/2017.
 */
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Map;

import app.hoot.R;
import app.hoot.activity.HootOut;
import app.hoot.activity.Timeline;
import app.hoot.activity.UsersTimeline;
import app.hoot.activity.Welcome;
import app.hoot.main.HootApp;
import app.hoot.model.Hoot;
import retrofit2.Call;

import static app.hoot.helpers.IntentHelper.navigateUp;
import static app.hoot.helpers.LogHelpers.info;

public class SettingsFragment  extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener  {
    private SharedPreferences prefs;
    private HootApp app;

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        prefs = PreferenceManager
                .getDefaultSharedPreferences(getActivity());

        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        info(getActivity(), "Setting change - key : value = " + key + " : " + sharedPreferences.getString(key, ""));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                navigateUp(getActivity());
                return true;

            case R.id.menu_item_new_hoot:
                //Toast.makeText(getActivity(), "Button pressed", Toast.LENGTH_SHORT).show();
                //TODO: Remove dependancies on hoots and ids, and instead just link straight to
                //creation of a hoot. Comment out rest of this button, and see how I get on.
                Call<Hoot> hoot = app.hootService.createHoot(new Hoot("Hello",  "date"));//"hashtag",

                startActivity(new Intent(getActivity(), HootOut.class));
                return true;

            case R.id.settings:
                Toast.makeText(getActivity(), "You are already in settings, you silly billy", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.map:
                startActivity(new Intent(getActivity(), Map.class));
                return true;

            case R.id.hoottimeline:
                startActivity(new Intent(getActivity(), Timeline.class));
                return true;

            case R.id.usertimeline:
                startActivity(new Intent(getActivity(), UsersTimeline.class));
                return true;

            case R.id.logout:
                Intent in = new Intent(getActivity(), Welcome.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(in, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
