package com.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders; // implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0'

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.currencyconverter.databinding.ActivityMainBinding;

public final class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = MainActivity.class.getName();

    private CurrencyViewModel mCurrencyViewModel;

    private TextView mTextViewConversionResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mCurrencyViewModel = ViewModelProviders.of(this).get(CurrencyViewModel.class);
        activityMainBinding.setTheCurrencyViewModel(mCurrencyViewModel);
        activityMainBinding.setLifecycleOwner(this); // https://developer.android.com/topic/libraries/data-binding/architecture.html#livedata
        //setContentView(activityMainBinding.getRoot()); // youtube video "Android Jetpack- ViewModel" time 2:58

        mCurrencyViewModel.getLiveDataConversionRateDollarsPerEuro().observe(this, new Observer<String>(){
            @Override
            public void onChanged(String value) {
            log("getLiveDataConversionRateDollarsPerEuro().observe value : " + value);
            mCurrencyViewModel.setConversionRateDollarsPerEuro(value);
            }
        });

        mCurrencyViewModel.getLiveDataEuro().observe(this, new Observer<String>(){
            @Override
            public void onChanged(String value) {
            log("getLiveDataEuro().observe value : " + value);
            mCurrencyViewModel.setEuro(value);
            }
        });

        mCurrencyViewModel.getLiveDataDollars().observe(this, new Observer<String>(){
            @Override
            public void onChanged(String value) {
            log("getLiveDataDollars().observe value : " + value);
            mCurrencyViewModel.setDollars(value);
            }
        });
    }

    private void log(String message) {
        Log.d(LOG_TAG, message);
    }
}