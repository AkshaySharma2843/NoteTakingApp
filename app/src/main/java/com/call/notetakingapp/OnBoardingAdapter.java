package com.call.notetakingapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class OnBoardingAdapter extends FragmentPagerAdapter {

    public OnBoardingAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new OnBoardingFragment(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
