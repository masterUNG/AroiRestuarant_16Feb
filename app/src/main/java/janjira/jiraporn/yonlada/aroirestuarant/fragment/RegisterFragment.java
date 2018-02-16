package janjira.jiraporn.yonlada.aroirestuarant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import janjira.jiraporn.yonlada.aroirestuarant.R;
import janjira.jiraporn.yonlada.aroirestuarant.utility.MyConstanct;
import janjira.jiraporn.yonlada.aroirestuarant.utility.PostNewUser;

/**
 * Created by masterung on 15/12/2017 AD.
 */

public class RegisterFragment extends Fragment {

    private String nameString, userString, passwordString;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controller
        registerController();

    }

    private void registerController() {

        Button button = getView().findViewById(R.id.btnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameEditText = getView().findViewById(R.id.edtName);
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                if (nameString.isEmpty() || userString.isEmpty() || passwordString.isEmpty()) {
                    Toast.makeText(getActivity(), "Have Space", Toast.LENGTH_SHORT).show();
                } else {
                    uploadNewUser();
                }


            }
        });

    }

    private void uploadNewUser() {
        try {

            MyConstanct myConstanct = new MyConstanct();
            PostNewUser postNewUser = new PostNewUser(getActivity());
            postNewUser.execute(nameString, userString, passwordString, myConstanct.getUrlAddUserString());

            String result = postNewUser.get();
            if (Boolean.parseBoolean(result)) {
                getActivity().getSupportFragmentManager().popBackStack();
            } else {
                Toast.makeText(getActivity(), "Cannot Upload", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}
