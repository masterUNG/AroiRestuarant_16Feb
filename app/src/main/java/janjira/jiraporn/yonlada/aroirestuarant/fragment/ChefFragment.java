package janjira.jiraporn.yonlada.aroirestuarant.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import janjira.jiraporn.yonlada.aroirestuarant.R;
import janjira.jiraporn.yonlada.aroirestuarant.utility.ChefAdapter;
import janjira.jiraporn.yonlada.aroirestuarant.utility.GetOrderWhereDate;
import janjira.jiraporn.yonlada.aroirestuarant.utility.MyConstanct;

public class ChefFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create ListView
        createListView();
    }

    private void createListView() {
        ListView listView = getView().findViewById(R.id.listViewChef);
        MyConstanct myConstanct = new MyConstanct();

        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateString = dateFormat.format(calendar.getTime());

        try {

            GetOrderWhereDate getOrderWhereDate = new GetOrderWhereDate(getActivity());
            getOrderWhereDate.execute(currentDateString, myConstanct.getUrlGetOrderWhereDate());

            String jsonString = getOrderWhereDate.get();
            JSONArray jsonArray = new JSONArray(jsonString);

            String[] nameFoodStrings = new String[jsonArray.length()];
            String[] tableStrings = new String[jsonArray.length()];
            String[] itemeStrings = new String[jsonArray.length()];
            String[] statusStrings = new String[jsonArray.length()];
            String[] columnStrings = myConstanct.getColumnOrder();

            for (int i = 0; i < jsonArray.length(); i += 1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameFoodStrings[i] = jsonObject.getString(columnStrings[3]);
                tableStrings[i] = "User-8";
                itemeStrings[i] = jsonObject.getString(columnStrings[4]);
                statusStrings[i] = jsonObject.getString(columnStrings[5]);
            }

            ChefAdapter chefAdapter = new ChefAdapter(getActivity(), nameFoodStrings, tableStrings, itemeStrings, statusStrings);
            listView.setAdapter(chefAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chef, container, false);
        return view;
    }
}