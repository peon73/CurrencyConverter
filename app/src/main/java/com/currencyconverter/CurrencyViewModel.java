package com.currencyconverter;

import android.util.Log;

import androidx.databinding.Observable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public final class CurrencyViewModel extends ViewModel implements Observable {
    private final static String LOG_TAG = CurrencyViewModel.class.getName();

    private final CurrencyModel mCurrencyModel = new CurrencyModel();
//    public CurrencyModel getCurrencyModel() {
//        return mCurrencyModel;
//    }


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

    public void observeConversionRateDollarsPerEuro(LifecycleOwner lifecycleOwner, Observer<String> observer) {
        this.mLiveDataConversionRateDollarsPerEuro.observe(lifecycleOwner, observer);
    }
    public void observeEuro(LifecycleOwner lifecycleOwner, Observer<String> observer) {
        this.mLiveDataEuro.observe(lifecycleOwner, observer);
    }
    public void observeDollars(LifecycleOwner lifecycleOwner, Observer<String> observer) {
        this.mLiveDataDollars.observe(lifecycleOwner, observer);
    }



    //@Bindable
    public MutableLiveData<String> getConversionRateDollarsPerEuro() {
        log("getConversionRateDollarsPerEuro " + mLiveDataConversionRateDollarsPerEuro.getValue());
        return mLiveDataConversionRateDollarsPerEuro;
    }

    public void setConversionRateDollarsPerEuro(String value) {
        log("setConversionRateDollarsPerEuro " + value);
        if(mCurrencyModel.isConversionRateDollarsPerEuroChanged(value)) {
            mCurrencyModel.setConversionRateDollarsPerEuroFromString(value);

            this.mLiveDataConversionRateDollarsPerEuro.setValue(value);
            this.mLiveDataDollars.setValue("" + mCurrencyModel.getDollarValue());
            updateConversionResultDisplayText();
        }
    }

    //@Bindable
//    public LiveData<String> getDollars() {
    public MutableLiveData<String> getDollars() {
        log("getDollars " + mLiveDataDollars.getValue());
        return mLiveDataDollars;
    }

    //public void setDollars(LiveData<String> value) {
    public void setDollars(String value) {
        log("setDollars " + value);
        if(mCurrencyModel.isDollarValueChanged(value)) {
            mCurrencyModel.setDollarValueFromString(value);

            this.mLiveDataDollars.setValue(value);
            this.mLiveDataEuro.setValue("" + mCurrencyModel.getEuroValue());
            updateConversionResultDisplayText();
        }
    }

    //@Bindable
    public MutableLiveData<String> getEuro() {
        log("getEuro " + mLiveDataEuro.getValue());
        return this.mLiveDataEuro;
    }

    public void setEuro(String value) {
        log("setEuro " + value);
        if(mCurrencyModel.isEuroValueChanged(value)) {
            mCurrencyModel.setEuroString(value);

            this.mLiveDataEuro.setValue(value);
            this.mLiveDataDollars.setValue("" + mCurrencyModel.getDollarValue());
            updateConversionResultDisplayText();
        }
    }


    //@Bindable
    public LiveData<String> getConversionResultDisplayText() {
        return mLiveDataConversionResultDisplayText;
    }


    private void updateConversionResultDisplayText() {
        this.mLiveDataConversionResultDisplayText.setValue(mCurrencyModel.getConversionResultDisplayText());
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        log("addOnPropertyChangedCallback");
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        log("removeOnPropertyChangedCallback");
    }

    private void log(String message) {
        Log.d(LOG_TAG, message);
    }
}