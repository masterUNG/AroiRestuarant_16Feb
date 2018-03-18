package janjira.jiraporn.yonlada.aroirestuarant.fragment;

import android.content.Context;
import android.content.SharedPreferences;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import janjira.jiraporn.yonlada.aroirestuarant.R;
import janjira.jiraporn.yonlada.aroirestuarant.utility.AddOrder;
import janjira.jiraporn.yonlada.aroirestuarant.utility.MyConstanct;
import janjira.jiraporn.yonlada.aroirestuarant.utility.MyOpenHelper;

/**
 * Created by masterung on 17/3/2018 AD.
 */

public class StatusOrderFragment extends Fragment {

    private String[] loginStrings;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Read All SQlite and Delete
        readAllSQLiteAndDelete();

//        Create ListView
        createListView();


    }   // Main Method

    private void createListView() {

        ListView listView = getView().findViewById(R.id.listViewOrder);
        MyConstanct myConstanct = new MyConstanct();

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = dateFormat.format(calendar.getTime());



        try {





        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void readAllSQLiteAndDelete() {
        try {

            SharedPreferences sharedPreferences = getActivity()
                    .getSharedPreferences("Login", Context.MODE_PRIVATE);
            String loginArrayListString = sharedPreferences.getString("LoginArray", null);
            Log.d("18MarchV1", "loginArrayList ==> " + loginArrayListString);

            loginArrayListString = loginArrayListString.substring(1, loginArrayListString.length() - 1);
            loginStrings = loginArrayListString.split(",");

            Calendar calendar = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String dateString = dateFormat.format(calendar.getTime());

            SQLiteDatabase sqLiteDatabase = getActivity()
                    .openOrCreateDatabase(MyOpenHelper.databaseName,
                            Context.MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orderTABLE", null);
            cursor.moveToFirst();

            String[] nameFoodStrings = new String[cursor.getCount()];
            String[] priceStrings = new String[cursor.getCount()];
            String[] itemStrings = new String[cursor.getCount()];

            MyConstanct myConstanct = new MyConstanct();

            String[] valueStrings = new String[6];

            for (int i=0; i<cursor.getCount(); i+=1) {

                valueStrings[0] = dateString;
                valueStrings[1] = "User-" + loginStrings[0];
                valueStrings[2] = cursor.getString(1);
                valueStrings[3] = cursor.getString(3);
                valueStrings[4] = "0";
                valueStrings[5] = myConstanct.getUrlAddOrder();

                addValueToOrder(valueStrings);

                cursor.moveToNext();
            }   // for

            sqLiteDatabase.delete("orderTABLE", null, null);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addValueToOrder(String[] strings) {
        try {

            AddOrder addOrder = new AddOrder(getActivity());
            addOrder.execute(strings);
            String resultString = addOrder.get();
            Log.d("18MarchV1", "Result ==> " + resultString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status_order, container, false);
        return view;
    }   // Main Method

}   // Main Class
