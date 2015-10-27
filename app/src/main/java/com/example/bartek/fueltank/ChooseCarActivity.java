package com.example.bartek.fueltank;

import android.content.Intent;
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
import com.example.bartek.fueltank.db.KeyValuePair;

import java.util.ArrayList;
import java.util.List;

public class ChooseCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_car);

        Spinner spinner = (Spinner)findViewById(R.id.spinner_choose_car);
        DatabaseHandler db = new DatabaseHandler(this);
        List<KeyValuePair> cars = db.getAllCarsForSpinner();
        ArrayAdapter<KeyValuePair> adapter = new ArrayAdapter<KeyValuePair>(this,
                R.layout.support_simple_spinner_dropdown_item, cars);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        db.close();
    }

    public List<Car> loadSpinnerCarData() {
        DatabaseHandler db = new DatabaseHandler(this);

        List<Car> cars = db.getAllCars();
        db.close();

        return cars;
    }

    public void addCar(View view) {
        Intent intent = new Intent(this, AddCarActivity.class);
        startActivity(intent);
    }
}
