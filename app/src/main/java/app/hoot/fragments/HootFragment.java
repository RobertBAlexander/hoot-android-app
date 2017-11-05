package app.hoot.fragments;

/**
 * Created by Robert Alexander on 21/10/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.ListFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.Manifest;
import android.app.Activity;

import app.hoot.R;
import app.hoot.activity.AddHoot;
import app.hoot.adapters.HootListAdapter;
import app.hoot.main.HootApp;
import app.hoot.model.Chronology;
import app.hoot.model.Hoot;
import static app.hoot.helpers.ContactHelper.getContact;
import static app.hoot.helpers.ContactHelper.getEmail;
import static app.hoot.helpers.ContactHelper.sendEmail;
import static app.hoot.helpers.IntentHelper.navigateUp;
//import static app.hoot.helpers.IntentHelper.navigateUp;


import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Date;
import java.util.GregorianCalendar;
import android.app.Activity;
import app.hoot.activity.ChronologyActivity;
import app.hoot.activity.Welcome;
import app.hoot.helpers.IntentHelper;
import app.hoot.main.HootApp;
import app.hoot.model.Chronology;
import app.hoot.model.Hoot;

//import app.tweeting.settings.SettingsActivity;   !!!!!!

import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


//import static app.hoot.R.id;
//import app.hoot.R;


public class HootFragment extends Fragment implements TextWatcher,
        //OnCheckedChangeListener,
        OnClickListener,
        DatePickerDialog.OnDateSetListener {

    public static final String EXTRA_HOOT_ID = "myhoot.HOOT_ID";
    private static  final int     REQUEST_CONTACT = 1;

    private EditText hootContent;
    private Button   hootButton;
    private Button   deleteHootButton;
    public EditText hashtag;
    public TextView date;
    private Button emailButton;
    private Button contactButton;
    private TextView countdown;

    private Hoot   hoot;
    private Chronology chronology;

    protected static HootListAdapter listAdapter;
    protected ListView listView;

    private String emailAddress = "";
    // This field is initialized in `onActivityResult`.
    private Intent data;
    HootApp app;

    public HootFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        Fragment fragment = new HootFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Long hootId = (Long) getActivity().getIntent().getSerializableExtra(EXTRA_HOOT_ID);

        //listAdapter = new HootListAdapter(getActivity(), this, Base.hootList);
        //setListAdapter (listAdapter);

        app = HootApp.getApp();
        chronology = app.chronology;
        hoot = chronology.getHoot(hootId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        super.onCreateView(inflater,  parent, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_addhoot, parent, false);
        AddHoot addHoot = (AddHoot) getActivity();
        addHoot.actionBar.setDisplayHomeAsUpEnabled(true);

        //AddHoot addHoot = (AddHoot)getActivity();
        //addHoot.actionBar.setDisplayHomeAsUpEnabled(true);

        addListeners(view);
        updateControls(hoot);

        return view;
    }
//Reference for countdown: https://stackoverflow.com/questions/24110265/android-create-count-down-word-field-when-user-type-in-edittext
    private void addListeners(View view) {
        hootContent  = view.findViewById(R.id.hootContent);
        hootButton   = view.findViewById(R.id.submitHoot);
        date =   view.findViewById(R.id.addHootDate);
        emailButton = view.findViewById(R.id.emailButton);
        contactButton = view.findViewById(R.id.contactButton);
        countdown = view.findViewById(R.id.countdown);

        countdown.setText(String.valueOf(140));
        hootContent .addTextChangedListener(this);
        hootButton  .setOnClickListener(this);
        date  .setOnClickListener(this);
        emailButton.setOnClickListener(this);
        contactButton.setOnClickListener(this);

        hootContent.addTextChangedListener(new TextWatcher() {

            //Reference for colour change. Sadly it didn't work: https://stackoverflow.com/questions/16430656/change-text-color-based-based-on-value-of-text
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                countdown.setText(String.valueOf(140 - (hootContent.getText().toString().length())));
/*                int countcolour = Integer.parseInt(countdown.toString());
                if (countcolour >= 1) {
                    countdown.setTextColor(Color.BLUE);
                } else if (countcolour == 0) {
                    countdown.setTextColor(Color.YELLOW);
                } else {
                        countdown.setTextColor(Color.RED);
                    }*/

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void updateControls(Hoot hoot) {
        hootContent.setText(hoot.hootContent);
        date.setText(hoot.getFullDate());
//        hashtag.setText(hoot.hashtag);
    }

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {

        }
    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.hoot_page, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: getActivity().finish();
                return true;


            case R.id.chronology:

                //if (hoot.hootContent == null) {
                    chronology.deleteHoot(hoot);
                //}
                startActivity(new Intent(getActivity(),ChronologyActivity.class));
                return true;

            /*case id.settings:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;*/

            case R.id.logout:
                Intent in = new Intent(getActivity(), Welcome.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(in, 0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
/*        if (hootContent.getText().toString().length() > 0) {
            hoot.hootContent = hootContent.getText().toString();
            IntentHelper.navigateUp(getActivity());
            chronology.saveHoots();
        } else {
            chronology.deleteHoot(hoot);
        }*/
       chronology.saveHoots();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {

            case R.id.submitHoot :                     //should this be hootButton?
                if (hootContent.getText().length() > 140) {
                    Toast.makeText(app.getApplicationContext(), "This hoot it way... way too long. Kind of like this notification. Try 140 characters or less", Toast.LENGTH_SHORT).show();
                    break;
                }
                        else if(hootContent.getText().length() > 0) {
                hoot.hootContent = hootContent.getText().toString();
                Toast.makeText(getActivity(), "Successful: " + hoot.hootContent, Toast.LENGTH_LONG).show();
                    getActivity().finish();
                    chronology.saveHoots();
                    break;
                } else {
                    Toast.makeText(app.getApplicationContext(), "You need to write something for your hoot.", Toast.LENGTH_SHORT).show();
                    break;
                }

            case R.id.emailButton:
                sendEmail(getActivity(), emailAddress, getString(R.string.email_subject), hoot.getHootContent());
                break;

            case R.id.contactButton:
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i, REQUEST_CONTACT);
                if (hoot.contact != null) {
                    contactButton.setText("Contact: " + hoot.contact);
                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode != Activity.RESULT_OK)
        {
            return;
        }

        switch (requestCode)
        {
            case REQUEST_CONTACT:
                this.data = data;
                checkContactsReadPermission();
                break;
        }
    }

    private void checkContactsReadPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACT);
        } else {
            readContact();
        }
    }

    //TODO !!!also needs another class for full permissions
    private void readContact() {
        String name = getContact(getActivity(), data);
        emailAddress = getEmail(getActivity(), data);
        hoot.contact = name;
        contactButton.setText("Contact: " + hoot.contact);
    }

    //Reference: https://stackoverflow.com/questions/33666071/android-marshmallow-request-permission
    //including links from this page
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CONTACT: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContact();
                }
            }
        }
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        Date date = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
        hoot.date = date.getTime();
        hootButton.setText(hoot.getFullDate());

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        Log.i(this.getClass().getSimpleName(), "hoot content " + editable.toString());
        hoot.setHootContent(editable.toString());

    }

/*    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        //hoot.rented = isChecked;
    }*/
}
