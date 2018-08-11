package com.marvel.android.a1000salama.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import Model.Area;
import Model.Catoegry;
import Model.City;
import Model.Governrate;
import Model.SystemMessage;

/**
 * Created by ahmed on 26/01/18.
 */

public class alfSamalaSDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "alfsamala.db";
    Context context;

    public alfSamalaSDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MESSAGE_TABLE = "CREATE TABLE " + Contract.alfsalamaEntry.SystemMessagesTable + " (" +
                Contract.alfsalamaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.alfsalamaEntry.COLUMN_SystemMessages_ID + " INTEGER NOT NULL, " +
                Contract.alfsalamaEntry.COLUMN_SystemMessages_message + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_MESSAGE_TABLE);

        final String SQL_CREATE_GOV_TABLE = "CREATE TABLE " + Contract.alfsalamaEntry.GOVERNRATETABLE + " (" +
                Contract.alfsalamaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.alfsalamaEntry.COLUMN_GOVERNRATE_ID + " INTEGER NOT NULL, " +
                Contract.alfsalamaEntry.COLUMN_GOVERNRATE_NAME + " TEXT NOT NULL);";
       db.execSQL(SQL_CREATE_GOV_TABLE);


        final String SQL_CREATE_CITY_TABLE = "CREATE TABLE " + Contract.alfsalamaEntry.CITYETABLE + " (" +
                Contract.alfsalamaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.alfsalamaEntry.COLUMN_CITY_ID + " INTEGER NOT NULL, " +
                Contract.alfsalamaEntry.COLUMN_CITY_GOV_ID + " INTEGER NOT NULL, "+
                Contract.alfsalamaEntry.COLUMN_CITY_NAME + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_CITY_TABLE);


        final String SQL_CREATE_AREA_TABLE = "CREATE TABLE " + Contract.alfsalamaEntry.AREATABLE + " (" +
                Contract.alfsalamaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.alfsalamaEntry.COLUMN_AREA_ID + " INTEGER NOT NULL, " +
                Contract.alfsalamaEntry.COLUMN_AREA_CITY_ID + " INTEGER NOT NULL, "+
                Contract.alfsalamaEntry.COLUMN_AREA_NAME + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_AREA_TABLE);




        final String SQL_CREATE_CAT_TABLE = "CREATE TABLE " + Contract.alfsalamaEntry.CATTable + " (" +
                Contract.alfsalamaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.alfsalamaEntry.COLUMN_CAT_IDID + " INTEGER NOT NULL, "+
                Contract.alfsalamaEntry.COLUMN_CAT_NAME + " TEXT NOT NULL ,"+
                Contract.alfsalamaEntry.COLUMN_CAT_ID + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_CAT_TABLE);




        final String SQL_CREATE_SP_TABLE = "CREATE TABLE " + Contract.alfsalamaEntry.SPTable + " (" +
                Contract.alfsalamaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.alfsalamaEntry.COLUMN_SystemMessages_ID + " INTEGER NOT NULL, " +
                Contract.alfsalamaEntry.COLUMN_SystemMessages_message + " TEXT NOT NULL);";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +Contract.alfsalamaEntry.SystemMessagesTable);
        db.execSQL("DROP TABLE IF EXISTS " +Contract.alfsalamaEntry.GOVERNRATETABLE);
        db.execSQL("DROP TABLE IF EXISTS " +Contract.alfsalamaEntry.CITYETABLE);
        db.execSQL("DROP TABLE IF EXISTS " +Contract.alfsalamaEntry.AREATABLE);
        db.execSQL("DROP TABLE IF EXISTS " +Contract.alfsalamaEntry.CATTable);
        onCreate(db);
    }


    public void insertSystemMessages(ContentValues values){
        SQLiteDatabase database = this.getWritableDatabase();

//        ContentValues values = new ContentValues();
//        values.put(Contract.alfsalamaEntry.COLUMN_SystemMessages_ID,systemMessage.getId());
//        values.put(Contract.alfsalamaEntry.COLUMN_SystemMessages_message,systemMessage.getMessage());
        // Inserting Row
        database.insert(Contract.alfsalamaEntry.SystemMessagesTable, null, values);
        Log.w("table", "data inseted");
        database.close(); // Closing database connection
    }

    public ArrayList<SystemMessage>getSystemMessages(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + Contract.alfsalamaEntry.SystemMessagesTable;
        ArrayList<SystemMessage> messages = new ArrayList<>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            SystemMessage message = new SystemMessage();
            message.setId(cursor.getString(1));
            message.setMessage(cursor.getString(2));
            messages.add(message);
        }

        Log.w("table", "Data selected");
        Log.w("table", messages.size() + "");

        cursor.close();
        db.close();

        return messages;
    }

    public void deleteSystemMessages() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(Contract.alfsalamaEntry.SystemMessagesTable, null, null);
        Log.w("table", "dataDeleted");
        db.close();
    }
    public SystemMessage getMessage(int messageId){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + Contract.alfsalamaEntry.SystemMessagesTable + " WHERE "
                + Contract.alfsalamaEntry.COLUMN_SystemMessages_ID + " = " + messageId;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();
        SystemMessage message = new SystemMessage();
        message.setId(cursor.getString(1));
        message.setMessage(cursor.getString(2));

        return message;
    }


    public long InsertArea(Area area) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put( Contract.alfsalamaEntry.COLUMN_AREA_ID, area.getID());
        values.put( Contract.alfsalamaEntry.COLUMN_AREA_NAME, area.getAreaName());
        values.put( Contract.alfsalamaEntry.COLUMN_AREA_CITY_ID, area.getCity_ID());
        // insert row
        long tag_id = db.insert( Contract.alfsalamaEntry.AREATABLE, null, values);

        return tag_id;
    }

    public Area getArea(long Gov_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + Contract.alfsalamaEntry.AREATABLE + " WHERE "
                + Contract.alfsalamaEntry.COLUMN_AREA_ID + " = " + Gov_id;

       // Log.e(Contract.alfsalamaEntry.GOVERNRATETABLE, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Area area = new Area();
       // city.setId((c.getInt(c.getColumnIndex(Contract.alfsalamaEntry.COLUMN_AREA_ID))));
        area.setAreaName(c.getString(c.getColumnIndex(Contract.alfsalamaEntry.COLUMN_AREA_NAME)));
        area.setCity_ID((c.getInt(c.getColumnIndex(Contract.alfsalamaEntry.COLUMN_AREA_CITY_ID))));
        return area;
    }








    public long InsertCAT(Catoegry Cat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put( Contract.alfsalamaEntry.COLUMN_CAT_IDID, Cat.getCatID());
        values.put( Contract.alfsalamaEntry.COLUMN_CAT_NAME, Cat.getCatName());
       values.put( Contract.alfsalamaEntry.COLUMN_CAT_ID, Cat.getCatID());

        long tag_id = db.insert( Contract.alfsalamaEntry.CATTable, null, values);

        return tag_id;
    }


    public ArrayList<Catoegry>getAllCats() {
        ArrayList<Catoegry> todos = new ArrayList<Catoegry>();
        String selectQuery = "SELECT  * FROM " + Contract.alfsalamaEntry.CATTable;

        //Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Catoegry td = new Catoegry();
                td.setCatID((c.getString(c.getColumnIndex( c.getColumnName(3)))));
                td.setCatName((c.getString(c.getColumnIndex( c.getColumnName(2)))));

                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }



    public ArrayList<Area>getAllAreaies() {
        ArrayList<Area> todos = new ArrayList<Area>();
        String selectQuery = "SELECT  * FROM " + Contract.alfsalamaEntry.AREATABLE;

        //Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Area td = new Area();
                // td.setID(c.getInt((c.getColumnIndex("_id"))));
                 td.setID(c.getInt(1));
                td.setAreaName((c.getString(c.getColumnIndex( Contract.alfsalamaEntry.COLUMN_AREA_NAME))));
                td.setCity_ID(c.getInt((c.getColumnIndex( Contract.alfsalamaEntry.COLUMN_AREA_CITY_ID))));

                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public ArrayList<Area>getCityAreaies(int cityId){
        ArrayList<Area> todos = new ArrayList<Area>();
        String selectQuery = "SELECT  * FROM " + Contract.alfsalamaEntry.AREATABLE + " WHERE AREA_CITY_ID = "+cityId;

        //Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Area td = new Area();
                td.setID(c.getInt(1));
                td.setAreaName((c.getString(c.getColumnIndex( Contract.alfsalamaEntry.COLUMN_AREA_NAME))));
                td.setCity_ID(c.getInt((c.getColumnIndex( Contract.alfsalamaEntry.COLUMN_AREA_CITY_ID))));

                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }




    public long InsertCity(City city) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put( Contract.alfsalamaEntry.COLUMN_CITY_ID, city.getId());
        values.put( Contract.alfsalamaEntry.COLUMN_CITY_NAME, city.getCityName());
        values.put( Contract.alfsalamaEntry.COLUMN_CITY_GOV_ID, city.getGov_id());
        // insert row
        long tag_id = db.insert( Contract.alfsalamaEntry.CITYETABLE, null, values);

        return tag_id;
    }




    public City getCity(long Gov_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + Contract.alfsalamaEntry.CITYETABLE + " WHERE "
                + Contract.alfsalamaEntry.COLUMN_CITY_ID + " = " + Gov_id;

        Log.e(Contract.alfsalamaEntry.GOVERNRATETABLE, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        City city = new City();
        city.setId((c.getInt(c.getColumnIndex(Contract.alfsalamaEntry.COLUMN_CITY_ID))));
        city.setCityName(c.getString(c.getColumnIndex(Contract.alfsalamaEntry.COLUMN_CITY_NAME)));
        city.setGov_id((c.getInt(c.getColumnIndex(Contract.alfsalamaEntry.COLUMN_CITY_GOV_ID))));
        return city;
    }




    /**
     * getting all todos
     * */
    public ArrayList<City>getAllCities() {
        ArrayList<City> todos = new ArrayList<City>();
        String selectQuery = "SELECT  * FROM " + Contract.alfsalamaEntry.CITYETABLE;

        //Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                City td = new City();
               // td.setID(c.getInt((c.getColumnIndex("_id"))));
                td.setId(c.getInt(1));
                td.setCityName((c.getString(c.getColumnIndex( Contract.alfsalamaEntry.COLUMN_CITY_NAME))));
                td.setGov_id(c.getInt((c.getColumnIndex( Contract.alfsalamaEntry.COLUMN_CITY_GOV_ID))));

                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }


    public long InsertGovernate(Governrate Gov) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put( Contract.alfsalamaEntry.COLUMN_GOVERNRATE_ID, Gov.getID());
        values.put( Contract.alfsalamaEntry.COLUMN_GOVERNRATE_NAME, Gov.getName());
        // insert row
        long tag_id = db.insert( Contract.alfsalamaEntry.GOVERNRATETABLE, null, values);

        return tag_id;
    }






    public Governrate getGovernrate(long Gov_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + Contract.alfsalamaEntry.GOVERNRATETABLE + " WHERE "
                + Contract.alfsalamaEntry.COLUMN_GOVERNRATE_ID + " = " + Gov_id;

        Log.e(Contract.alfsalamaEntry.GOVERNRATETABLE, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Governrate governrate = new Governrate();
        governrate.setName(c.getString(c.getColumnIndex(Contract.alfsalamaEntry.COLUMN_GOVERNRATE_NAME)));
        governrate.setID((c.getInt(c.getColumnIndex(Contract.alfsalamaEntry.COLUMN_GOVERNRATE_ID))));


        return governrate;
    }

    /**
     * getting all todos
     * */
    public ArrayList<Governrate>getAllGovernrate() {
        ArrayList<Governrate> todos = new ArrayList<Governrate>();
        String selectQuery = "SELECT  * FROM " + Contract.alfsalamaEntry.GOVERNRATETABLE;

        //Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Governrate td = new Governrate();
               // td.setID(c.getInt((c.getColumnIndex("_id"))));
                td.setID(c.getInt((c.getColumnIndex( Contract.alfsalamaEntry.COLUMN_GOVERNRATE_ID))));
                td.setName((c.getString(c.getColumnIndex( Contract.alfsalamaEntry.COLUMN_GOVERNRATE_NAME))));


                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public void deleteGovernorate() {
        SQLiteDatabase db = this.getReadableDatabase();
        String deleteGovernorate = "DELETE FROM "+Contract.alfsalamaEntry.GOVERNRATETABLE;
        db.execSQL(deleteGovernorate);
        db.close();
    }

    public void deleteCity() {
        SQLiteDatabase db = this.getReadableDatabase();
        String deleteGovernorate = "DELETE FROM "+Contract.alfsalamaEntry.CITYETABLE;
        db.execSQL(deleteGovernorate);
        db.close();
    }

    public void deleteArea() {
        SQLiteDatabase db = this.getReadableDatabase();
        String deleteGovernorate = "DELETE FROM "+Contract.alfsalamaEntry.AREATABLE;
        db.execSQL(deleteGovernorate);
        Log.w("areaDeleted","areaDeleted");
        db.close();
    }

    public void deleteCategory() {
        SQLiteDatabase db = this.getReadableDatabase();
        String deleteGovernorate = "DELETE FROM "+Contract.alfsalamaEntry.CATTable;
        db.execSQL(deleteGovernorate);
        db.close();
    }









}