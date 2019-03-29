package com.currencyconverter;

import android.util.Log;

public final class CurrencyModel {
    private final static String LOG_TAG = CurrencyModel.class.getName();

    // 1 EUR = 1.13074 USD
    private double conversionRateDollarsPerEuro = 1.13074;
    private double euroValue = 1;
    private double dollarValue; // = euroValue * conversionRateDollarsPerEuro;

    public CurrencyModel() {
        updateDollarValue();
    }

    public double getConversionRateDollarsPerEuro() {
        return conversionRateDollarsPerEuro;
    }
    public double getEuroValue() {
        return euroValue;
    }
    public double getDollarValue() {
        return dollarValue;
    }

    public String getConversionResultDisplayText() {
        return  "" + this.getEuroValue() + " EURO = " + this.getDollarValue() + " USD (exchange rate: " + this.getConversionRateDollarsPerEuro() + " USD per EURO)";
    }

    private void updateDollarValue() {
        dollarValue = getRoundedValue(euroValue * conversionRateDollarsPerEuro);
    }

    private void updateEuroValue() {
        euroValue = getRoundedValue(dollarValue / conversionRateDollarsPerEuro);
    }


    public boolean setConversionRateDollarsPerEuro(final double conversionRateDollarsPerEuro) {
        final boolean isNewValue = !areValuesEqual(this.conversionRateDollarsPerEuro, conversionRateDollarsPerEuro);
        if(isNewValue) {
            this.conversionRateDollarsPerEuro = conversionRateDollarsPerEuro;
            updateDollarValue();
        }
        return isNewValue;
    }

    //public boolean setEuroValue(final double euroValue) {
    // precondition: isEuroValueChanged should be true
    public void setEuroValue(final double euroValue) {
//        final boolean isNewValue = !areValuesEqual(this.euroValue, euroValue);
//        if(isNewValue) {
            this.euroValue = euroValue;
            updateDollarValue();
//        }
//        return isNewValue;
    }

    public boolean isConversionRateDollarsPerEuroChanged(String conversionRateDollarsPerEuroString) {
        if(isNullOrEmpty(conversionRateDollarsPerEuroString)) return false;
        double conversionRateDollarsPerEuro = Double.parseDouble(conversionRateDollarsPerEuroString);
        return !areValuesEqual(this.conversionRateDollarsPerEuro, conversionRateDollarsPerEuro);
    }
    public boolean isEuroValueChanged(String euroValueString) {
        if(isNullOrEmpty(euroValueString)) return false;
        double euroValue = Double.parseDouble(euroValueString);
        return !areValuesEqual(this.euroValue, euroValue);
    }
    public boolean isDollarValueChanged(String dollarValueString) {
        if(isNullOrEmpty(dollarValueString)) return false;
        double dollarValue = Double.parseDouble(dollarValueString);
        return !areValuesEqual(this.dollarValue, dollarValue);
    }

    // precondition: isDollarValueChanged should be true
//    public boolean setDollarValue(final double dollarValue) {
    public void setDollarValue(final double dollarValue) {
//        final boolean isNewValue = !areValuesEqual(this.dollarValue, dollarValue);
//        if(isNewValue) {
            this.dollarValue = dollarValue;
            updateEuroValue();
//        }
//        return isNewValue;
    }



    public boolean setConversionRateDollarsPerEuroFromString(final String value) {
        if(isNullOrEmpty(value)) return true;
        return this.setConversionRateDollarsPerEuro(Double.parseDouble(value));
    }

    public void setEuroString2(String value) {
        if(isNullOrEmpty(value)) return;
        this.setEuroValue(Double.parseDouble(value));
    }

    // precondition: isEuroValueChanged should be true
    public void setEuroString(String value) {
        Log.d(LOG_TAG, "setEuroString is invoked with value " + value);
//        if(isNullOrEmpty(value)) return true;
        this.setEuroValue(Double.parseDouble(value));
    }

    public String getEuroString() {
        return "" + this.euroValue;
    }

    // precondition: isDollarValueChanged should be true
    public void setDollarValueFromString(String value) {
//        if(isNullOrEmpty(value)) return true;
        this.setDollarValue(Double.parseDouble(value));
    }



    private boolean isNullOrEmpty(String value) {
        if(value == null) return true;
        if(value.trim().equals("")) return true;
        return false;
    }

    private boolean areValuesEqual(double value1, double value2) {
        //return Double.compare(value1, value2) == 0;
        final double diff = Math.abs(value1 - value2);
        return diff < 0.0000001;
    }

    private double getRoundedValue(final double value) {
        return Math.round(value * 100000d) / 100000d;
    }
}
