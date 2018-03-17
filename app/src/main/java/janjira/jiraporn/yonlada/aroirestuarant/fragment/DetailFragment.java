package janjira.jiraporn.yonlada.aroirestuarant.fragment;

import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import janjira.jiraporn.yonlada.aroirestuarant.R;
import janjira.jiraporn.yonlada.aroirestuarant.SerciveOrderActivity;
import janjira.jiraporn.yonlada.aroirestuarant.utility.MyManage;

/**
 * Created by masterung on 15/12/2017 AD.
 */

public class DetailFragment extends Fragment {

    private String nameFoodString, categoryString, imagePathString, priceString, detailString;

    public static DetailFragment detailInstance(String nameFoodString,
                                                String categoryString,
                                                String imagePathString,
                                                String priceString,
                                                String detailString) {

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NameFood", nameFoodString);
        bundle.putString("Category", categoryString);
        bundle.putString("ImagePath", imagePathString);
        bundle.putString("Price", priceString);
        bundle.putString("Detail", detailString);
        detailFragment.setArguments(bundle);
        return detailFragment;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get Value From Argument
        getValueFromArgument();

//        Show View
        showView();

//        Create Toolbar
        createToolbar();


    }   // Main Method

    private void chooseItemOrder() {

        final int[] itemAInt = {1};

        CharSequence[] charSequences = new CharSequence[]{"1 Set", "2 Set", "3 Set", "4 Set", "5 Set"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
        MyManage myManage = new MyManage(getActivity());
        myManage.addSQLite(nameFoodString, priceString, itemString);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenfragmentService, new ListOrderFragment())
                .commit();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemMenuOrder) {
            chooseItemOrder();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_service, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarDetail);
        ((SerciveOrderActivity) getActivity()).setSupportActionBar(toolbar);

        ((SerciveOrderActivity) getActivity()).getSupportActionBar().setTitle(nameFoodString);
        ((SerciveOrderActivity) getActivity()).getSupportActionBar().setSubtitle(categoryString);

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

    private void getValueFromArgument() {
        nameFoodString = getArguments().getString("NameFood");
        categoryString = getArguments().getString("Category");
        imagePathString = getArguments().getString("ImagePath");
        priceString = getArguments().getString("Price");
        detailString = getArguments().getString("Detail");
    }

    private void showView() {

        TextView nameFoodTextView = getView().findViewById(R.id.txtNamefood);
        nameFoodTextView.setText(nameFoodString + "\n" + categoryString);

        ImageView imageView = getView().findViewById(R.id.imvFood);
        Picasso.with(getActivity()).load(imagePathString).into(imageView);

        TextView priceTextView = getView().findViewById(R.id.txtPrice);
        priceTextView.setText(priceString + " THB.");

        TextView detailTextView = getView().findViewById(R.id.txtDetail);
        detailTextView.setText(detailString);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }
}
