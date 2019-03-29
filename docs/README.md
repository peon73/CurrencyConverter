# Android App trying to use two-way databinding

This Android App is a currency converter.

The purpose of the App is to experiment with Android MVVM with MVVM/ViewModel/LiveData/databinding.

![screenshot.jpg](images/screenshot.jpg)

There are three input fields as in the above screenshot.
When one of the three fields is changed, then one of the other two fields is also changed.
The text below the input fields is also changed when any of the three fields are changed.
Field 1: The exchange rate.
Field 2: Euros
Field 3: Dollars

Field 3 should always be equal to a multiplication with field 1 and field 2 as factors.
When the user edits field 1, then field 3 becomes updated.
When the user edits field 2, then field 3 becomes updated.
When the user edits field 3, then field 2 becomes updated.
