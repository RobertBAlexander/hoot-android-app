package app.hoot.fragments;

/**
 * Created by Robert Alexander on 02/11/2017.
 */
import app.hoot.R;

import android.widget.AbsListView;
import android.widget.AdapterView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.hoot.activity.AddHoot;
import app.hoot.helpers.IntentHelper;
import app.hoot.main.HootApp;
import app.hoot.activity.Welcome;
import app.hoot.model.Chronology;
import app.hoot.model.Hoot;

public class ChronologyFragment extends ListFragment implements AdapterView.OnItemClickListener, AbsListView.MultiChoiceModeListener {
    private ArrayList<Hoot> hoots;
    private Chronology chronology;
    private HootAdapter adapter;
    private ListView listView;

    HootApp app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.app_name);

        app = HootApp.getApp();
        chronology = app.chronology;
        hoots = chronology.hoots;

        adapter = new HootAdapter(getActivity(), hoots);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, parent, savedInstanceState);
        listView = view.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);
        return view;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Hoot hoot = ((HootAdapter) getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), AddHoot.class);
        i.putExtra(HootFragment.EXTRA_HOOT_ID, hoot.hootId);
        startActivityForResult(i, 0);
    }


    @Override
    public void onResume() {
        super.onResume();
        ((HootAdapter) getListAdapter()).notifyDataSetChanged();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.chronology, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_hoot:
                //Toast.makeText(getActivity(), "Button pressed", Toast.LENGTH_SHORT).show();
                Hoot hoot = new Hoot();
                chronology.addHoot(hoot);

                Intent i = new Intent(getActivity(), AddHoot.class);
                i.putExtra(HootFragment.EXTRA_HOOT_ID, hoot.hootId);
                startActivityForResult(i, 0);
                return true;

/*            case R.id.settings:
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Hoot hoot = adapter.getItem(position);
        IntentHelper.startActivityWithData(getActivity(), AddHoot.class, "HOOT_ID", hoot.hootId);
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.chronology_context, menu);
        return true;
    }


    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }


    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_item_delete_hoot:
                deleteHoot(actionMode);
                return true;
            default:
                return false;
        }
    }


    private void deleteHoot(ActionMode actionMode) {
        for (int i = adapter.getCount() - 1; i >= 0; i--) {
            if (listView.isItemChecked(i)) {
                chronology.deleteHoot(adapter.getItem(i));
            }
        }
        actionMode.finish();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
    }


    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
    }

//Reference: lab 3b S Drohan
    class HootAdapter extends ArrayAdapter<Hoot> {
        private Context context;

        public HootAdapter(Context context, ArrayList<Hoot> hoots) {
            super(context, 0, hoots);
            this.context = context;
        }

        //Reference for removing new lines: https://stackoverflow.com/questions/11221491/how-to-make-multiple-line-string-to-single-line-string
        //Reference for reducing string length to 30: https://stackoverflow.com/questions/1199352/smart-way-to-shorten-long-strings-with-javascript

        @SuppressLint("InflateParams")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.hootrow, null);
            }
           Hoot hoot = getItem(position);

            TextView messageView = convertView.findViewById(R.id.chronology_item_hoot);
            assert hoot != null;
            messageView.setText(hoot.hootContent.replaceAll("[\r\n]+", " ").substring(0, 30));

            TextView dateView = convertView.findViewById(R.id.chronology_item_dateTextView);
            dateView.setText(hoot.getFullDate());
            return convertView;
        }
    }

}
