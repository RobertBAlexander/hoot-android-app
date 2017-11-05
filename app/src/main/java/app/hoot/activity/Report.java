/*
package app.hoot.activity;
*/
/**
 * Created by Robert Alexander on 18/10/2017.
 *//*
/*
import app.hoot.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.DatePickerDialog;
import android.view.View;
import android.view.View.OnClickListener;

import app.hoot.main.HootApp;
import app.hoot.model.Chronology;
import app.hoot.model.Hoot;

public class Report extends AppCompatActivity implements TextWatcher,
        OnCheckedChangeListener,
        View.OnClickListener,
        DatePickerDialog.OnDateSetListener {
    ListView listView;
    private Hoot hoot;
    private Button dateButton;
    private Chronology chronology;
    private EditText hootActual;


    static final String[] numbers = new String[] {
            "Amount, Pay method",
            "10,     Direct",
            "100,    PayPal",
            "1000,   Direct",
            "10,     PayPal",
            "5000,   PayPal"};//all of this needs to change when actual hoots are created and reported

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        listView = (ListView) findViewById(R.id.reportList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, numbers);
        listView.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId())
        {
            case R.id.hootText:   startActivity(new Intent(this, AddHoot.class));
                break;
            case R.id.menuSettings: Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuLogout:   startActivity(new Intent(this, Welcome.class));
                break;
        }
        return true;
    }

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
}*/
