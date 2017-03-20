package arnoux.com.outerspacemanager.outerspacemanager.retrofit.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by White on 20/03/2017.
 */

public class OuterSpaceManagerDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "OuterSpaceBuildings.db";
    public static final String BUILDING_TABLE_NAME = "Buildings";
    public static final String BUILDING_ID = "id";
    public static final String BUILDING_NAME = "name";
    public static final String BUILDING_LEVEL = "level";
    public static final String BUILDING_TIMETOBUILDBYLEVEL = "timetobuildbylevel";
    public static final String BUILDING_TIMEBUILDINGLAUNCHED = "timesincebuildinghavebeenlaunched";

    private static final String BUILDINGS_TABLE_CREATE = "CREATE TABLE " + BUILDING_TABLE_NAME + " (" + BUILDING_ID + " TEXT, " +
            BUILDING_NAME + " TEXT, " + BUILDING_LEVEL + " TEXT, " +
            BUILDING_TIMETOBUILDBYLEVEL + " TEXT, " + BUILDING_TIMEBUILDINGLAUNCHED + " TEXT);";

    public OuterSpaceManagerDB(Context context) {
        super(context, Environment.getExternalStorageDirectory()+"/"+DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BUILDINGS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {db.execSQL("DROP TABLE IF EXISTS " +
            BUILDING_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BUILDING_TABLE_NAME);
        onCreate(db);
    }
}

