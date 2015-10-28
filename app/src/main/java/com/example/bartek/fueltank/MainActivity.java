package com.example.bartek.fueltank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bartek.fueltank.db.DatabaseHandler;
import com.example.bartek.fueltank.db.FuelType;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.bartek.fueltank.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        // Inserting

/*
        Log.d("Reading", "Reading...");
        List<FuelType> fuelTypes = db.getAllFuelTypes();

        String allFuelTypes = "";
        for (FuelType ft: fuelTypes) {
            String log = "ID: " + ft.get_id() + " | NAME: " + ft.get_name() + " >> ";
            allFuelTypes += log;
        }

        EditText editText = (EditText)findViewById(R.id.edit_message);
        editText.setText(allFuelTypes);
        */
    }

    public void resetDb(View view) {
        try {
            DatabaseHandler db = new DatabaseHandler(this);
            db.resetDatabase();
            Toast toastSuc = Toast.makeText(this, "Database reseted", Toast.LENGTH_SHORT);
            toastSuc.show();
        }
        catch (Exception e) {
            Log.e("Reset db", e.getMessage(), e);
            Toast toastErr = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            toastErr.show();
        }
    }

    public void chooseCar(View view) {
        Intent intent = new Intent(this, ChooseCarActivity.class);
        startActivity(intent);
    }

    //public void sendMessage(View view) {
    //    Intent intent = new Intent(this, DisplayMessageActivity.class);
        //EditText editText = (EditText)findViewById(R.id.);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
    //    startActivity(intent);
    //}
}
