package com.example.bartek.fueltank;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bartek.fueltank.db.DatabaseHandler;
import com.example.bartek.fueltank.db.Fuel;
import com.example.bartek.fueltank.db.KeyValuePair;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddFuelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fuel);

        Spinner spinnerCars = (Spinner)findViewById(R.id.spinner_choose_car);
        Spinner spinnerFuelType = (Spinner)findViewById(R.id.spinner_choose_fueltype2);

        DatabaseHandler db = new DatabaseHandler(this);
        List<KeyValuePair> cars = db.getAllCarsForSpinner();
        List<KeyValuePair> fuelTypes = db.getAllFuelTypesForSpinner();

        ArrayAdapter<KeyValuePair> adapter = new ArrayAdapter<KeyValuePair>(this,
                R.layout.support_simple_spinner_dropdown_item, fuelTypes);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerFuelType.setAdapter(adapter);

        ArrayAdapter<KeyValuePair> adapterCars = new ArrayAdapter<KeyValuePair>(this,
                R.layout.support_simple_spinner_dropdown_item, cars);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCars.setAdapter(adapterCars);

        db.close();

    }

    public void addFuel(View view) {
        Spinner car = (Spinner)findViewById(R.id.spinner_choose_car);
        Spinner fuel = (Spinner)findViewById(R.id.spinner_choose_fueltype2);
        DatePicker date = (DatePicker)findViewById(R.id.datePicker_fuel_date);
        EditText amount = (EditText)findViewById(R.id.txt_add_fuel_amount);
        EditText overallPrice = (EditText)findViewById(R.id.txt_add_fuel_price_overall);
        EditText price = (EditText)findViewById(R.id.txt_add_fuel_price);
        EditText mileage = (EditText)findViewById(R.id.txt_add_fuel_mileage);
        CheckBox fullFuel = (CheckBox)findViewById(R.id.chb_fuel_full);

        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonth(), date.getDayOfMonth());
        Date selectedDate = calendar.getTime();

        Fuel f = new Fuel(selectedDate, Double.parseDouble(amount.getText().toString()),
                Double.parseDouble(price.getText().toString()),
                Double.parseDouble(overallPrice.getText().toString()),
                Integer.parseInt(mileage.getText().toString()), fullFuel.isChecked(),
                ((KeyValuePair)car.getSelectedItem()).getValue(),
                ((KeyValuePair)fuel.getSelectedItem()).getValue());

        DatabaseHandler db = new DatabaseHandler(this);
        db.addFuel(f);

        Toast toast = Toast.makeText(getApplicationContext(), "Dodano tankowanie", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void cancel(View view) {
        onBackPressed();
    }

}
