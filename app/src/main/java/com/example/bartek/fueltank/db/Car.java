package com.example.bartek.fueltank.db;

/**
 * Created by bartek on 26.10.15.
 */
public class Car {
    int _id;
    String _name;
    int _year;
    int _defaultFuel;

    public Car() {

    }

    public Car(int _id, String _name, int _year, int _defaultFuel) {
        this._id = _id;
        this._name = _name;
        this._year = _year;
        this._defaultFuel = _defaultFuel;
    }

    public Car(String _name, int _year, int _defaultFuel) {
        this._name = _name;
        this._year = _year;
        this._defaultFuel = _defaultFuel;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_defaultFuel() {
        return _defaultFuel;
    }

    public void set_defaultFuel(int _defaultFuel) {
        this._defaultFuel = _defaultFuel;
    }

    public int get_year() {
        return _year;
    }

    public void set_year(int _year) {
        this._year = _year;
    }

    @Override
    public String toString() {
        return _name;
    }
}
