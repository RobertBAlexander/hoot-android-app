package app.hoot.activity;

/**
 * Created by Robert Alexander on 02/01/2018.
 */

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


import app.hoot.R;
import app.hoot.model.Chronology;
import app.hoot.model.Hoot;
import app.hoot.main.HootApp;
import app.hoot.settings.SettingsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import static app.hoot.R.attr.title;
import static app.hoot.helpers.ContactHelper.getContact;
import static app.hoot.helpers.ContactHelper.getEmail;
import static app.hoot.helpers.ContactHelper.sendEmail;
import static app.hoot.helpers.IntentHelper.navigateUp;
import static app.hoot.helpers.IntentHelper.selectContact;

public class HootOut extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener,  Callback<Hoot> {
    private static final int REQUEST_CONTACT = 1;
    private Button submitHoot;
    private EditText hootmain;
    private TextView counter;
    private HootApp app;
    private TextView hootDate;
    //private TextView hashtag;
    private Button   contactButton;
    private Button emailButton;
    private String emailAddress = "";
    private String emailBody = "";
    private Hoot hoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhoot);
        app = (HootApp) getApplication();
        submitHoot = (Button) findViewById(R.id.submitHoot);
        hootmain = (EditText) findViewById(R.id.hootmain);
        //counter = (TextView) findViewById(R.id.counter);
        hootDate = (TextView) findViewById(R.id.addHootDate);
        contactButton = (Button) findViewById(R.id.contactButton);
        emailButton = (Button) findViewById(R.id.emailButton);
        contactButton.setOnClickListener(this);
        emailButton.setOnClickListener(this);
        // http://www.technotalkative.com/android-get-current-date-and-time-in-different-format/
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss");

        String currentDateTimeString = dateFormat.format(cal.getTime());
        //String currentDateTimeString = "The date";
        hootDate.setText(currentDateTimeString);

        hootmain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //counter.setText(String.valueOf(140 - (hootWords.getText().toString().length())));
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
    public void hootButtonPressed (View view) {
        String text = hootmain.getText().toString();
        String date = hootDate.getText().toString();
        //String date = "testing without date";
        if(text.equals("")) {
            Toast toast = Toast.makeText(this, "Hoot must contain characters", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(text.length() > 140){
            Toast toast = Toast.makeText(this, "Hoot must be less than 140 characters", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Hoot hoot = new Hoot(text, date);// hashtag
            Call<Hoot> call = (Call<Hoot>)app.hootService.createHoot(hoot);
            call.enqueue(this);
            Log.v("Hoot", "Hoot Pressed!");
            startActivity(new Intent(this, Timeline.class));
        }
        hootmain.setText("");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.chronology:
                startActivity(new Intent(this, Timeline.class));
                break;
            case R.id.menuSettings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.menuLogout:   startActivity(new Intent(this, Welcome.class));
                break;
            case android.R.id.home:
                navigateUp(this);
                return true;
        }
        return true;
    }
    @Override
    // https://stackoverflow.com/questions/5914040/onbackpressed-to-hide-not-destroy-activity
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.hoot_page, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.contactButton : selectContact(this, REQUEST_CONTACT);
                break;
            case R.id.emailButton:
                sendEmail(this, emailAddress,
                        getString(R.string.email_subject), hootmain.getText().toString());
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case REQUEST_CONTACT:
                String name = getContact(this, data);
                emailAddress = getEmail(this, data);
                contactButton.setText(name + " : " + emailAddress);
                //residence.tenant = name;
                break;
        }
    }
    @Override
    public void onResponse(Call<Hoot> call, Response<Hoot> response) {
        Toast toast = Toast.makeText(this, "Hoot hooted", Toast.LENGTH_SHORT);
        toast.show();
        app.addHoot(response.body());
    }
    @Override
    public void onFailure(Call<Hoot> call, Throwable t) {
        Toast toast = Toast.makeText(this, "Error making hoot", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        String date = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime().toString();
        hoot.date = date;
        submitHoot.setText(hoot.getFullDate());

    }
}
