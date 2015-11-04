package com.example.bartek.fueltank.calculators;

/**
 * Created by bartek on 31.10.15.
 */
public class FuelCalculator {

    public static double getAvgFuelPer100(long distance, double amount) {
        return 100 * amount / (double)distance;
    }

    public static double getAvgFuelPer100FromTank(long currentMileage, long lastMileage,
                                                      double amount) {
        return getAvgFuelPer100(currentMileage - lastMileage, amount);
    }



}
