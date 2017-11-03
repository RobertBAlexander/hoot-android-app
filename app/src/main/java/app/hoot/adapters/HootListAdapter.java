package app.hoot.adapters;

/**
 * Created by Robert Alexander on 25/10/2017.
 */
import app.hoot.model.Hoot;
import app.hoot.R;

import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class HootListAdapter extends ArrayAdapter<Hoot> {

    private Context context;
    private OnClickListener deleteListener;
    public List<Hoot> hootList;

    public HootListAdapter(Context context, OnClickListener deleteListener, List<Hoot> hootList)
    {
        super(context, R.layout.hootrow, hootList);

        this.context = context;
        this.deleteListener = deleteListener;
        this.hootList = hootList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        HootItem item = new HootItem(context, parent, deleteListener,
                hootList.get(position));
        return item.view;

    }//need to have this as not null too!

    @Override
    public int getCount()
    {
        return hootList.size();
    }

    @Override
    public Hoot getItem(int position)
    {
        return hootList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getPosition(Hoot hoot)
    {
        return hootList.indexOf(hoot);
    }
}
