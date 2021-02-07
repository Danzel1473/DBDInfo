package com.example.dbdinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dbdinfo.KillerInfo.KillerInfoActivity;
import com.example.dbdinfo.RandomPerk.RandomPerkActivity;
import com.example.dbdinfo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.mainKillerInfoBtn.setOnClickListener(this);
        binding.mainRandomPerkBtn.setOnClickListener(this);
        binding.mainEtcInfoBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.main_killer_info_btn:
                Intent intent = new Intent(this, KillerInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.main_random_perk_btn:
                Intent intent2 = new Intent(this, RandomPerkActivity.class);
                startActivity(intent2);
                break;
            case R.id.main_etc_info_btn:
                Toast etcToast = Toast.makeText(this.getApplicationContext(),"추가 예정",Toast.LENGTH_LONG);
                etcToast.setGravity(Gravity.CENTER,0,200);
                etcToast.show();
                break;
        }
    }
}