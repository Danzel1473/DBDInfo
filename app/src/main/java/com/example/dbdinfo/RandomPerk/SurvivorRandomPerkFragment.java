package com.example.dbdinfo.RandomPerk;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.dbdinfo.Perk;
import com.example.dbdinfo.PopupActivity;
import com.example.dbdinfo.R;
import com.example.dbdinfo.databinding.FragmentSurvivorRandomPerkBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SurvivorRandomPerkFragment extends Fragment implements View.OnClickListener {

    FragmentSurvivorRandomPerkBinding binding;

    Random sharedRandom = new Random();
    ArrayList<Perk> perkList = new ArrayList<>();
    ArrayList<Perk> nowPerkList = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_survivor_random_perk, container, false);
        View view = binding.getRoot();

        binding.survivorRandomBtn.setOnClickListener(this);
        binding.survivorRandomPerk1.setOnClickListener(this);
        binding.survivorRandomPerk2.setOnClickListener(this);
        binding.survivorRandomPerk3.setOnClickListener(this);
        binding.survivorRandomPerk4.setOnClickListener(this);

        try {
            loadPerkList();
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.survivor_random_btn:
                AssetManager am = getResources().getAssets();
                List<Integer> perkNum = new ArrayList<Integer>();
                InputStream inputStream;
                nowPerkList.clear();
                if(!binding.survivorRandomCheckboxExhaustion.isChecked() && !binding.survivorRandomCheckboxRecovery.isChecked()){
                    for (int i = 0; i < 4; i++) {
                        do {
                            int randomNum = sharedRandom.nextInt(perkList.size());
                            if (perkNum.contains(randomNum))
                                continue;
                            perkNum.add(randomNum);
                            break;
                        } while(true);
                    }
                } else if (binding.survivorRandomCheckboxExhaustion.isChecked() && binding.survivorRandomCheckboxRecovery.isChecked()){
                    do{
                        int randomNum = sharedRandom.nextInt(perkList.size());
                        if(perkList.get(randomNum).getType().equals("탈진") ){
                            perkNum.add(randomNum);
                            break;
                        }
                    } while (true);
                    do{
                        int randomNum = sharedRandom.nextInt(perkList.size());
                        if(perkList.get(randomNum).getType().equals("회복") ){
                            perkNum.add(randomNum);
                            break;
                        }
                    } while (true);
                    for (int i = 0; i < 2; i++) {
                        do {
                            int randomNum = sharedRandom.nextInt(perkList.size());
                            if (perkNum.contains(randomNum))
                                continue;
                            perkNum.add(randomNum);
                            break;
                        } while(true);
                    }
                } else if (binding.survivorRandomCheckboxExhaustion.isChecked() || binding.survivorRandomCheckboxRecovery.isChecked()){
                    do{
                        String type;
                        if(binding.survivorRandomCheckboxExhaustion.isChecked()){
                            type="탈진";
                        }
                        else{
                            type="회복";
                        }
                        int randomNum = sharedRandom.nextInt(perkList.size());
                        if(perkList.get(randomNum).getType().equals(type) ){
                            perkNum.add(randomNum);
                            break;
                        }
                    } while (true);
                    for (int i = 0; i < 3; i++) {
                        do {
                            int randomNum = sharedRandom.nextInt(perkList.size());
                            if (perkNum.contains(randomNum))
                                continue;
                            perkNum.add(randomNum);
                            break;
                        } while(true);
                    }
                }
                nowPerkList.add(perkList.get(perkNum.get(0)));
                nowPerkList.add(perkList.get(perkNum.get(1)));
                nowPerkList.add(perkList.get(perkNum.get(2)));
                nowPerkList.add(perkList.get(perkNum.get(3)));

                try {
                    Perk perk = nowPerkList.get(0);
                    inputStream = am.open(perk.getImg()+".png");
                    binding.survivorRandomPerk1.setImageDrawable(Drawable.createFromStream(inputStream, null));
                    binding.survivorRandomPerk1Text.setText(perk.getName());

                    perk = nowPerkList.get(1);
                    inputStream = am.open(perk.getImg()+".png");
                    binding.survivorRandomPerk2.setImageDrawable(Drawable.createFromStream(inputStream, null));
                    binding.survivorRandomPerk2Text.setText(perk.getName());

                    perk = nowPerkList.get(2);
                    inputStream = am.open(perk.getImg()+".png");
                    binding.survivorRandomPerk3.setImageDrawable(Drawable.createFromStream(inputStream, null));
                    binding.survivorRandomPerk3Text.setText(perk.getName());

                    perk = nowPerkList.get(3);
                    inputStream = am.open(perk.getImg()+".png");
                    binding.survivorRandomPerk4.setImageDrawable(Drawable.createFromStream(inputStream, null));
                    binding.survivorRandomPerk4Text.setText(perk.getName());
                    inputStream.close();
                } catch (IOException i){}

                break;
            case R.id.survivor_random_perk1:
                try {
                    Intent intent = new Intent(v.getContext(), PopupActivity.class);
                    intent.putExtra("perkName", nowPerkList.get(0).getName());
                    intent.putExtra("spanInt", nowPerkList.get(0).getIntforcolor());
                    intent.putExtra("perkImg", nowPerkList.get(0).getImg());
                    intent.putExtra("perkText", nowPerkList.get(0).getText());
                    startActivity(intent);
                }catch (Exception e){}
                break;
            case R.id.survivor_random_perk2:
                try {
                    Intent intent = new Intent(v.getContext(), PopupActivity.class);
                    intent.putExtra("perkName", nowPerkList.get(1).getName());
                    intent.putExtra("spanInt", nowPerkList.get(1).getIntforcolor());
                    intent.putExtra("perkImg", nowPerkList.get(1).getImg());
                    intent.putExtra("perkText", nowPerkList.get(1).getText());
                    startActivity(intent);
                }catch (Exception e){}
                break;
            case R.id.survivor_random_perk3:
                try {
                    Intent intent = new Intent(v.getContext(), PopupActivity.class);
                    intent.putExtra("perkName", nowPerkList.get(2).getName());
                    intent.putExtra("spanInt", nowPerkList.get(2).getIntforcolor());
                    intent.putExtra("perkImg", nowPerkList.get(2).getImg());
                    intent.putExtra("perkText", nowPerkList.get(2).getText());
                    startActivity(intent);
                }catch (Exception e){}
                break;
            case R.id.survivor_random_perk4:
                try {
                    Intent intent = new Intent(v.getContext(), PopupActivity.class);
                    intent.putExtra("perkName", nowPerkList.get(3).getName());
                    intent.putExtra("spanInt", nowPerkList.get(3).getIntforcolor());
                    intent.putExtra("perkImg", nowPerkList.get(3).getImg());
                    intent.putExtra("perkText", nowPerkList.get(3).getText());
                    startActivity(intent);
                }catch (Exception e){}
                break;
        }
    }

    private void loadPerkList() throws IOException, JSONException {
        AssetManager am = getContext().getAssets();
        InputStream is = am.open("survivor_perk.json");
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String str;
        StringBuffer buffer = new StringBuffer();
        while ((str = rd.readLine()) != null) {
            buffer.append(str);
        }
        String receiveMsg = buffer.toString();

        JSONObject jsonObject = new JSONObject(receiveMsg);
        JSONArray jsonArray = jsonObject.getJSONArray("survivor_perk");
        int[] a;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject survivorPerkObject = jsonArray.getJSONObject(i);
            JSONArray colorArray = survivorPerkObject.getJSONArray("intforcolor");
            a = new int[colorArray.length()];
            for(int j=0; j<colorArray.length(); j++)
                a[j] = colorArray.getInt(j);
            Perk perk = new Perk(survivorPerkObject.getString("name"), survivorPerkObject.getString("for"),
                    survivorPerkObject.getString("image"), survivorPerkObject.getString("type"), survivorPerkObject.getString("text"), a);
            perkList.add(perk);
        }

        Log.i("DBDInfo", "생존자 퍽 리스트 개수: " + perkList.size());
    }
}
