package com.example.bartek.fueltank.db;

import java.util.Date;

/**
 * Created by bartek on 30.10.15.
 */
public class Fuel {
    int _id;
    Date _date;
    double _amount;
    double _price;
    double _priceOverall;
    int _mileage;
    boolean _fullFuel;

    int _idCar;
    int _id_FuelType;

    public Fuel(int _id, Date _date, double _amount, double _price, double _priceOverall, int _mileage, boolean _fullFuel, int _idCar, int _id_FuelType) {
        this._id = _id;
        this._date = _date;
        this._amount = _amount;
        this._price = _price;
        this._priceOverall = _priceOverall;
        this._mileage = _mileage;
        this._fullFuel = _fullFuel;
        this._idCar = _idCar;
        this._id_FuelType = _id_FuelType;
    }

    public Fuel(Date _date, double _amount, double _price, double _priceOverall, int _mileage, boolean _fullFuel, int _idCar, int _id_FuelType) {
        this._date = _date;
        this._amount = _amount;
        this._price = _price;
        this._priceOverall = _priceOverall;
        this._mileage = _mileage;
        this._fullFuel = _fullFuel;
        this._idCar = _idCar;
        this._id_FuelType = _id_FuelType;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Date get_date() {
        return _date;
    }

    public void set_date(Date _date) {
        this._date = _date;
    }

    public double get_amount() {
        return _amount;
    }

    public void set_amount(double _amount) {
        this._amount = _amount;
    }

    public double get_price() {
        return _price;
    }

    public void set_price(double _price) {
        this._price = _price;
    }

    public double get_priceOverall() {
        return _priceOverall;
    }

    public void set_priceOverall(double _priceOverall) {
        this._priceOverall = _priceOverall;
    }

    public int get_mileage() {
        return _mileage;
    }

    public void set_mileage(int _mileage) {
        this._mileage = _mileage;
    }

    public boolean is_fullFuel() {
        return _fullFuel;
    }

    public void set_fullFuel(boolean _fullFuel) {
        this._fullFuel = _fullFuel;
    }

    public int get_idCar() {
        return _idCar;
    }

    public void set_idCar(int _idCar) {
        this._idCar = _idCar;
    }

    public int get_id_FuelType() {
        return _id_FuelType;
    }

    public void set_id_FuelType(int _id_FuelType) {
        this._id_FuelType = _id_FuelType;
    }
}
