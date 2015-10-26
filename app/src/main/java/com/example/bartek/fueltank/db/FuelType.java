package com.example.bartek.fueltank.db;

/**
 * Created by bartek on 25.10.15.
 */
public class FuelType {
    int _id;
    String _name;
    String _icon;

    public FuelType() {

    }

    public FuelType(int id, String name, String icon) {
        this._id = id;
        this._name = name;
        this._icon = icon;
    }

    public FuelType(String name, String icon) {
        this._name = name;
        this._icon = icon;
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

    public String get_icon() {
        return _icon;
    }

    public void set_icon(String _icon) {
        this._icon = _icon;
    }
}
