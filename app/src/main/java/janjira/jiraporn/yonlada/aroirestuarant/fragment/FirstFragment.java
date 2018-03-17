package janjira.jiraporn.yonlada.aroirestuarant.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import janjira.jiraporn.yonlada.aroirestuarant.AdminActivity;
import janjira.jiraporn.yonlada.aroirestuarant.ChefActivity;
import janjira.jiraporn.yonlada.aroirestuarant.MainActivity;
import janjira.jiraporn.yonlada.aroirestuarant.OwnerActivity;
import janjira.jiraporn.yonlada.aroirestuarant.R;
import janjira.jiraporn.yonlada.aroirestuarant.UserActivity;
import janjira.jiraporn.yonlada.aroirestuarant.WaiterActivity;
import janjira.jiraporn.yonlada.aroirestuarant.utility.LoadAllJSON;
import janjira.jiraporn.yonlada.aroirestuarant.utility.MyConstanct;

/**
 * Created by masterung on 16/2/2018 AD.
 */

public class FirstFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Login Controller
        loginController();

//        Register Controller
        registerController();

    }   // Main Method

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentFirstFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();


//                Check Space
                if (userString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    myAlert("Please Fill All Every Blank");

                } else {

//                    No Space
                    try {

                        MyConstanct myConstanct = new MyConstanct();
                        boolean userBoolean = true; // True ==> User False
                        LoadAllJSON loadAllJSON = new LoadAllJSON(getActivity());
                        loadAllJSON.execute(myConstanct.getUrlGetAllUserString());
                        String[] columnStrings = new String[]{"id", "Name", "User", "Password", "Category"};
                        String[] loginStrings = new String[columnStrings.length];

                        String resultJSON = loadAllJSON.get();
                        Log.d("test1", "JSON ==> " + resultJSON);

                        JSONArray jsonArray = new JSONArray(resultJSON);

                        for (int i = 0; i < jsonArray.length(); i += 1) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (userString.equals(jsonObject.getString("User"))) {

                                userBoolean = false;
                                for (int i1 = 0; i1 < loginStrings.length; i1 += 1) {
                                    loginStrings[i1] = jsonObject.getString(columnStrings[i1]);
                                }   // for

                            }   // if1

                        }   // for

                        if (userBoolean) {
                            myAlert("No This User in my Database");
                        } else if (!passwordString.equals(loginStrings[3])) {
                            myAlert("Password False");
                        } else {
                            myAlert("Welcome " + loginStrings[1]);
                            String loginArrayListString = makeArrayList(loginStrings);
                            Log.d("5MarchV1", "loginArrayListString ==> " + loginArrayListString);

                            saveLoginOnPreferences(loginArrayListString);

                            switch (Integer.parseInt(loginStrings[4])) {
                                case 0:
                                    Intent intent = new Intent(getActivity(), OwnerActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                    break;
                                case 1:
                                    Intent intent1 = new Intent(getActivity(), ChefActivity.class);
                                    startActivity(intent1);
                                    getActivity().finish();
                                    break;
                                case 2:
                                    Intent intent2 = new Intent(getActivity(), WaiterActivity.class);
                                    startActivity(intent2);
                                    getActivity().finish();
                                    break;
                                case 3:
                                    Intent intent3 = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent3);
                                    getActivity().finish();
                                    break;
                            }


                        }   // if


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }   // if


            }   // onClick
        });
    }

    private void saveLoginOnPreferences(String loginArrayListString) {

        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LoginArray", loginArrayListString);
        editor.commit();

    }

    private String makeArrayList(String[] loginStrings) {

        String resultString = null;
        ArrayList<String> stringArrayList = new ArrayList<>();

        for (int i = 0; i < loginStrings.length; i += 1) {
            stringArrayList.add(loginStrings[i]);
        }

        resultString = stringArrayList.toString();
        return resultString;
    }

    private void myAlert(String messageString) {
        Toast.makeText(getActivity(), messageString, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }
}   // Main Class
