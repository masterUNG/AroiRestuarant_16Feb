package janjira.jiraporn.yonlada.aroirestuarant.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import janjira.jiraporn.yonlada.aroirestuarant.R;

/**
 * Created by masterung on 19/3/2018 AD.
 */

public class StatusAdapter extends BaseAdapter {

    private Context context;
    private String[] nameFoodStrings, ItemStrings, statusStrings;
    private TextView nameFoodTextView, imageTextView, statusTextView;
    private String[] strings = new String[]{"กำลังปรุง", "เสร็จแล้ว"};

    public StatusAdapter(Context context,
                         String[] nameFoodStrings,
                         String[] itemStrings,
                         String[] statusStrings) {
        this.context = context;
        this.nameFoodStrings = nameFoodStrings;
        ItemStrings = itemStrings;
        this.statusStrings = statusStrings;
    }

    @Override
    public int getCount() {
        return nameFoodStrings.length;
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
        View view = layoutInflater.inflate(R.layout.listview_status, parent, false);

        nameFoodTextView = view.findViewById(R.id.txtNamefood);
        imageTextView = view.findViewById(R.id.txtItem);
        statusTextView = view.findViewById(R.id.txtStatus);

        nameFoodTextView.setText(nameFoodStrings[position]);
        imageTextView.setText(ItemStrings[position]);
        statusTextView.setText(strings[Integer.parseInt(statusStrings[position])]);

        return view;
    }
}
