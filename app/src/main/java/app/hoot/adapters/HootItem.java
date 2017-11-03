package app.hoot.adapters;

/**
 * Created by Robert Alexander on 25/10/2017.
 */
import app.hoot.model.Hoot;
import app.hoot.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class HootItem {
    View view;

    public HootItem(Context context, ViewGroup parent,
                      OnClickListener deleteListener, Hoot hoot)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.hootrow, parent, false);
       // view.setId(hoot.hootId);

        updateControls(hoot);

        //ImageView imgDelete =  view.findViewById(R.id.imgDelete);
        //imgDelete.setTag(hoot);
        //imgDelete.setOnClickListener(deleteListener);
    }

    private void updateControls(Hoot hoot) {
        ((TextView) view.findViewById(R.id.chronology_item_hoot)).setText(hoot.hootContent);
        ((TextView) view.findViewById(R.id.chronology_item_hashtagTextView)).setText(hoot.hashtag);
        ((TextView) view.findViewById(R.id.chronology_item_dateTextView)).setText(hoot.getFullDate());

        //ImageView imgIcon = view.findViewById(R.id.RowImage);

       /* if (hoot.favourite == true)
            imgIcon.setImageResource(R.drawable.ic_favourite_on);
        else
            imgIcon.setImageResource(R.drawable.ic_favourite_off);*/
    }
}
