package janjira.jiraporn.yonlada.aroirestuarant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import janjira.jiraporn.yonlada.aroirestuarant.R;

/**
 * Created by masterung on 20/3/2018 AD.
 */

public class ScoreAnCommentFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Confirm Controller
        confirmController();


    }   // Main Method

    private void confirmController() {
        Button button = getView().findViewById(R.id.btnConfirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentFragmentMain, new CheckBillFragment()).commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_comment, container, false);
        return view;
    }
}
