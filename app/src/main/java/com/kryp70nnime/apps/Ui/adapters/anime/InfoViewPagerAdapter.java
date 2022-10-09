package com.kryp70nnime.apps.Ui.adapters.anime;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kryp70nnime.apps.Ui.Activity.detail.fragment.EpisodesFragment;
import com.kryp70nnime.apps.Ui.Activity.detail.fragment.InformationFragment;

public class InfoViewPagerAdapter extends FragmentPagerAdapter {

    public InfoViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0 ){
            fragment = new InformationFragment();
        } else if (position == 1){
            fragment = new EpisodesFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position){
        String title = null;

        if (position == 0){
            title = "Information";
        } else if (position == 1) {
            title = "Episodes";
        }
        return title;
    }
}
