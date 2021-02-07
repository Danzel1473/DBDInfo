package com.example.dbdinfo.KillerInfo;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.dbdinfo.R;

public class KillerInfoActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_killer_info);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, new KillerInfoFragment());
        transaction.commit();

    }

    public void setFrag(int n, Bundle bundle){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch(n){
            case 0:
                KillerDetailFragment killerDetailFragment = new KillerDetailFragment();
                killerDetailFragment.setArguments(bundle);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fragment_container, killerDetailFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                break;
            case 3:
                break;
        }
    }

}
