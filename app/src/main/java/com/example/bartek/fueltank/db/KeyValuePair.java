package com.example.bartek.fueltank.db;

/**
 * Created by bartek on 27.10.15.
 */
public class KeyValuePair {
    private String label;
    private int value;

    public KeyValuePair(int value, String label) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return label;
    }
}
