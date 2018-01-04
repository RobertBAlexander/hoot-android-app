package app.hoot.activity;

/**
 * Created by Robert Alexander on 02/11/2017.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import app.hoot.R;
import app.hoot.fragments.ChronologyFragment;
import app.hoot.main.HootApp;
import app.hoot.main.HootService;
import app.hoot.model.Hoot;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChronologyActivity extends AppCompatActivity {

    //private ListView listView;
    //private Chronology chronology;
    public HootService hootService;
    private HootAdapter adapter;

    HootApp app;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);



        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new ChronologyFragment();
            manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }

/*    @Override
    public void onResponse(Call<List<Hoot>> call, Response<List<Hoot>> response) {
        adapter.hoots = response.body();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<Hoot>> call, Throwable t) {
        Toast toast = Toast.makeText(this, "Error retrieving hoots", Toast.LENGTH_LONG);
        toast.show();
    }*/


}
