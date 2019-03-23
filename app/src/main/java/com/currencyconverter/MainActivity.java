package com.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public final class MainActivity extends AppCompatActivity {

    private CurrencyModel mCurrencyModel = new CurrencyModel();

    private TextView mTextViewConversionResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText editTextConversionRateDollarsPerEuro = (EditText) findViewById(R.id.editTextConversionRateDollarsPerEuro);
        final EditText editTextDollar = (EditText) findViewById(R.id.editTextDollar);
        final EditText editTextEuro = (EditText) findViewById(R.id.editTextEuro);
        mTextViewConversionResult = (TextView) findViewById(R.id.textViewConversionResult);


        editTextConversionRateDollarsPerEuro.setText("" + mCurrencyModel.getConversionRateDollarsPerEuro());
        editTextDollar.setText("" + mCurrencyModel.getDollarValue());
        editTextEuro.setText("" + mCurrencyModel.getEuroValue());
        mTextViewConversionResult.setText(mCurrencyModel.getConversionResultDisplayText());


        editTextConversionRateDollarsPerEuro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                final boolean isNewValue = mCurrencyModel.setConversionRateDollarsPerEuroFromString(editTextConversionRateDollarsPerEuro.getText().toString());
                if(isNewValue) {
                    editTextDollar.setText("" + mCurrencyModel.getDollarValue());
                    updateConversionResultDisplayText();
                }
            }
        });

        editTextDollar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                final boolean isNewValue = mCurrencyModel.setDollarValueFromString(editTextDollar.getText().toString());
                if(isNewValue) {
                    editTextEuro.setText("" + mCurrencyModel.getEuroValue());
                    updateConversionResultDisplayText();
                }
            }
        });

        editTextEuro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                final boolean isNewValue = mCurrencyModel.setEuroValueFromString(editTextEuro.getText().toString());
                if(isNewValue) {
                    editTextDollar.setText("" + mCurrencyModel.getDollarValue());
                    updateConversionResultDisplayText();
                }
            }
        });
    }

    private void updateConversionResultDisplayText() {
        mTextViewConversionResult.setText(mCurrencyModel.getConversionResultDisplayText());
    }
}