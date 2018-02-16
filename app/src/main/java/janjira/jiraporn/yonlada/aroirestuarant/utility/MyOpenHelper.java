package janjira.jiraporn.yonlada.aroirestuarant.utility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by masterung on 22/12/2017 AD.
 */

public class MyOpenHelper extends SQLiteOpenHelper{

    private Context context;
    public static final String databaseName = "Mua.db";
    private static final int databaseVerion = 1;
    private static final String detailOrderTable = "CREATE TABLE orderTABLE (" +
            "id INTEGER PRIMARY KEY," +
            "NameFood TEXT," +
            "Price TEXT," +
            "Item TEXT);";

    public MyOpenHelper(Context context) {
        super(context, databaseName, null, databaseVerion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(detailOrderTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
