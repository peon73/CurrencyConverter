package com.currencyconverter;

import android.util.Log;

import androidx.databinding.InverseMethod;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public final class CurrencyViewModel extends ViewModel {
    private final static String LOG_TAG = CurrencyViewModel.class.getName();

    private final CurrencyModel mCurrencyModel = new CurrencyModel();

    private final MutableLiveData<String> mLiveDataConversionRateDollarsPerEuro = new MutableLiveData<String>();
    private final MutableLiveData<String> mLiveDataEuro = new MutableLiveData<String>();
    private final MutableLiveData<String> mLiveDataDollars = new MutableLiveData<String>();
    private final MutableLiveData<String> mLiveDataConversionResultDisplayText = new MutableLiveData<String>();

    public CurrencyViewModel() {
        log("CurrencyViewModel constructor");
        this.mLiveDataConversionRateDollarsPerEuro.setValue("" + mCurrencyModel.getConversionRateDollarsPerEuro());
        this.mLiveDataEuro.setValue("" + mCurrencyModel.getEuroValue());
        this.mLiveDataDollars.setValue("" + mCurrencyModel.getDollarValue());
        this.mLiveDataConversionResultDisplayText.setValue("" + mCurrencyModel.getConversionResultDisplayText());
    }

    public MutableLiveData<String> getLiveDataConversionRateDollarsPerEuro() {
        return mLiveDataConversionRateDollarsPerEuro;
    }
//    public void setLiveDataConversionRateDollarsPerEuro(LiveData<String> value) {
//        log("CurrencyViewModel setLiveDataConversionRateDollarsPerEuro " + value.getValue());
//        mLiveDataConversionRateDollarsPerEuro.setValue(value.getValue());
//    }

//    @InverseMethod("setLiveDataEuro")
    //public LiveData<String> getLiveDataEuro() {
    public MutableLiveData<String> getLiveDataEuro() {
        return mLiveDataEuro;
    }

//    public void setLiveDataEuro(String value) {
//        log("CurrencyViewModel setLiveDataEuro " + value);
//        mLiveDataEuro.setValue(value);
//    }

    public MutableLiveData<String> getLiveDataDollars() {
        return mLiveDataDollars;
    }
//    public void setLiveDataDollars(MutableLiveData<String> value) {
//        log("CurrencyViewModel setLiveDataDollars " + value);
//        mLiveDataDollars.setValue(value.getValue());
//    }

    public LiveData<String> getLiveDataConversionResultDisplayText() {
        return mLiveDataConversionResultDisplayText;
    }

    public void setConversionRateDollarsPerEuro(String value) {
        final boolean isNewValue = mCurrencyModel.setConversionRateDollarsPerEuroFromString(value);
        if(isNewValue) {
            this.mLiveDataDollars.setValue("" + mCurrencyModel.getDollarValue());
            updateConversionResultDisplayText();
        }
    }

    public void setEuro(String value) {
        final boolean isNewValue = mCurrencyModel.setEuroValueFromString(value);
        if(isNewValue) {
            this.mLiveDataDollars.setValue("" + mCurrencyModel.getDollarValue());
            updateConversionResultDisplayText();
        }
    }

    public void setDollars(String value) {
        final boolean isNewValue = mCurrencyModel.setDollarValueFromString(value);
        if(isNewValue) {
            this.mLiveDataEuro.setValue("" + mCurrencyModel.getEuroValue());
            updateConversionResultDisplayText();
        }
    }

    private void updateConversionResultDisplayText() {
        this.mLiveDataConversionResultDisplayText.setValue(mCurrencyModel.getConversionResultDisplayText());
    }

    private void log(String message) {
        Log.d(LOG_TAG, message);
    }
}
