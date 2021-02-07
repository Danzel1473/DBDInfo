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

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.dbdinfo.Addon;
import com.example.dbdinfo.Item;
import com.example.dbdinfo.Killer;
import com.example.dbdinfo.PopupAddonActivity;
import com.example.dbdinfo.R;
import com.example.dbdinfo.databinding.FragmentSurvivorRandomAddonBinding;

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

public class SurvivorRandomAddonFragment extends Fragment implements View.OnClickListener {

    FragmentSurvivorRandomAddonBinding binding;
    Random sharedRandom = new Random();
    ArrayList<Item> itemList = new ArrayList<>();
    ArrayList<Addon> itemAddonList = new ArrayList<>();
    ArrayList<Addon> nowAddonList = new ArrayList<>();
    Item nowItem;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_survivor_random_addon, container, false);
        View view = binding.getRoot();

        binding.survivorAddonBtn.setOnClickListener(this);
        binding.survivorAddonImage1.setOnClickListener(this);
        binding.survivorAddonImage2.setOnClickListener(this);
        binding.survivorAddonItemImage.setOnClickListener(this);

        try {
            loadItemList();
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
            case R.id.survivor_addon_btn:
                AssetManager am = getResources().getAssets();
                List<Integer> addonNum = new ArrayList<Integer>();
                InputStream inputStream;
                String strColor = "init";

                itemAddonList.clear();
                nowAddonList.clear();

                int randomNum = sharedRandom.nextInt(itemList.size());
                nowItem = itemList.get(randomNum);

                try {
                    inputStream = am.open(nowItem.getImage() +".png");
                    if(nowItem.getRare().equals("평범함"))
                        strColor = "#ab713c";
                    else if(nowItem.getRare().equals("평범하지 않음"))
                        strColor = "#e8c252";
                    else if(nowItem.getRare().equals("희귀함"))
                        strColor = "#199b1e";
                    else if(nowItem.getRare().equals("아주 희귀함"))
                        strColor = "#cc3ad4";
                    else if(nowItem.getRare().equals("굉장히 희귀함"))
                        strColor = "#ff0955";
                    binding.survivorAddonItemName.setTextColor(Color.parseColor(strColor));
                    binding.survivorAddonItemName.setText(nowItem.getName());
                    binding.survivorAddonItemImage.setImageDrawable(Drawable.createFromStream(inputStream, null));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                try {
                    loadAddonList(nowItem.getType());
                } catch (JSONException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                for (int i = 0; i < 2; i++) {
                    do {
                        randomNum = sharedRandom.nextInt(itemAddonList.size());
                        if (addonNum.contains(randomNum))
                            continue;
                        addonNum.add(randomNum);
                        break;
                    } while(true);
                }
                try {
                    nowAddonList.add(itemAddonList.get(addonNum.get(0)));
                    nowAddonList.add(itemAddonList.get(addonNum.get(1)));

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

                    binding.survivorAddonImage1.setImageDrawable(Drawable.createFromStream(inputStream, null));
                    binding.survivorAddonImage1Text.setTextColor(Color.parseColor(strColor));
                    binding.survivorAddonImage1Text.setText(nowAddonList.get(0).getName());


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
                    binding.survivorAddonImage2.setImageDrawable(Drawable.createFromStream(inputStream, null));
                    binding.survivorAddonImage2Text.setTextColor(Color.parseColor(strColor));
                    binding.survivorAddonImage2Text.setText(nowAddonList.get(1).getName());

                    binding.survivorAddonImage1.setClickable(true);
                    binding.survivorAddonImage2.setClickable(true);

                }catch (IOException i){}

                break;
            case R.id.survivor_addon_item_image:
                try {
                    Intent intent = new Intent(v.getContext(), PopupAddonActivity.class);
                    intent.putExtra("name", nowItem.getName());
                    intent.putExtra("img", nowItem.getImage());
                    intent.putExtra("text", nowItem.getText());
                    startActivity(intent);
                }catch (Exception e){}
                break;
            case R.id.survivor_addon_image1:
                try {
                    Intent intent = new Intent(v.getContext(), PopupAddonActivity.class);
                    intent.putExtra("name", nowAddonList.get(0).getName());
                    intent.putExtra("img", nowAddonList.get(0).getImage());
                    intent.putExtra("text", nowAddonList.get(0).getText());
                    startActivity(intent);
                }catch (Exception e){}
                break;
            case R.id.survivor_addon_image2:
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

    private void loadAddonList(String type) throws IOException, JSONException {
        AssetManager am = getContext().getAssets();
        InputStream is = am.open("item_addon.json");
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String str;
        StringBuffer buffer = new StringBuffer();
        while ((str = rd.readLine()) != null) {
            buffer.append(str);
        }
        String receiveMsg = buffer.toString();

        JSONObject jsonObject = new JSONObject(receiveMsg);
        JSONArray jsonArray = jsonObject.getJSONArray(type);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject itemAddonObject = jsonArray.getJSONObject(i);
            Addon addon = new Addon(itemAddonObject.getString("name"), itemAddonObject.getString("image"),
                    itemAddonObject.getString("rare"), itemAddonObject.getString("text"));
            itemAddonList.add(addon);
        }

        Log.i("DBDInfo", "아이템 애드온 리스트 개수: " + itemAddonList.size());
    }

    private void loadItemList() throws IOException, JSONException {
        AssetManager am = getContext().getAssets();
        InputStream is = am.open("item.json");
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String str;
        StringBuffer buffer = new StringBuffer();
        while ((str = rd.readLine()) != null) {
            buffer.append(str);
        }
        String receiveMsg = buffer.toString();

        JSONObject jsonObject = new JSONObject(receiveMsg);
        JSONArray jsonArray = jsonObject.getJSONArray("item");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject itemObject = jsonArray.getJSONObject(i);
            Item item = new Item(itemObject.getString("name"), itemObject.getString("image"), itemObject.getString("type"), itemObject.getString("rare"),
                    itemObject.getString("text"));
            itemList.add(item);
        }

        Log.i("DBDInfo", "아이템 리스트 개수: " + itemList.size());
    }
}
