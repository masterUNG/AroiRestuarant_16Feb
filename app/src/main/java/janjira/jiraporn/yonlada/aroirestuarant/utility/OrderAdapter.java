package janjira.jiraporn.yonlada.aroirestuarant.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import janjira.jiraporn.yonlada.aroirestuarant.R;

/**
 * Created by masterung on 22/12/2017 AD.
 */

public class OrderAdapter extends BaseAdapter {

    private Context context;
    private String[] nameFoodStrings, priceStrings, itemStrings;
    private TextView numTextView, nameFoodTextView, priceTextView,
            itemTextView, sumTextView;
    private int anInt = 0;

    public OrderAdapter(Context context, String[] nameFoodStrings, String[] priceStrings, String[] itemStrings) {
        this.context = context;
        this.nameFoodStrings = nameFoodStrings;
        this.priceStrings = priceStrings;
        this.itemStrings = itemStrings;
    }

    @Override
    public int getCount() {
        return nameFoodStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.listview_food_order, viewGroup, false);

        numTextView = view1.findViewById(R.id.txtNum);
        nameFoodTextView = view1.findViewById(R.id.txtNamefood);
        priceTextView = view1.findViewById(R.id.txtPrice);
        itemTextView = view1.findViewById(R.id.txtItem);
        sumTextView = view1.findViewById(R.id.txtSum);

        numTextView.setText(Integer.toString(anInt+=1));

        nameFoodTextView.setText(nameFoodStrings[i]);

        priceTextView.setText(priceStrings[i]);

        itemTextView.setText(itemStrings[i]);

        int intSum = Integer.parseInt(priceStrings[i]) * Integer.parseInt(itemStrings[i]);

        sumTextView.setText(Integer.toString(intSum));


        return view1;
    }
}
