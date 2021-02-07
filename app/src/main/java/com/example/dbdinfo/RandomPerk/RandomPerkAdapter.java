package com.example.dbdinfo.RandomPerk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class RandomPerkAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> items;
    private ArrayList<String> itext = new ArrayList<String>();
    public RandomPerkAdapter(FragmentManager fm){
        super(fm);
        items=new ArrayList<Fragment>();
        items.add(new SurvivorRandomPerkFragment());
        items.add(new SurvivorRandomAddonFragment());
        items.add(new KillerRandomPerkFragment());
        items.add(new KillerRandomAddonFragment());

        itext.add("생존자 퍽");
        itext.add("생존자 애드온");
        itext.add("살인마 퍽");
        itext.add("살인마 애드온");

    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return itext.get(position);
    }
}
