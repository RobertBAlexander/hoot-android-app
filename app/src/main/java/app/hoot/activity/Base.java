package app.hoot.activity;

/**
 * Created by Robert Alexander on 21/10/2017.
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import app.hoot.R;
//import app.hoot.fragments.HootFragment;
import app.hoot.main.HootApp;

public class Base extends AppCompatActivity {
    public static HootApp app;
    protected Bundle         	activityInfo; // Used for persistence (of sorts)
    //protected HootFragment hootFragment; // How we'll 'share' our List of Hoots between Activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (HootApp) getApplication();
    }

    protected void goToActivity(Activity current,
                                Class<? extends Activity> activityClass,
                                Bundle bundle) {
        Intent newActivity = new Intent(current, activityClass);

        if (bundle != null) newActivity.putExtras(bundle);

        current.startActivity(newActivity);
    }

    public void openInfoDialog(Activity current) {
        Dialog dialog = new Dialog(current);
        dialog.setTitle("About Hoot Hoot app");
        dialog.setContentView(R.layout.hoot_app_info);

        TextView currentVersion = (TextView) dialog
                .findViewById(R.id.versionTextView);
        currentVersion.setText("1.0.0");

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public void menuInfo(MenuItem m)
    {
        openInfoDialog(this);
    }

    /*public void menuHelp(MenuItem m)
    {
        goToActivity(this, Help.class, null);
    }

    public void menuHome(MenuItem m)
    {
        goToActivity(this, Home.class, null);
    }*/

    protected void toastMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }




}
