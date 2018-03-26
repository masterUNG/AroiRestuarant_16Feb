package janjira.jiraporn.yonlada.aroirestuarant.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import janjira.jiraporn.yonlada.aroirestuarant.R;

/**
 * Created by masterung on 21/3/2018 AD.
 */

public class TableFragment extends Fragment implements View.OnClickListener {

    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5,
            imageView6, imageView7, imageView8, imageView9;
    private boolean statusABoolean = true;
    private boolean[] statusBooleans = new boolean[]{true, true, true, true, true, true, true, true, true};

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Initial View
        initialView();

//        Image Controller
        imageController();


    }   // Main Method

    private void imageController() {
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        imageView6.setOnClickListener(this);
        imageView7.setOnClickListener(this);
        imageView8.setOnClickListener(this);
        imageView9.setOnClickListener(this);
    }

    private void initialView() {
        imageView1 = getView().findViewById(R.id.imvTable1);
        imageView2 = getView().findViewById(R.id.imvTable2);
        imageView3 = getView().findViewById(R.id.imvTable3);
        imageView4 = getView().findViewById(R.id.imvTable4);
        imageView5 = getView().findViewById(R.id.imvTable5);
        imageView6 = getView().findViewById(R.id.imvTable6);
        imageView7 = getView().findViewById(R.id.imvTable7);
        imageView8 = getView().findViewById(R.id.imvTable8);
        imageView9 = getView().findViewById(R.id.imvTable9);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {

        Log.d("27MarchV1", "statusABoolean ==> " + statusABoolean);

        if (v == imageView1) {
            if (statusBooleans[0] && statusABoolean) {
                comfireTable("Table1", imageView1);
            }
        }
        if (v == imageView2) {
            if (statusBooleans[1] && statusABoolean) {
                comfireTable("Table1", imageView2);
            }
        }
        if (v == imageView3) {
            if (statusBooleans[2] && statusABoolean) {
                comfireTable("Table1", imageView3);
            }
        }
        if (v == imageView4) {
            if (statusBooleans[3] && statusABoolean) {
                comfireTable("Table1", imageView4);
            }
        }
        if (v == imageView5) {
            if (statusBooleans[4] && statusABoolean) {
                comfireTable("Table2", imageView5);
            }
        }
        if (v == imageView6) {
            if (statusBooleans[5] && statusABoolean) {
                comfireTable("Table3", imageView6);
            }
        }
        if (v == imageView7) {
            if (statusBooleans[6] && statusABoolean) {
                comfireTable("Table4", imageView7);
            }
        }
        if (v == imageView8) {
            if (statusBooleans[7] && statusABoolean) {
                comfireTable("Table5", imageView8);
            }
        }
        if (v == imageView9) {
            if (statusBooleans[8] && statusABoolean) {
                comfireTable("Table6", imageView9);
            }
        }

    }   // onClick

    private void comfireTable(String tableString, final ImageView imageView) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setIcon(R.drawable.heart48);
        builder.setTitle("Choose Table");
        builder.setMessage("You Choose " + tableString);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                imageView.setImageResource(R.drawable.red_table);
                statusABoolean = false;
            }
        });
        builder.show();

    }
}   // Main Class
