package com.example.bartek.fueltank;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.bartek.fueltank.adapters.FuelListAdapter;
import com.example.bartek.fueltank.db.DatabaseHandler;
import com.example.bartek.fueltank.db.Fuel;

import java.util.ArrayList;
import java.util.List;

public class AllFuelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_fuels);

        ListView listViewFuels = (ListView)findViewById(R.id.listViewFuels);

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Fuel> fuels = db.getAllFuels();

        FuelListAdapter fuelListAdapter = new FuelListAdapter(fuels, this);

        listViewFuels.setAdapter(fuelListAdapter);
    }

}
