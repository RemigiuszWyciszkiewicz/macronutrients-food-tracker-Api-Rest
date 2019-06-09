package com.Remigiusz.MacronutrientsApiREST.Utility;

public class MacrosCalculator {

    static double calculate(int amount,double macro)
    {
        return round((double)amount/100 * macro,1);
    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
