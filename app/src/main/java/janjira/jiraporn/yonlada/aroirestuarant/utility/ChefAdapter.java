package janjira.jiraporn.yonlada.aroirestuarant.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import janjira.jiraporn.yonlada.aroirestuarant.R;

/**
 * Created by masterung on 25/3/2018 AD.
 */

public class ChefAdapter extends BaseAdapter {
    Context context;
    String[] nameFoodStrings, tableStrings, itemStrings, statusStrings;
    private String[] strings = new String[]{"กำลังปรุง", "เสร็จแล้ว"};

    public ChefAdapter(Context context,
                       String[] nameFoodStrings,
                       String[] tableStrings,
                       String[] itemStrings,
                       String[] statusStrings) {
        this.context = context;
        this.nameFoodStrings = nameFoodStrings;
        this.tableStrings = tableStrings;
        this.itemStrings = itemStrings;
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
        View view = layoutInflater.inflate(R.layout.listview_chef, parent, false);

        TextView nameFoodTextView = view.findViewById(R.id.txtNamefood);
        TextView tableTextView = view.findViewById(R.id.txtNameTable);
        TextView itemTextView = view.findViewById(R.id.txtItem);
        TextView statusTextView = view.findViewById(R.id.txtStatus);

        nameFoodTextView.setText(nameFoodStrings[position]);
        tableTextView.setText(tableStrings[position]);
        itemTextView.setText(itemStrings[position]);
        statusTextView.setText(strings[Integer.parseInt(statusStrings[position])]);



        return view;
    }
}
