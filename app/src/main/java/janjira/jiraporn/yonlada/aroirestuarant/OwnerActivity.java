package janjira.jiraporn.yonlada.aroirestuarant;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import janjira.jiraporn.yonlada.aroirestuarant.fragment.MainFragment;

public class OwnerActivity extends AppCompatActivity {

    private String loginArrayString;
    private String[] loginStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

//        GetValue From SharedPreference
        getVauleFromSharedPreferance();

//        Creage Toolbar
        creageToolbar();

//        Add Fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentOwnerFragment, MainFragment.mainInstance(1)).commit();
        }

    }   // Main Method

    private void creageToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarOwner);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(loginStrings[1]);
        getSupportActionBar().setSubtitle("Owner");
    }

    private void getVauleFromSharedPreferance() {
        SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        loginArrayString = sharedPreferences.getString("LoginArray", "None");
        Log.d("5MarchV1", "LoginArray ที่รับค่ามา ==> " + loginArrayString);

        loginArrayString = loginArrayString.replace("[", "");
        loginArrayString = loginArrayString.replace("]", "");
        Log.d("5MarchV1", "LoginArray ที่ replace ค่ามา ==> " + loginArrayString);

        loginStrings = loginArrayString.split(",");
        for (int i=0; i<loginStrings.length; i+=1) {
            Log.d("5MarchV1", "loginStrings[" + i + "] ==> " + loginStrings[i]);
        }

    }
}
