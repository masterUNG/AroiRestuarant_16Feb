package janjira.jiraporn.yonlada.aroirestuarant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import janjira.jiraporn.yonlada.aroirestuarant.R;

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



    }   // Main Method

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
