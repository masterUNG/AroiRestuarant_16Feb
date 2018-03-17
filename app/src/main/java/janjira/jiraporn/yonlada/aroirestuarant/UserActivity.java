package janjira.jiraporn.yonlada.aroirestuarant;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import janjira.jiraporn.yonlada.aroirestuarant.fragment.MainFragment;
import janjira.jiraporn.yonlada.aroirestuarant.utility.MyConstanct;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private String loginArrayString;
    private String[] loginStrings;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private TextView homeTextView, singleFoodTextView, sopeTextView,
            burnTextView, setFoodTextView, nudleTextView, chiliTextView,
            streamTextView, candyTextView, fruidTextView, drinkTextView;
    private int indexAnInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

//        GetValue From SharedPreference
        getVauleFromSharedPreferance();

        addFragement(savedInstanceState);

//        Creage Toolbar
        creageToolbar();

        textViewController();

    }   // Main Method

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    private void creageToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarAdmin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(loginStrings[1]);
        getSupportActionBar().setSubtitle("Owner");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(UserActivity.this,
                drawerLayout, R.string.open, R.string.close);

    }

    private void addFragement(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentUserFragment, MainFragment.mainInstance(indexAnInt))
                    .commit();
        }
    }

    private void textViewController() {

//        Initial View
        homeTextView = findViewById(R.id.txtHome);
        singleFoodTextView = findViewById(R.id.txtSingleFood);
        sopeTextView = findViewById(R.id.txtSoup);
        burnTextView = findViewById(R.id.txtburn);
        setFoodTextView = findViewById(R.id.txtSetFood);
        nudleTextView = findViewById(R.id.txtNudle);
        chiliTextView = findViewById(R.id.txtChili);
        streamTextView = findViewById(R.id.txtsteam);
        candyTextView = findViewById(R.id.txtCandy);
        fruidTextView = findViewById(R.id.txtFruit);
        drinkTextView = findViewById(R.id.txtDrink);

//        Show View
        MyConstanct myConstanct = new MyConstanct();
        String[] categoryStrings = myConstanct.getCategoryStrings();
        homeTextView.setText(categoryStrings[0]);
        singleFoodTextView.setText(categoryStrings[1]);
        sopeTextView.setText(categoryStrings[2]);
        burnTextView.setText(categoryStrings[3]);
        setFoodTextView.setText(categoryStrings[4]);
        nudleTextView.setText(categoryStrings[5]);
        chiliTextView.setText(categoryStrings[6]);
        streamTextView.setText(categoryStrings[7]);
        candyTextView.setText(categoryStrings[8]);
        fruidTextView.setText(categoryStrings[9]);
        drinkTextView.setText(categoryStrings[10]);

//        Listener
        homeTextView.setOnClickListener(UserActivity.this);
        singleFoodTextView.setOnClickListener(UserActivity.this);
        sopeTextView.setOnClickListener(UserActivity.this);
        burnTextView.setOnClickListener(UserActivity.this);
        setFoodTextView.setOnClickListener(UserActivity.this);
        nudleTextView.setOnClickListener(UserActivity.this);
        chiliTextView.setOnClickListener(UserActivity.this);
        streamTextView.setOnClickListener(UserActivity.this);
        candyTextView.setOnClickListener(UserActivity.this);
        fruidTextView.setOnClickListener(UserActivity.this);
        drinkTextView.setOnClickListener(UserActivity.this);

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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.txtHome:
                indexAnInt = 0;
                break;
            case R.id.txtSingleFood:
                indexAnInt = 1;
                break;
            case R.id.txtSoup:
                indexAnInt = 2;
                break;
            case R.id.txtburn:
                indexAnInt = 3;
                break;
            case R.id.txtSetFood:
                indexAnInt = 4;
                break;
            case R.id.txtNudle:
                indexAnInt = 5;
                break;
            case R.id.txtChili:
                indexAnInt = 6;
                break;
            case R.id.txtsteam:
                indexAnInt = 7;
                break;
            case R.id.txtCandy:
                indexAnInt = 8;
                break;
            case R.id.txtFruit:
                indexAnInt = 9;
                break;
            case R.id.txtDrink:
                indexAnInt = 10;
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentUserFragment, MainFragment.mainInstance(indexAnInt)).commit();
        Log.d("30novV1", "You Choose index ==> " + indexAnInt);
        drawerLayout.closeDrawers();
    }
}
