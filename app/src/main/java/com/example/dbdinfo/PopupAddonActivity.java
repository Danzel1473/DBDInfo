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

public class PopupAddonActivity extends Activity {
    ImageView perkImage;
    TextView perkName, perkText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_perk);
        String nameString = getIntent().getStringExtra("name").toString();
        String image = getIntent().getStringExtra("img");
        String text = getIntent().getStringExtra("text").toString();

        perkImage = findViewById(R.id.popup_perk_image);
        perkName = findViewById(R.id.popup_perk_name);
        perkText = findViewById(R.id.popup_perk_text);
        perkName.setText(nameString);

        AssetManager am = getResources().getAssets();
        InputStream inputStream;
        try{
            inputStream = am.open(image+".png");
            perkImage.setImageDrawable(Drawable.createFromStream(inputStream, null));
        }catch (Exception e){}

        perkText.setText(text);

        Button killerBtn = findViewById(R.id.popup_perk_btn);
        killerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
