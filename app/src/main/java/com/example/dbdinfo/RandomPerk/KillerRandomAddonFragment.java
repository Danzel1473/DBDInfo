package com.example.dbdinfo.RandomPerk;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
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

import com.example.dbdinfo.Addon;
import com.example.dbdinfo.Killer;
import com.example.dbdinfo.Perk;
import com.example.dbdinfo.PopupActivity;
import com.example.dbdinfo.PopupAddonActivity;
import com.example.dbdinfo.R;
import com.example.dbdinfo.databinding.FragmentKillerRandomAddonBindingImpl;
import com.example.dbdinfo.databinding.FragmentKillerRandomPerkBinding;

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

public class KillerRandomAddonFragment extends Fragment implements View.OnClickListener {

    FragmentKillerRandomAddonBindingImpl binding;
    Random sharedRandom = new Random();
    ArrayList<Killer> killerList = new ArrayList<>();
    ArrayList<Addon> killerAddonList = new ArrayList<>();
    ArrayList<Addon> addonList = new ArrayList<>();
    ArrayList<Addon> nowAddonList = new ArrayList<>();
    Killer nowKiller;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_killer_random_addon, container, false);
        View view = binding.getRoot();

        binding.killerAddonBtn.setOnClickListener(this);
        binding.killerAddonImage1.setOnClickListener(this);
        binding.killerAddonImage2.setOnClickListener(this);

        try {
            loadKillerList();
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
            case R.id.killer_addon_btn:
                AssetManager am = getResources().getAssets();
                List<Integer> addonNum = new ArrayList<Integer>();
                InputStream inputStream;
                String strColor = "init";

                killerAddonList.clear();
                nowAddonList.clear();

                int randomNum = sharedRandom.nextInt(killerList.size());
                nowKiller = killerList.get(randomNum);

                int killerimg = getContext().getResources().getIdentifier("drawable/" + nowKiller.getKillerimg(), null, getContext().getPackageName());

                binding.killerAddonKillerName.setText(nowKiller.getNick());
                binding.killerAddonKillerImage.setImageResource(killerimg);

                try {
                    loadAddonList(nowKiller.getNick());
                } catch (JSONException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                for (int i = 0; i < 2; i++) {
                    do {
                        randomNum = sharedRandom.nextInt(killerAddonList.size());
                        if (addonNum.contains(randomNum))
                            continue;
                        addonNum.add(randomNum);
                        break;
                    } while(true);
                }
                try {
                    nowAddonList.add(killerAddonList.get(addonNum.get(0)));
                    nowAddonList.add(killerAddonList.get(addonNum.get(1)));

                    inputStream = am.open(nowAddonList.get(0).getImage()+".png");

                    if(nowAddonList.get(0).getRare().equals("평범함"))
                        strColor = "#ab713c";
                    else if(nowAddonList.get(0).getRare().equals("평범하지 않음"))
                        strColor = "#e8c252";
                    else if(nowAddonList.get(0).getRare().equals("희귀함"))
                        strColor = "#199b1e";
                    else if(nowAddonList.get(0).getRare().equals("아주 희귀함"))
                        strColor = "#cc3ad4";
                    else if(nowAddonList.get(0).getRare().equals("굉장히 희귀함"))
                        strColor = "#ff0955";

                    binding.killerAddonImage1.setImageDrawable(Drawable.createFromStream(inputStream, null));
                    binding.killerAddonImage1Text.setTextColor(Color.parseColor(strColor));
                    binding.killerAddonImage1Text.setText(nowAddonList.get(0).getName());

                    if(nowAddonList.get(1).getRare().equals("평범함"))
                        strColor = "#ab713c";
                    else if(nowAddonList.get(1).getRare().equals("평범하지 않음"))
                        strColor = "#e8c252";
                    else if(nowAddonList.get(1).getRare().equals("희귀함"))
                        strColor = "#199b1e";
                    else if(nowAddonList.get(1).getRare().equals("아주 희귀함"))
                        strColor = "#cc3ad4";
                    else if(nowAddonList.get(1).getRare().equals("굉장히 희귀함"))
                        strColor = "#ff0955";

                    inputStream = am.open(nowAddonList.get(1).getImage()+".png");
                    binding.killerAddonImage2.setImageDrawable(Drawable.createFromStream(inputStream, null));
                    binding.killerAddonImage2Text.setTextColor(Color.parseColor(strColor));
                    binding.killerAddonImage2Text.setText(nowAddonList.get(1).getName());

                    binding.killerAddonImage1.setClickable(true);
                    binding.killerAddonImage2.setClickable(true);
                }catch (IOException i){}

                break;
            case R.id.killer_addon_image1:
                try {
                    Intent intent = new Intent(v.getContext(), PopupAddonActivity.class);
                    intent.putExtra("name", nowAddonList.get(0).getName());
                    intent.putExtra("img", nowAddonList.get(0).getImage());
                    intent.putExtra("text", nowAddonList.get(0).getText());
                    startActivity(intent);
                }catch (Exception e){}
                break;
            case R.id.killer_addon_image2:
                try {
                    Intent intent = new Intent(v.getContext(), PopupAddonActivity.class);
                    intent.putExtra("name", nowAddonList.get(1).getName());
                    intent.putExtra("img", nowAddonList.get(1).getImage());
                    intent.putExtra("text", nowAddonList.get(1).getText());
                    startActivity(intent);
                }catch (Exception e){}
                break;
        }

    }

    private void loadAddonList(String forwho) throws IOException, JSONException {
        AssetManager am = getContext().getAssets();
        InputStream is = am.open("killer_addon.json");
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String str;
        StringBuffer buffer = new StringBuffer();
        while ((str = rd.readLine()) != null) {
            buffer.append(str);
        }
        String receiveMsg = buffer.toString();

        JSONObject jsonObject = new JSONObject(receiveMsg);
        JSONArray jsonArray = jsonObject.getJSONArray(forwho);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject killerAddonObject = jsonArray.getJSONObject(i);
            Addon addon = new Addon(killerAddonObject.getString("name"), killerAddonObject.getString("image"),
                    killerAddonObject.getString("rare"), killerAddonObject.getString("text"));
            killerAddonList.add(addon);
        }

        Log.i("DBDInfo", "살인마 애드온 리스트 개수: " + addonList.size());
    }

    private void loadKillerList() throws IOException, JSONException {
        AssetManager am = getContext().getAssets();
        InputStream is = am.open("killer.json");
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String str;
        StringBuffer buffer = new StringBuffer();
        while ((str = rd.readLine()) != null) {
            buffer.append(str);
        }
        String receiveMsg = buffer.toString();

        JSONObject jsonObject = new JSONObject(receiveMsg);
        JSONArray jsonArray = jsonObject.getJSONArray("killer");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject killerObject = jsonArray.getJSONObject(i);
            Killer killer = new Killer(killerObject.getString("killerimg"), killerObject.getString("nick"));
            killerList.add(killer);
        }

        Log.i("DBDInfo", "살인마 리스트 개수: " + killerList.size());
    }
}
