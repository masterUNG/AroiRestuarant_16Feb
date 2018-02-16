package janjira.jiraporn.yonlada.aroirestuarant.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import janjira.jiraporn.yonlada.aroirestuarant.R;
import janjira.jiraporn.yonlada.aroirestuarant.utility.MyOpenHelper;
import janjira.jiraporn.yonlada.aroirestuarant.utility.OrderAdapter;

/**
 * Created by masterung on 22/12/2017 AD.
 */

public class ListOrderFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create ListView
        createListView();

    }

    private void createListView() {
        ListView listView = getView().findViewById(R.id.listViewOrder);

        try {

            SQLiteDatabase sqLiteDatabase = getActivity()
                    .openOrCreateDatabase(MyOpenHelper.databaseName, Context.MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orderTABLE", null);
            cursor.moveToFirst();

            String[] nameFoodStrings = new String[cursor.getCount()];
            String[] priceStrings = new String[cursor.getCount()];
            String[] itemStrings = new String[cursor.getCount()];

            for (int i=0; i<cursor.getCount(); i+=1) {

                nameFoodStrings[i] = cursor.getString(1);
                priceStrings[i] = cursor.getString(2);
                itemStrings[i] = cursor.getString(3);

                Log.d("22DecV2", "nameFood[" + i + "] ==> " + nameFoodStrings[i]);

                cursor.moveToNext();
            }

            OrderAdapter orderAdapter = new OrderAdapter(getActivity(),
                    nameFoodStrings, priceStrings, itemStrings);
            listView.setAdapter(orderAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_order, container, false);
        return view;
    }
}
