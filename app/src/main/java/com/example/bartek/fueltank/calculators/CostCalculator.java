package com.example.bartek.fueltank.calculators;

/**
 * Created by bartek on 31.10.15.
 */
public class CostCalculator {

    public static double getOverallPrice(double amount, double price) {
        return amount * price;
    }

    public static double getPrice(double amount, double overallPrice) {
        return overallPrice / amount;
    }

}
