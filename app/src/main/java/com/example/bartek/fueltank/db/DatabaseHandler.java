package com.example.bartek.fueltank.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bartek on 25.10.15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "fuelManager";

    // Tables
    private static final String TABLE_CAR = "car";
    private static final String TABLE_FUEL_TYPE = "fuel_type";
    private static final String TABLE_FUEL = "fuel";

    // Columns
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_YEAR = "year";
    private static final String KEY_FK_DEFAULT_FUEL_TYPE = "id_default_fuel_type";
    private static final String KEY_FK_FUEL_TYPE = "id_fuel_type";
    private static final String KEY_ICON = "icon";
    private static final String KEY_DATE_FUEL = "fuel_date";
    private static final String KEY_FK_CAR = "id_car";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_PRICE_OVERALL = "price_overall";
    private static final String KEY_PRICE = "price";

    private ArrayList<FuelType> DICT_FUEL_TYPES = new ArrayList<>();

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_FUEL_TYPE_TABLE = "CREATE TABLE " + TABLE_FUEL_TYPE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ICON + " TEXT" + ")";

        String CREATE_CAR_TABLE = "CREATE TABLE " + TABLE_CAR + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_YEAR + " INT," + KEY_FK_FUEL_TYPE + " INT" + ")";

        String CREATE_FUEL_TABLE = "CREATE TABLE " + TABLE_FUEL + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FK_CAR + " INT,"
                + KEY_FK_FUEL_TYPE + " INT," + KEY_DATE_FUEL + " TEXT,"
                + KEY_AMOUNT + " REAL," + KEY_PRICE + " REAL,"
                + KEY_PRICE_OVERALL + "REAL" + ")";

        db.execSQL(CREATE_FUEL_TYPE_TABLE);
        db.execSQL(CREATE_CAR_TABLE);
        db.execSQL(CREATE_FUEL_TABLE);


        DICT_FUEL_TYPES.add(new FuelType(1, "PB 95", ""));
        DICT_FUEL_TYPES.add(new FuelType(2, "PB 98", ""));
        DICT_FUEL_TYPES.add(new FuelType(3, "LPG", ""));
        DICT_FUEL_TYPES.add(new FuelType(4, "CNG", ""));
        DICT_FUEL_TYPES.add(new FuelType(5, "DIESEL", ""));

        for (FuelType ft: DICT_FUEL_TYPES) {
            addFuelType(ft);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FUEL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
        db.execSQL("DROP TABLE ID EXISTS " + TABLE_FUEL_TYPE);

        onCreate(db);
    }

    public void addFuelType(FuelType fuelType) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, fuelType.get_name());
        values.put(KEY_ICON, fuelType.get_icon());

        db.insert(TABLE_FUEL_TYPE, null, values);
        db.close();
    }

    public FuelType getFuelType(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FUEL_TYPE, new String[]{KEY_ID, KEY_NAME, KEY_ICON},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        FuelType fuelType = new FuelType(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return fuelType;
    }

    public List<FuelType> getAllFuelTypes() {
        List<FuelType> fuelTypes = new ArrayList<FuelType>();

        String selectQuery = "SELECT * FROM " + TABLE_FUEL_TYPE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                FuelType fuelType = new FuelType();
                fuelType.set_id(Integer.parseInt(cursor.getString(0)));
                fuelType.set_name(cursor.getString(1));
                fuelType.set_icon(cursor.getString(2));

                fuelTypes.add(fuelType);
            } while (cursor.moveToNext());
        }

        return fuelTypes;
    }
}
