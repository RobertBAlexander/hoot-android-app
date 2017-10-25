package app.hoot.activity;

/**
 * Created by Robert Alexander on 21/10/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

import java.util.Locale;

import app.hoot.R;
import app.hoot.model.Hoot;

public class AddHoot extends Base implements
        View.OnClickListener {
    private String hootActual, hashtag;
    private String user;
    private String hootDate;
    private EditText hootContent;
    private Spinner spinner;
    private TextView Date;
    private String fullDate;
    private Long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhoot);

        Button saveButton = (Button) findViewById(R.id.submitHoot);
        hootContent = (EditText) findViewById(R.id.hootContent);

        //spinner array Reference: https://stackoverflow.com/questions/9768919/how-to-use-spinner-and-fill-it-from-array-in-android
        spinner = (Spinner) findViewById(R.id.addHashtagSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.hashtags_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        hashtag = spinner.toString();

        //Date Reference: https://stackoverflow.com/questions/12934661/android-get-current-date-and-show-it-in-textview
        //And: https://docs.microsoft.com/en-us/dotnet/standard/base-types/standard-date-and-time-format-strings
        //However additional modifications were made
        Date = (TextView) findViewById(R.id.addHootDate);
        //String theDate = DateFormat.getDateInstance(DateFormat.FULL, Locale.UK).format(new Date());
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        fullDate = currentDateTimeString;

        Date.setText(currentDateTimeString.substring(0, 17));

        userId = 123456789L;
        saveButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        hootActual = hootContent.getText().toString();
        //Get spinner string Reference: https://stackoverflow.com/questions/10331854/how-to-get-spinner-selected-item-value-to-string
        hashtag = spinner.getSelectedItem().toString();
        //user = userId;
        hootDate = Date.toString();

        if ((hootActual.length() > 0) && hootActual.length() < 140)
        {
            Hoot h = new Hoot(hootActual, hashtag, fullDate, userId);
            app.hootList.add(h);
            goToActivity(this, Signup.class, null);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!This is only temporary until it runs. Must change to home, when created!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            Log.v("Hoot", "Submit Hoot Pressed!"); //Test Hoot button functionality
        }
        else
        {
            Toast.makeText(
                    this,
                    "Your tweet must be between 1 and 140 characters in length",
                    Toast.LENGTH_SHORT).show();
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
                    /*else
            {
                //Vibrate Reference: https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 900 milliseconds
                v.vibrate(900);
                Toast.makeText(this, "Invalid Hoot, please try again", Toast.LENGTH_SHORT).show();
            }*/

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
