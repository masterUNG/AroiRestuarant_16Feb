package janjira.jiraporn.yonlada.aroirestuarant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import janjira.jiraporn.yonlada.aroirestuarant.fragment.FirstFragment;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentFirstFragment, new FirstFragment()).commit();

        }


    }   // Main Method

}   // Main Class
