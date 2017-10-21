package app.hoot.activity;

/**
 * Created by rober on 21/10/2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import static app.hoot.helpers.IntentHelper.startActivityWithData;
import static app.hoot.helpers.IntentHelper.startActivityWithDataForResult;

import app.hoot.R;

public class AddHoot extends AppCompatActivity {
    private Button submitHoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoot);

        submitHoot = (Button)findViewById(R.id.submitHoot);

        if(submitHoot != null)
        {
            Log.v("Hoot", "got the submit hoot button");
        }
    }

    public void submitHootPressed (View view)
    {
        Log.v("Hoot", "Submit Hoot Pressed!");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            /*case R.id.menu_item_new_hoot: AddHoot hoot = new AddHoot();
                portfolio.addHoot(hoot);
                startActivityWithDataForResult(this, AddHoot.class, "RESIDENCE_ID", hoot.id, 0);
                return true;*/

            case R.id.menuReport:
                startActivity (new Intent(this, Report.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuLogout:   startActivity(new Intent(this, Welcome.class));
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hoot, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
