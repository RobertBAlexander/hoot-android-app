package app.hoot.fragments;

/**
 * Created by Robert Alexander on 21/10/2017.
 */
import android.app.AlertDialog;
//import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import app.hoot.R;
import app.hoot.activity.Base;
//import app.hoot.activity.Edit;
//import app.hoot.adapters.CoffeeFilter;
//import app.hoot.adapters.CoffeeListAdapter;
import app.hoot.main.HootApp;
import app.hoot.model.Hoot;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
//import app.hoot.model.Portfolio;
//import app.hoot.model.Residence;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.DatePickerDialog;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
//import static app.hoot.helpers.ContactHelper.sendEmail;
//import static app.hoot.helpers.ContactHelper.getEmail;
//import static app.hoot.helpers.ContactHelper.getContact;
//import static app.hoot.helpers.IntentHelper.navigateUp;

import static app.hoot.R.layout;
import static app.hoot.R.id;
import static app.hoot.R.string;


public class HootFragment extends Fragment implements TextWatcher,
        OnCheckedChangeListener,
        OnClickListener,
        DatePickerDialog.OnDateSetListener {

    public static final String EXTRA_HOOT_ID = "myhoot.HOOT_ID";

    private EditText hootContent;
    private Button   viewHootButton;
    private Button   deleteHootButton;

    private Hoot   hoot;

    private String emailAddress = "";
    // This field is initialized in `onActivityResult`.
    private Intent data;
    HootApp app;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Long resId = (Long) getActivity().getIntent().getSerializableExtra(EXTRA_HOOT_ID);

        app = HootApp.getApp();
        //portfolio = app.portfolio;
        //residence = portfolio.getResidence(resId);
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        super.onCreateView(inflater,  parent, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_container, parent, false);

        ResidenceActivity residenceActivity = (ResidenceActivity)getActivity();
        residenceActivity.actionBar.setDisplayHomeAsUpEnabled(true);

        addListeners(view);
        updateControls(residence);

        return view;
    }*/

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }
}
