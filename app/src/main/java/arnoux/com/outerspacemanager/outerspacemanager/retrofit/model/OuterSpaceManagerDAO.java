package arnoux.com.outerspacemanager.outerspacemanager.retrofit.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import arnoux.com.outerspacemanager.outerspacemanager.Entity.BuildingDB;


/**
 * Created by White on 20/03/2017.
 */

public class OuterSpaceManagerDAO {

    // Database fields
    private SQLiteDatabase database;
    private OuterSpaceManagerDB dbHelper;
    private String[] allColumns = { OuterSpaceManagerDB.BUILDING_ID,OuterSpaceManagerDB.BUILDING_NAME, OuterSpaceManagerDB.BUILDING_LEVEL,
    OuterSpaceManagerDB.BUILDING_TIMETOBUILDBYLEVEL, OuterSpaceManagerDB.BUILDING_TIMEBUILDINGLAUNCHED};

    public OuterSpaceManagerDAO(Context context) {
        dbHelper = new OuterSpaceManagerDB(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    public BuildingDB createBuilding(int id, String name, int level, int timeToBuildByLevel, long timeBuildingLaunched) {
        ContentValues values = new ContentValues();
        values.put(OuterSpaceManagerDB.BUILDING_ID, id);
        values.put(OuterSpaceManagerDB.BUILDING_NAME, name);
        values.put(OuterSpaceManagerDB.BUILDING_LEVEL, level);
        values.put(OuterSpaceManagerDB.BUILDING_TIMETOBUILDBYLEVEL, timeToBuildByLevel);
        values.put(OuterSpaceManagerDB.BUILDING_TIMEBUILDINGLAUNCHED, timeBuildingLaunched);
        database.insert(OuterSpaceManagerDB.BUILDING_TABLE_NAME, null,
                values);
        Cursor cursor = database.query(OuterSpaceManagerDB.BUILDING_TABLE_NAME,
                allColumns, OuterSpaceManagerDB.BUILDING_ID + "=?" , new String[]{String.valueOf(id)},
                null, null, null);
        cursor.moveToFirst();
        BuildingDB building = cursorToBuilding(cursor);
        cursor.close();
        return building;
    }

    public List<BuildingDB> getAllBuildings() {
        List<BuildingDB> buildings = new ArrayList<>();
        Cursor cursor = database.query(OuterSpaceManagerDB.BUILDING_TABLE_NAME,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BuildingDB building = cursorToBuilding(cursor);
            buildings.add(building);
            cursor.moveToNext();
        }

        cursor.close();
        return buildings;
    }

    private BuildingDB cursorToBuilding(Cursor cursor) {
        BuildingDB comment = new BuildingDB();
        comment.setId(cursor.getInt(0));
        comment.setName(cursor.getString(1));
        comment.setLevel(cursor.getInt(2));
        comment.setTimeToBuildByLevel(cursor.getInt(3));
        comment.setTimeBuildingLaunched(cursor.getLong(4));
        return comment;
    }
}
