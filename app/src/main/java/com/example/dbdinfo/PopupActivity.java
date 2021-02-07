package com.example.dbdinfo;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class PopupActivity extends Activity {
    ImageView perkImage;
    TextView perkName, perkText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_perk);
        String perkNameString = getIntent().getStringExtra("perkName").toString();
        String perkimage = getIntent().getStringExtra("perkImg");
        String perktext = getIntent().getStringExtra("perkText").toString();
        int[] a = getIntent().getIntArrayExtra("spanInt");

        SpannableStringBuilder builder1 = new SpannableStringBuilder(perktext);
        buildString(a, builder1);

        perkImage = findViewById(R.id.popup_perk_image);
        perkName = findViewById(R.id.popup_perk_name);
        perkText = findViewById(R.id.popup_perk_text);
        perkName.setText(perkNameString);

        AssetManager am = getResources().getAssets();
        InputStream inputStream;
        try{
            inputStream = am.open(perkimage+".png");
            perkImage.setImageDrawable(Drawable.createFromStream(inputStream, null));
        }catch (Exception e){}

        perkText.setText(builder1);

        Button killerBtn = findViewById(R.id.popup_perk_btn);
        killerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void buildString(int[] a, SpannableStringBuilder builder1){
        for(int i = 0; i< a.length; i+=6){
            builder1.setSpan(new ForegroundColorSpan(Color.parseColor("#ffff00")), a[i], a[i+1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder1.setSpan(new ForegroundColorSpan(Color.parseColor("#00ff00")), a[i+2], a[i+3], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder1.setSpan(new ForegroundColorSpan(Color.parseColor("#8b00ff")), a[i+4], a[i+5], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

}
