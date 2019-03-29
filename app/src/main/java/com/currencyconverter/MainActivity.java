package com.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders; // implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0'
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.currencyconverter.databinding.ActivityMainBinding; // generated class

public final class MainActivity extends AppCompatActivity {
    private final static String LOG_TAG = MainActivity.class.getName();

    private CurrencyViewModel mCurrencyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final LifecycleOwner lifecycleOwner = this;

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mCurrencyViewModel = ViewModelProviders.of(this).get(CurrencyViewModel.class);
        activityMainBinding.setTheCurrencyViewModel(mCurrencyViewModel);
        activityMainBinding.setLifecycleOwner(lifecycleOwner); // https://developer.android.com/topic/libraries/data-binding/architecture.html#livedata

        mCurrencyViewModel.observeConversionRateDollarsPerEuro(lifecycleOwner);
        mCurrencyViewModel.observeEuro(lifecycleOwner);
        mCurrencyViewModel.observeDollars(lifecycleOwner);
    }
}