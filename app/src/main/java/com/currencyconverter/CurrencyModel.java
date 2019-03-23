package com.currencyconverter;

public final class CurrencyModel {
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
        dollarValue = euroValue * conversionRateDollarsPerEuro;
        dollarValue = getRoundedValue(dollarValue);
    }

    private void updateEuroValue() {
        euroValue = dollarValue / conversionRateDollarsPerEuro;
        euroValue = getRoundedValue(euroValue);
    }



    public boolean setConversionRateDollarsPerEuro(final double conversionRateDollarsPerEuro) {
        final boolean isNewValue = !areValuesEqual(this.conversionRateDollarsPerEuro, conversionRateDollarsPerEuro);
        if(isNewValue) {
            this.conversionRateDollarsPerEuro = conversionRateDollarsPerEuro;
            updateDollarValue();
        }
        return isNewValue;
    }

    public boolean setEuroValue(final double euroValue) {
        final boolean isNewValue = !areValuesEqual(this.euroValue, euroValue);
        if(isNewValue) {
            this.euroValue = euroValue;
            updateDollarValue();
        }
        return isNewValue;
    }

    public boolean setDollarValue(final double dollarValue) {
        final boolean isNewValue = !areValuesEqual(this.dollarValue, dollarValue);
        if(isNewValue) {
            this.dollarValue = dollarValue;
            updateEuroValue();
        }
        return isNewValue;
    }



    public boolean setConversionRateDollarsPerEuroFromString(final String value) {
        if(isNullOrEmpty(value)) return true;
        return this.setConversionRateDollarsPerEuro(Double.parseDouble(value));
    }

    public boolean setEuroValueFromString(String value) {
        if(isNullOrEmpty(value)) return true;
        return this.setEuroValue(Double.parseDouble(value));
    }

    public boolean setDollarValueFromString(String value) {
        if(isNullOrEmpty(value)) return true;
        return this.setDollarValue(Double.parseDouble(value));
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
