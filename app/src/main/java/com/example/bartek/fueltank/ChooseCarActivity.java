package com.example.bartek.fueltank;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.bartek.fueltank.db.Car;
import com.example.bartek.fueltank.db.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class ChooseCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_car);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner = (Spinner)findViewById(R.id.spinner_choose_car);
        List<Car> cars = loadSpinnerCarData();
        //ArrayAdapter<Car> carAdapter = new ArrayAdapter<Car>(this, null, cars);

    }

    public List<Car> loadSpinnerCarData() {
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        List<Car> cars = db.getAllCars();
        db.close();

        return cars;
    }

}
