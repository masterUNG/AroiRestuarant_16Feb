package janjira.jiraporn.yonlada.aroirestuarant;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import janjira.jiraporn.yonlada.aroirestuarant.fragment.DetailFragment;
import janjira.jiraporn.yonlada.aroirestuarant.fragment.ListOrderFragment;
import janjira.jiraporn.yonlada.aroirestuarant.fragment.RegisterFragment;
import janjira.jiraporn.yonlada.aroirestuarant.utility.MyManage;

public class SerciveOrderActivity extends AppCompatActivity {

    private String nameFoodString, categoryString, imagePathString, priceString, detailString;

//    Explicit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sercive_order);

//        Get Value Frome Intent
        getValueFromeIntent();

//        Create Toolbar
        createToolbar();

//        Add Fragment
        addFragment(savedInstanceState);

    }   // Main Method

    private void getValueFromeIntent() {
        nameFoodString = getIntent().getStringExtra("NameFood");
        categoryString = getIntent().getStringExtra("Category");
        imagePathString = getIntent().getStringExtra("ImagePath");
        priceString = getIntent().getStringExtra("Price");
        detailString = getIntent().getStringExtra("Detail");
    }

    private void addFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenfragmentService, DetailFragment.detailInstance(
                            nameFoodString, categoryString,
                            imagePathString, priceString, detailString))
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_service, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemMenuOrder) {

            chooseItemOrder();

        }

        if (item.getItemId() == R.id.itemMenuNewRegister) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenfragmentService, new RegisterFragment())
                    .addToBackStack(null)
                    .commit();
        }

        return super.onOptionsItemSelected(item);
    }

    private void chooseItemOrder() {

        final int[] itemAInt = {1};

        CharSequence[] charSequences = new CharSequence[]{"1 Set", "2 Set", "3 Set", "4 Set", "5 Set"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(SerciveOrderActivity.this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_fruit);
        builder.setTitle("Please Choose Item");
        builder.setSingleChoiceItems(charSequences, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                itemAInt[0] = itemAInt[0] + i;
                Log.d("22DecV1", "Item ==> " + itemAInt[0]);
                addDataToSQLite(itemAInt[0]);
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }

    private void addDataToSQLite(int itemAint) {

        String itemString = Integer.toString(itemAint);
        MyManage myManage = new MyManage(SerciveOrderActivity.this);
        myManage.addSQLite(nameFoodString, priceString, itemString);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenfragmentService, new ListOrderFragment())
                .commit();



    }

    private void createToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbarService);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.service));

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}   // Main Class













