package com.example.bartek.fueltank.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
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
    private static final String KEY_FK_FUEL_TYPE = "id_fuel_type";
    private static final String KEY_ICON = "icon";
    private static final String KEY_DATE_FUEL = "fuel_date";
    private static final String KEY_FK_CAR = "id_car";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_PRICE_OVERALL = "price_overall";
    private static final String KEY_PRICE = "price";
    private static final String KEY_IS_DEFAULT = "is_default";
    private static final String KEY_IS_FULL = "is_full";
    private static final String KEY_MILEAGE = "mileage";

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
                + KEY_YEAR + " INT," + KEY_FK_FUEL_TYPE + " INT,"
                + KEY_IS_DEFAULT + " INTEGER " + ")";

        String CREATE_FUEL_TABLE = "CREATE TABLE " + TABLE_FUEL + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FK_CAR + " INT,"
                + KEY_FK_FUEL_TYPE + " INT," + KEY_DATE_FUEL + " TEXT,"
                + KEY_AMOUNT + " REAL," + KEY_PRICE + " REAL,"
                + KEY_PRICE_OVERALL + " REAL," + KEY_MILEAGE + " INT, " + KEY_IS_FULL + ")";

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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FUEL_TYPE);

        onCreate(db);
    }

    public void resetDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, db.getVersion(), db.getVersion());
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

    public List<KeyValuePair> getAllFuelTypesForSpinner() {
        List<KeyValuePair> fuelTypes = new ArrayList<KeyValuePair>();

        String selectQuery = "SELECT " + KEY_ID + "," + KEY_NAME + " FROM " + TABLE_FUEL_TYPE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                KeyValuePair pair = new KeyValuePair(cursor.getInt(0), cursor.getString(1));

                fuelTypes.add(pair);
            } while (cursor.moveToNext());
        }

        return fuelTypes;
    }

    public List<KeyValuePair> getAllCarsForSpinner() {
        List<KeyValuePair> cars = new ArrayList<KeyValuePair>();

        String selectQuery = "SELECT " + KEY_ID + "," + KEY_NAME + " FROM " + TABLE_CAR;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                KeyValuePair pair = new KeyValuePair(cursor.getInt(0), cursor.getString(1));

                cars.add(pair);
            } while (cursor.moveToNext());
        }

        return cars;
    }

    public long addCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, car.get_name());
        values.put(KEY_YEAR, car.get_year());
        values.put(KEY_FK_FUEL_TYPE, car.get_defaultFuel());

        long result = db.insert(TABLE_CAR, null, values);
        db.close();

        return result;
    }

    public long addFuel(Fuel fuel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FK_CAR, fuel.get_idCar());
        values.put(KEY_FK_FUEL_TYPE, fuel.get_id_FuelType());
        values.put(KEY_DATE_FUEL, fuel.get_date());
        values.put(KEY_AMOUNT, fuel.get_amount());
        values.put(KEY_PRICE, fuel.get_price());
        values.put(KEY_PRICE_OVERALL, fuel.get_priceOverall());
        values.put(KEY_MILEAGE, fuel.get_mileage());
        values.put(KEY_IS_FULL, fuel.is_fullFuel());

        long result = db.insert(TABLE_FUEL, null, values);
        db.close();

        return result;
    }

    public ArrayList<Fuel> getAllFuels() {
        ArrayList<Fuel> fuels = new ArrayList<Fuel>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT " + KEY_ID + ","
                + KEY_FK_CAR + ","
                + KEY_FK_FUEL_TYPE + ","
                + KEY_DATE_FUEL + ","
                + KEY_AMOUNT + ","
                + KEY_PRICE + ","
                + KEY_PRICE_OVERALL + ","
                + KEY_MILEAGE + ","
                + KEY_IS_FULL +
                " FROM " + TABLE_FUEL;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Fuel fuel = new Fuel();
                fuel.set_id(cursor.getInt(0));
                fuel.set_idCar(cursor.getInt(1));
                fuel.set_id_FuelType(cursor.getInt(2));
                fuel.set_date(cursor.getString(3));
                fuel.set_amount(cursor.getDouble(4));
                fuel.set_price(cursor.getDouble(5));
                fuel.set_priceOverall(cursor.getDouble(6));
                fuel.set_mileage(cursor.getInt(7));
                fuel.set_fullFuel(Boolean.parseBoolean(cursor.getString(8)));

                fuels.add(fuel);
            } while (cursor.moveToNext());
        }


        db.close();

        return fuels;
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<Car>();

        String selectQuery = "SELECT * FROM " + TABLE_CAR;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Car car = new Car();
                car.set_id(Integer.parseInt(cursor.getString(0)));
                car.set_name(cursor.getString(1));
                car.set_year(cursor.getInt(2));
                car.set_defaultFuel(cursor.getInt(3));

                cars.add(car);
            } while (cursor.moveToNext());
        }

        return cars;
    }

    public void setDefaultCar(long id) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dataToUpdate = new ContentValues();
        dataToUpdate.put(KEY_IS_DEFAULT, 0);

        db.update(TABLE_CAR, dataToUpdate, null, null);

        dataToUpdate.clear();
        dataToUpdate.put(KEY_IS_DEFAULT, 1);

        String where = KEY_ID + "=?";
        String[] whereArgs = new String[] {
                String.valueOf(id)
        };

        db.update(TABLE_CAR, dataToUpdate, where, whereArgs);
        db.close();
    }
}
