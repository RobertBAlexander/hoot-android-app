package app.hoot.activity;
/**
 * Created by Robert Alexander on 18/10/2017.
 */
import app.hoot.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Report extends AppCompatActivity
{
    ListView listView;

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
            case R.id.hootText:   startActivity(new Intent(this, Hoot.class));
                break;
            case R.id.menuSettings: Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuLogout:   startActivity(new Intent(this, Welcome.class));
                break;
        }
        return true;
    }
}