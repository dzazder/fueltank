package com.example.bartek.fueltank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bartek.fueltank.db.Car;
import com.example.bartek.fueltank.db.DatabaseHandler;
import com.example.bartek.fueltank.db.KeyValuePair;

import java.util.List;

public class AddCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        Spinner spinnerFuelType = (Spinner)findViewById(R.id.spinner_choose_fueltype);
        DatabaseHandler db = new DatabaseHandler(this);
        List<KeyValuePair> fuelTypes = db.getAllFuelTypesForSpinner();
        ArrayAdapter<KeyValuePair> adapter = new ArrayAdapter<KeyValuePair>(this,
                R.layout.support_simple_spinner_dropdown_item, fuelTypes);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerFuelType.setAdapter(adapter);
        db.close();
    }

    public void addCar(View view) {
        EditText txtName = (EditText)findViewById(R.id.txt_car_name);
        EditText txtYear = (EditText)findViewById(R.id.txt_car_year);
        Spinner spnFuelType = (Spinner)findViewById(R.id.spinner_choose_fueltype);

        String carName = txtName.getText().toString();
        int carYear = Integer.parseInt(txtYear.getText().toString());
        int fuelTypeId = ((KeyValuePair)spnFuelType.getSelectedItem()).getValue();

        Car c = new Car();
        c.set_name(carName);
        c.set_year(carYear);
        c.set_defaultFuel(fuelTypeId);

        DatabaseHandler db = new DatabaseHandler(this);
        db.addCar(c);
        db.close();

        Toast toast = Toast.makeText(getApplicationContext(), "Dodano samochód", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void cancel(View view) {
        onBackPressed();
    }
}
