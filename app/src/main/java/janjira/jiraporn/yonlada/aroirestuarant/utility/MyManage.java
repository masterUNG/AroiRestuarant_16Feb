package janjira.jiraporn.yonlada.aroirestuarant.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterung on 22/12/2017 AD.
 */

public class MyManage {

    private Context context;
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MyManage(Context context) {
        this.context = context;
        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();
    }

    public long addSQLite(String nameFoodString,
                          String priceFoodString,
                          String itemFoodString) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("NameFood", nameFoodString);
        contentValues.put("Price", priceFoodString);
        contentValues.put("Item", itemFoodString);
        return sqLiteDatabase.insert("orderTABLE", null, contentValues);

    }



}
