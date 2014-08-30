package ru.korvin.dominion.util.text;

import android.graphics.Color;

public class TextUtil {

    public static String getVaLueWithChange(int newValue, int oldValue) {
        if (newValue == oldValue) {
            return Integer.toString(newValue);
        } else {
            return Integer.toString(newValue) + " (" + Integer.toString(oldValue) + ")";
        }
    }

    public static int getColorForChange(int newValue, int oldValue) {
        if (newValue == oldValue)
            return Color.WHITE;
        if (newValue < oldValue)
            return Color.RED;
        if (oldValue < newValue)
            return Color.GREEN;
        return Color.MAGENTA;
    }
}
