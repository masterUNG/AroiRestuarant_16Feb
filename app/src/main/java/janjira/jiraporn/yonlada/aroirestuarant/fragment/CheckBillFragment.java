package janjira.jiraporn.yonlada.aroirestuarant.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import janjira.jiraporn.yonlada.aroirestuarant.R;
import janjira.jiraporn.yonlada.aroirestuarant.utility.GetFoodWhereName;
import janjira.jiraporn.yonlada.aroirestuarant.utility.GetOrderWhereDateAnOrderID;
import janjira.jiraporn.yonlada.aroirestuarant.utility.MyConstanct;
import janjira.jiraporn.yonlada.aroirestuarant.utility.OrderAdapter;

/**
 * Created by masterung on 20/3/2018 AD.
 */

public class CheckBillFragment extends Fragment {

    private String[] loginStrings;
    private String dateString;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Show Ref
        showRef();

//        Show NameUser
        showNameUser();

//        Show Date
        showDate();

//        Create ListView
        createListView();


//        Pay Controller
        payController();


    }   // Main Method

    private void createListView() {

        ListView listView = getView().findViewById(R.id.listViewBill);
        MyConstanct myConstanct = new MyConstanct();

        try {

            GetOrderWhereDateAnOrderID getOrderWhereDateAnOrderID = new GetOrderWhereDateAnOrderID(getActivity());
            getOrderWhereDateAnOrderID.execute(dateString, "User-" + loginStrings[0], myConstanct.getUrlGetOrderWhereDateAnOrderID());
            String result = getOrderWhereDateAnOrderID.get();
            Log.d("20MarchV1", "Result ==> " + result);

            JSONArray jsonArray = new JSONArray(result);

            String[] nameFoodStrings = new String[jsonArray.length()];
            String[] itemStrings = new String[jsonArray.length()];
            String[] priceStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                nameFoodStrings[i] = jsonObject.getString("orNameFood");
                itemStrings[i] = jsonObject.getString("Item");
                priceStrings[i] = findPrice(nameFoodStrings[i]);

            }

            OrderAdapter orderAdapter = new OrderAdapter(getActivity(), nameFoodStrings,
                    priceStrings, itemStrings);

            listView.setAdapter(orderAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private String findPrice(String nameFoodString) {

        try {

            MyConstanct myConstanct = new MyConstanct();
            GetFoodWhereName getFoodWhereName = new GetFoodWhereName(getActivity());
            getFoodWhereName.execute(nameFoodString, myConstanct.getUrlGetFoodWhereName());

            String jsonString = getFoodWhereName.get();
            Log.d("21MarchV1", "JSON ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            return jsonObject.getString("Price");

        } catch (Exception e) {
            e.printStackTrace();
            return "1";
        }


    }

    private void payController() {
        Button button = getView().findViewById(R.id.btnPay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    private void showDate() {
        TextView textView = getView().findViewById(R.id.txtDate);
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateString = dateFormat.format(calendar.getTime());
        textView.setText(dateString);
    }

    private void showNameUser() {
        TextView textView = getView().findViewById(R.id.txtNameUser);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String s = sharedPreferences.getString("LoginArray", null);
        s = s.substring(1, s.length() - 1);
        loginStrings = s.split(",");

        textView.setText(loginStrings[1]);


    }

    private void showRef() {
        TextView textView = getView().findViewById(R.id.txtRef);
        Random random = new Random();
        int i = random.nextInt(1000);
        textView.setText("Ref-" + Integer.toString(i));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_bill, container, false);
        return view;
    }
}
