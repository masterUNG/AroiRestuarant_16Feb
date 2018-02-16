package janjira.jiraporn.yonlada.aroirestuarant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import janjira.jiraporn.yonlada.aroirestuarant.MainActivity;
import janjira.jiraporn.yonlada.aroirestuarant.R;

/**
 * Created by ASUS on 10/16/2017.
 */

public class AuthenFragment extends Fragment{
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create Toolber

        createToolber();

    }  // Main Method

    private void createToolber() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarAuthen);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).setTitle(getString(R.string.title_authen));
        ((MainActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//ทำงานของลูกศร
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();//กลับไปยังหน้าแรกเดิม
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authen,container,false);
        return view;
    }
}  // Main Class

