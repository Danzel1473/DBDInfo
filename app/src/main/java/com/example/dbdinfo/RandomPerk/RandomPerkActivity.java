package com.example.dbdinfo.RandomPerk;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.dbdinfo.KillerInfo.KillerInfoFragment;
import com.example.dbdinfo.R;
import com.google.android.material.tabs.TabLayout;

public class RandomPerkActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_random_perk);

        ViewPager vp = findViewById(R.id.random_perk_vp);
        RandomPerkAdapter adapter = new RandomPerkAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

        TabLayout tab = findViewById(R.id.random_perk_tablayout);
        tab.setupWithViewPager(vp);

    }
}
