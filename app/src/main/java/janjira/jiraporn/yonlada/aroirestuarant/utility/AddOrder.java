package janjira.jiraporn.yonlada.aroirestuarant.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by masterung on 17/3/2018 AD.
 */

public class AddOrder extends AsyncTask<String, Void, String>{

    private Context context;

    public AddOrder(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        MyConstanct myConstanct = new MyConstanct();
        String[] columnStrings1 = myConstanct.getColumnOrder();


        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add(columnStrings1[1], strings[0])
                    .add(columnStrings1[2], strings[2])
                    .add(columnStrings1[3], strings[3])
                    .add(columnStrings1[4], strings[4])
                    .add(columnStrings1[5], strings[5])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[6]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
