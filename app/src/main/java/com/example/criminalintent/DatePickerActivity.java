package com.example.criminalintent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerActivity extends SingleFragmentActivity {

    public static Intent getIntent(Context context, Date date) {
        Intent intent = new Intent(context, DatePickerActivity.class);
        intent.putExtra(DatePickerFragment.ARG_DATE, date);
        return intent;
    }

    private static Date getDateFromIntent(Intent intent) {
        return (Date)intent.getSerializableExtra(DatePickerFragment.ARG_DATE);
    }

    @Override
    protected Fragment createFragment() {
        return DatePickerFragment.newInstance(DatePickerActivity.getDateFromIntent(getIntent()));
    }
}
