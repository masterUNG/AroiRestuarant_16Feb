package janjira.jiraporn.yonlada.aroirestuarant.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import janjira.jiraporn.yonlada.aroirestuarant.R;
import janjira.jiraporn.yonlada.aroirestuarant.utility.GetOrderWhereDate;
import janjira.jiraporn.yonlada.aroirestuarant.utility.MyConstanct;

public class ChefFragment extends Fragment{

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