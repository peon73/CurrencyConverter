package com.currencyconverter;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public final class CurrencyViewModel extends ViewModel
{
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

    public void observeConversionRateDollarsPerEuro(LifecycleOwner lifecycleOwner) {
        final CurrencyViewModel currencyViewModel = this;
        this.mLiveDataConversionRateDollarsPerEuro.observe(lifecycleOwner,  new Observer<String>(){
            @Override
            public void onChanged(String value) {
            log("observeConversionRateDollarsPerEuro onChanged value : " + value);
            currencyViewModel.setConversionRateDollarsPerEuro(value);
            }
        });
    }

    public void observeEuro(LifecycleOwner lifecycleOwner){
        final CurrencyViewModel currencyViewModel = this;
        this.mLiveDataEuro.observe(lifecycleOwner,  new Observer<String>(){
            @Override
            public void onChanged(String value) {
            log("observeEuro onChanged value : " + value);
            currencyViewModel.setEuro(value);
            }
        });
    }

    public void observeDollars(LifecycleOwner lifecycleOwner) {
        final CurrencyViewModel currencyViewModel = this;
        this.mLiveDataDollars.observe(lifecycleOwner, new Observer<String>(){
            @Override
            public void onChanged(String value) {
                log("observeDollars onChanged value : " + value);
                currencyViewModel.setDollars(value);
            }
        });
    }

    //     Is it possible to avoid exposing "MutableLiveData<String>" for a two way databinding ?
    //     Doing that seem to be similarly ugly (regarding encapsulation) as exposing a modifiable list instead
    //     of exposiing an unmodifiableCollection.
    //     It would seem to be more clean to expose a LiveData instead of MutableLiveData
    //     but then the two way code does not seem to work.
    //     I have tried using a setter method with either "String" or "LiveData<String>"
    //     but have not succeeded.

    //@Bindable
    public MutableLiveData<String> getConversionRateDollarsPerEuro() {
        log("getConversionRateDollarsPerEuro " + mLiveDataConversionRateDollarsPerEuro.getValue());
        return mLiveDataConversionRateDollarsPerEuro;
    }

    private void setConversionRateDollarsPerEuro(String value) {
        log("setConversionRateDollarsPerEuro " + value);
        if(mCurrencyModel.isConversionRateDollarsPerEuroChanged(value)) {
            mCurrencyModel.setConversionRateDollarsPerEuroFromString(value);

            this.mLiveDataConversionRateDollarsPerEuro.setValue(value);
            this.mLiveDataDollars.setValue("" + mCurrencyModel.getDollarValue());
            updateConversionResultDisplayText();
        }
    }

    //@Bindable
    public MutableLiveData<String> getDollars() {
        log("getDollars " + mLiveDataDollars.getValue());
        return mLiveDataDollars;
    }

    private void setDollars(String value) {
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

    private void setEuro(String value) {
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

    private void log(String message) {
        Log.d(LOG_TAG, message);
    }

}