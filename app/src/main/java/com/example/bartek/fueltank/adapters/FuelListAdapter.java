package com.example.bartek.fueltank.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.example.bartek.fueltank.R;
import com.example.bartek.fueltank.db.Fuel;

import java.util.ArrayList;

/**
 * Created by bartek on 04.11.15.
 */
public class FuelListAdapter extends BaseAdapter {
    ArrayList<Fuel> data;
    private Context ctx;

    public FuelListAdapter(ArrayList<Fuel> data, Context ctx) {
        this.data = data;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderPattern view_holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_fuel, parent, false);

            view_holder = new ViewHolderPattern();

            view_holder.lblDate = (EditText)convertView.findViewById(R.id.lbl_date);
            view_holder.lblAmount = (EditText)convertView.findViewById(R.id.lbl_amount);
            view_holder.lblPriceOverall = (EditText)convertView.findViewById(R.id.lbl_price_overall);

            convertView.setTag(view_holder);
        } else {
            view_holder = (ViewHolderPattern)convertView.getTag();
        }

        view_holder.lblDate.setText(data.get(position).get_date());
        view_holder.lblAmount.setText(Double.toString(data.get(position).get_amount()));
        view_holder.lblPriceOverall.setText(Double.toString(data.get(position).get_priceOverall()));

        return convertView;
    }

    /**
     * Helper class to hold controls
     */
    private class ViewHolderPattern {
        EditText lblDate;
        EditText lblAmount;
        EditText lblPriceOverall;
    }
}
