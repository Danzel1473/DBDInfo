package com.example.dbdinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dbdinfo.KillerInfo.KillerInfoActivity;
import com.example.dbdinfo.RandomPerk.RandomPerkActivity;
import com.example.dbdinfo.RandomPerk.RandomPerkAdapter;
import com.example.dbdinfo.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends FragmentActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_random_perk);

        ViewPager vp = findViewById(R.id.random_perk_vp);
        RandomPerkAdapter adapter = new RandomPerkAdapter(getSupportFragmentManager());
        vp.setOffscreenPageLimit(4);
        vp.setAdapter(adapter);

        TabLayout tab = findViewById(R.id.random_perk_tablayout);
        tab.setupWithViewPager(vp);
    }
}