package com.call.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;

public class OnBoarding extends AppCompatActivity {
    ViewPager viewPager;
    OnBoardingAdapter onBoardingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        initSharedPref();
        viewPager = findViewById(R.id.viewpager);
        setViewPagerWithFragment();
    }

    private void initSharedPref() {
        SharedPreferences sharedPreferences = SharedPreferencesManager.createSharedPreferences(this);
        sharedPreferences.edit().putBoolean("IS_USER_FIRST_TIME", true).apply();
    }

    private void setViewPagerWithFragment() {

        onBoardingAdapter = new OnBoardingAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(onBoardingAdapter);
    }
}