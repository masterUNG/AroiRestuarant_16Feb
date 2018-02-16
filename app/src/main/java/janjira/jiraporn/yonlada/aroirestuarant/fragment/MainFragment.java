package janjira.jiraporn.yonlada.aroirestuarant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Member;

import janjira.jiraporn.yonlada.aroirestuarant.MainActivity;
import janjira.jiraporn.yonlada.aroirestuarant.R;
import janjira.jiraporn.yonlada.aroirestuarant.SerciveOrderActivity;
import janjira.jiraporn.yonlada.aroirestuarant.utility.GetAllData;
import janjira.jiraporn.yonlada.aroirestuarant.utility.MyAdapter;
import janjira.jiraporn.yonlada.aroirestuarant.utility.MyConstanct;

/**
 * Created by ASUS on 10/16/2017.
 */

public class MainFragment extends Fragment {

    //    Explicit
    private String tag = "30novV1";
    private int indexAnInt = 0;


    public static MainFragment mainInstance(int index) {

        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Index", index);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Receive index from Activity
        indexAnInt = getArguments().getInt("Index");
        Log.d(tag, "Index Receive ==> " + indexAnInt);

//        Create ListView
        createListView();

    }  // Main Method

    private void createListView() {
        ListView listView = getView().findViewById(R.id.livShowPromotion);
        MyConstanct myConstanct = new MyConstanct();
        String[] columnUserStrings = myConstanct.getColumnFood();
        String tag = "15novV1";
        String[] categoryStrings = myConstanct.getCategoryStrings();

        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(categoryStrings[indexAnInt], myConstanct.getUrlPromotionString());
            String resultJSON = getAllData.get();
            Log.d(tag, "JSON ==> " + resultJSON);

            JSONArray jsonArray = new JSONArray(resultJSON);

            final String[] titleStrings = new String[jsonArray.length()];
            final String[] detailStrings = new String[jsonArray.length()];
            final String[] iconStrings = new String[jsonArray.length()];
            final String[] catStrings = new String[jsonArray.length()];
            final String[] priceStrings = new String[jsonArray.length()];


            for (int i = 0; i < jsonArray.length(); i += 1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                titleStrings[i] = jsonObject.getString(columnUserStrings[2]);
                detailStrings[i] = jsonObject.getString(columnUserStrings[4]);
                iconStrings[i] = jsonObject.getString(columnUserStrings[5]);
                catStrings[i] = jsonObject.getString(columnUserStrings[1]);
                priceStrings[i] = jsonObject.getString(columnUserStrings[3]);

            }


            MyAdapter myAdapter = new MyAdapter(getActivity(),
                    titleStrings, detailStrings, iconStrings);
            listView.setAdapter(myAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                    Intent to ServiceOrder
                    Intent intent = new Intent(getActivity(), SerciveOrderActivity.class);
                    intent.putExtra("NameFood", titleStrings[position]);
                    intent.putExtra("Category", catStrings[position]);
                    intent.putExtra("ImagePath", iconStrings[position]);
                    intent.putExtra("Price", priceStrings[position]);
                    intent.putExtra("Detail", detailStrings[position]);
                    startActivity(intent);

                }
            });

        } catch (Exception e) {
            Log.d(tag, "e ==> " + e.toString());
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
} // Main Class
