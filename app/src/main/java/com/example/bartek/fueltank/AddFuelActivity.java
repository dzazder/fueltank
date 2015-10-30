package com.example.bartek.fueltank;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.bartek.fueltank.db.DatabaseHandler;
import com.example.bartek.fueltank.db.Fuel;
import com.example.bartek.fueltank.db.KeyValuePair;
import com.example.bartek.fueltank.helpers.DatePickerFragment;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddFuelActivity extends AppCompatActivity {

    TextView dateView;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fuel);

        // load data for spinners
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

        // datePicker configuration
        dateView= (TextView)findViewById(R.id.lbl_selected_date);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

    }

    public void addFuel(View view) {
        Spinner car = (Spinner)findViewById(R.id.spinner_choose_car);
        Spinner fuel = (Spinner)findViewById(R.id.spinner_choose_fueltype2);
        TextView date = (TextView)findViewById(R.id.lbl_selected_date);
        EditText amount = (EditText)findViewById(R.id.txt_add_fuel_amount);
        EditText overallPrice = (EditText)findViewById(R.id.txt_add_fuel_price_overall);
        EditText price = (EditText)findViewById(R.id.txt_add_fuel_price);
        EditText mileage = (EditText)findViewById(R.id.txt_add_fuel_mileage);
        CheckBox fullFuel = (CheckBox)findViewById(R.id.chb_fuel_full);

        try {
            Fuel f = new Fuel(date.getText().toString(), Double.parseDouble(amount.getText().toString()),
                    Double.parseDouble(price.getText().toString()),
                    Double.parseDouble(overallPrice.getText().toString()),
                    Integer.parseInt(mileage.getText().toString()), fullFuel.isChecked(),
                    ((KeyValuePair) car.getSelectedItem()).getValue(),
                    ((KeyValuePair) fuel.getSelectedItem()).getValue());

            DatabaseHandler db = new DatabaseHandler(this);
            db.addFuel(f);

            Toast toast = Toast.makeText(getApplicationContext(), "Dodano tankowanie", Toast.LENGTH_SHORT);
            toast.show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Popraw formularz", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("-")
            .append(month).append("-").append(year));
    }

    @SuppressWarnings("deprecation")
    public void showDatePicker(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    public void cancel(View view) {
        onBackPressed();
    }

}
