package janjira.jiraporn.yonlada.aroirestuarant.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import janjira.jiraporn.yonlada.aroirestuarant.MainActivity;
import janjira.jiraporn.yonlada.aroirestuarant.R;
import janjira.jiraporn.yonlada.aroirestuarant.SerciveOrderActivity;
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

        createToolbar();

    }   // Main Method

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.item_add_order) {
            getActivity().finish();
            return true;
        }

        if (item.getItemId() == R.id.item_confirm_order) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("Status", true);
            startActivity(intent);
            getActivity().finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_confirm_order, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarListOrder);
        ((SerciveOrderActivity) getActivity()).setSupportActionBar(toolbar);

        ((SerciveOrderActivity) getActivity()).getSupportActionBar().setTitle("List Order");
        ((SerciveOrderActivity) getActivity()).getSupportActionBar().setSubtitle("Please Wait Few Minus");

        ((SerciveOrderActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((SerciveOrderActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        setHasOptionsMenu(true);

    }

    private void createListView() {
        ListView listView = getView().findViewById(R.id.listViewOrder);

        try {

            final SQLiteDatabase sqLiteDatabase = getActivity()
                    .openOrCreateDatabase(MyOpenHelper.databaseName, Context.MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orderTABLE", null);
            cursor.moveToFirst();

            final String[] nameFoodStrings = new String[cursor.getCount()];
            String[] priceStrings = new String[cursor.getCount()];
            String[] itemStrings = new String[cursor.getCount()];
            final String[] idStrings = new String[cursor.getCount()];

            for (int i=0; i<cursor.getCount(); i+=1) {

                idStrings[i] = cursor.getString(0);
                nameFoodStrings[i] = cursor.getString(1);
                priceStrings[i] = cursor.getString(2);
                itemStrings[i] = cursor.getString(3);

                Log.d("22DecV2", "nameFood[" + i + "] ==> " + nameFoodStrings[i]);

                cursor.moveToNext();
            }

            OrderAdapter orderAdapter = new OrderAdapter(getActivity(),
                    nameFoodStrings, priceStrings, itemStrings);
            listView.setAdapter(orderAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setCancelable(false);
                    builder.setTitle("Are You Sure ?");
                    builder.setMessage("You Want Delete This Item ?");
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sqLiteDatabase.delete("orderTABLE",
                                    "id" + "=" + idStrings[position],
                                    null);
                            createListView();
                            dialog.dismiss();
                        }
                    });
                    builder.show();

                }
            });

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
