package janjira.jiraporn.yonlada.aroirestuarant.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import janjira.jiraporn.yonlada.aroirestuarant.R;

/**
 * Created by masterung on 15/11/2017 AD.
 */

public class MyAdapter extends BaseAdapter{

    private Context context;
    private String[] titleStrings, detailStrings, iconStrings;

    public MyAdapter(Context context,
                     String[] titleStrings,
                     String[] detailStrings,
                     String[] iconStrings) {
        this.context = context;
        this.titleStrings = titleStrings;
        this.detailStrings = detailStrings;
        this.iconStrings = iconStrings;
    }

    @Override
    public int getCount() {
        return titleStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.listview_food, parent, false);

        TextView titleTextView = view.findViewById(R.id.txtTitle);
        TextView detailTextView = view.findViewById(R.id.txtDetail);
        ImageView imageView = view.findViewById(R.id.imvIcon);

        titleTextView.setText(titleStrings[position]);
        detailTextView.setText(detailStrings[position]);
        Picasso.with(context).load(iconStrings[position]).into(imageView);

        return view;
    }
}
