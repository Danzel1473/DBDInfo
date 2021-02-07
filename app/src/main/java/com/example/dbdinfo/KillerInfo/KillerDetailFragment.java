package com.example.dbdinfo.KillerInfo;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.dbdinfo.Killer;
import com.example.dbdinfo.Perk;
import com.example.dbdinfo.PopupActivity;
import com.example.dbdinfo.R;
import com.example.dbdinfo.databinding.FragmentKillerDetailBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

public class KillerDetailFragment extends Fragment implements View.OnClickListener {
    FragmentKillerDetailBinding binding;
    Killer killerData;
    ArrayList<Perk> perkList;
    View view;
    String perkString1, perkString2, perkString3;
    int killerimg, abilimg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_killer_detail, container, false);
        view = binding.getRoot();
        perkList = new ArrayList<Perk>();
        String killer = this.getArguments().getString("Killer");

        binding.detailPerkImagebtn1.setOnClickListener(this);
        binding.detailPerkImagebtn2.setOnClickListener(this);
        binding.detailPerkImagebtn3.setOnClickListener(this);

        try {
            loadKillerList(killer);
            loadKillerPerkList(killer);
            setImage(perkList.get(0).getImg(), perkList.get(1).getImg(),perkList.get(2).getImg());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        initKillerData(killerData.getKillerimg(), killerData.getAbilimg());


        if(killerData.getNick().equals("쉐이프")){ //쉐이프일때
            setProfile(killerData.getNick(), killerimg, killerData.getName(), killerData.getSpeed(), killerData.getAbilspeed(), killerData.getHeartbeat(), killerData.getWeapon(),
                    killerData.getDlc(), abilimg , killerData.getAbilname(), killerData.getAbiltextdefault(),killerData.getAbiltitle1(), killerData.getAbiltext1(), killerData.getAbiltitle2(), killerData.getAbiltext2(),
                    killerData.getAdv(), killerData.getDisadv(), killerData.getTiptext(), true);

        } else if(!killerData.getAbiltextdefault().equals("") && !killerData.getAbiltext1().equals("") && !killerData.getAbiltext2().equals("")){ //세개 다 있을때
            setProfile(killerData.getNick(), killerimg, killerData.getName(), killerData.getSpeed(), killerData.getAbilspeed(), killerData.getHeartbeat(), killerData.getWeapon(),
                    killerData.getDlc(), abilimg , killerData.getAbilname(), killerData.getAbiltextdefault(),killerData.getAbiltitle1(), killerData.getAbiltext1(), killerData.getAbiltitle2(),
                    killerData.getAbiltext2(), killerData.getAdv(), killerData.getDisadv(), killerData.getTiptext());

        } else if(killerData.getAbiltextdefault().equals("") && !killerData.getAbiltext1().equals("") && !killerData.getAbiltext2().equals("")){ //디폴트가 없을때
            setProfile(killerData.getNick(), killerimg, killerData.getName(), killerData.getSpeed(), killerData.getAbilspeed(), killerData.getHeartbeat(), killerData.getWeapon(),
                    killerData.getDlc(), abilimg , killerData.getAbilname(), killerData.getAbiltitle1(), killerData.getAbiltext1(), killerData.getAbiltitle2(), killerData.getAbiltext2(),
                    killerData.getAdv(), killerData.getDisadv(), killerData.getTiptext());

        } else if(!killerData.getAbiltextdefault().equals("") && killerData.getAbiltext1().equals("") && killerData.getAbiltext2().equals("")){ //디폴트만 있을때
            setProfile(killerData.getNick(), killerimg, killerData.getName(), killerData.getSpeed(), killerData.getAbilspeed(), killerData.getHeartbeat(), killerData.getWeapon(),
                    killerData.getDlc(), abilimg, killerData.getAbilname(), killerData.getAbiltextdefault(), killerData.getAdv(), killerData.getDisadv(), killerData.getTiptext());
        }

        return view;
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.detail_perk_Imagebtn1:
                try {
                    Intent intent = new Intent(v.getContext(), PopupActivity.class);
                    intent.putExtra("perkName", perkList.get(0).getName());
                    intent.putExtra("spanInt", perkList.get(0).getIntforcolor());
                    intent.putExtra("perkImg", perkList.get(0).getImg());
                    intent.putExtra("perkText", perkList.get(0).getText());
                    startActivity(intent);
                }catch (Exception e){}
                break;
            case R.id.detail_perk_Imagebtn2:
                try {
                    Intent intent = new Intent(v.getContext(), PopupActivity.class);
                    intent.putExtra("perkName", perkList.get(1).getName());
                    intent.putExtra("spanInt", perkList.get(1).getIntforcolor());
                    intent.putExtra("perkImg", perkList.get(1).getImg());
                    intent.putExtra("perkText", perkList.get(1).getText());
                    startActivity(intent);
                }catch (Exception e){}
                break;
            case R.id.detail_perk_Imagebtn3:
                try {
                    Intent intent = new Intent(v.getContext(), PopupActivity.class);
                    intent.putExtra("perkName", perkList.get(2).getName());
                    intent.putExtra("spanInt", perkList.get(2).getIntforcolor());
                    intent.putExtra("perkImg", perkList.get(2).getImg());
                    intent.putExtra("perkText", perkList.get(2).getText());
                    startActivity(intent);
                }catch (Exception e){}
                break;
        }
    }

    protected void setProfile(String NickName, int image, String name, String speed, String abilSpeed, String heartbeat, String weapon, String dlc, int abilimage, String abilname,
                              String abilDefaulttext,String abilDetailname1, String abiltext1, String abilDetailname2, String abiltext2, String advan, String disadvan, String tiptext){
        //디폴트, 1, 2 다 있을때
        binding.detailKillerNickname.setText(NickName);
        binding.detailKillerImageview.setImageResource(image);
        binding.detailKillerName.setText(name);
        binding.detailKillerSpeed.setText(speed);
        binding.detailKillerAbilSpeed.setText(abilSpeed);
        binding.detailHeart.setText(heartbeat);
        binding.detailWeapon.setText(weapon);
        binding.detailDlc.setText(dlc);
        binding.detailAbilIcon.setImageResource(abilimage);
        binding.detailAbilName.setText(abilname);
        binding.detailAbilDefaultText.setVisibility(View.VISIBLE);
        binding.detailAbilDefaultText.setText(abilDefaulttext);
        binding.detailAbilNameDetail1.setText(abilDetailname1);
        binding.detailAbilText1.setText(abiltext1);
        binding.detailAbilNameDetail2.setText(abilDetailname2);
        binding.detailAbilText2.setText(abiltext2);
        binding.detailEvaluationAdvantages.setText(advan);
        binding.detailEvaluationDisadvantages.setText(disadvan);
        binding.detailTip.setText(tiptext);
    }
    protected void setProfile(String NickName, int image, String name, String speed, String abilSpeed, String heartbeat, String weapon, String dlc, int abilimage, String abilname,
                              String abilDetailname1, String abiltext1, String abilDetailname2, String abiltext2, String advan, String disadvan, String tiptext){
        //디폴트 없고 1,2
        binding.detailKillerNickname.setText(NickName);
        binding.detailKillerImageview.setImageResource(image);
        binding.detailKillerName.setText(name);
        binding.detailKillerSpeed.setText(speed);
        binding.detailKillerAbilSpeed.setText(abilSpeed);
        binding.detailHeart.setText(heartbeat);
        binding.detailWeapon.setText(weapon);
        binding.detailDlc.setText(dlc);
        binding.detailAbilIcon.setImageResource(abilimage);
        binding.detailAbilName.setText(abilname);
        binding.detailAbilNameDetail1.setText(abilDetailname1);
        binding.detailAbilText1.setText(abiltext1);
        binding.detailAbilNameDetail2.setText(abilDetailname2);
        binding.detailAbilText2.setText(abiltext2);
        binding.detailEvaluationAdvantages.setText(advan);
        binding.detailEvaluationDisadvantages.setText(disadvan);
        binding.detailTip.setText(tiptext);
    }

    protected void setProfile(String NickName, int image, String name, String speed, String abilSpeed, String heartbeat, String weapon, String dlc, int abilimage,
                              String abilname, String abilDefaulttext,String abilDetailname1, String abiltext1, String abilDetailname2, String abiltext2, String advan, String disadvan, String tiptext, boolean gif){
        //쉐이프용
        binding.detailKillerNickname.setText(NickName);
        binding.detailKillerImageview.setImageResource(image);
        binding.detailKillerName.setText(name);
        binding.detailKillerSpeed.setText(speed);
        binding.detailKillerAbilSpeed.setText(abilSpeed);
        binding.detailHeart.setText(heartbeat);
        binding.detailWeapon.setText(weapon);
        binding.detailDlc.setText(dlc);
        Glide.with(this).load(abilimage).into(binding.detailAbilIcon);
        binding.detailAbilName.setText(abilname);
        binding.detailAbilDefaultText.setVisibility(View.VISIBLE);
        binding.detailAbilDefaultText.setText(abilDefaulttext);
        binding.detailAbilNameDetail1.setText(abilDetailname1);
        binding.detailAbilText1.setText(abiltext1);
        binding.detailAbilNameDetail2.setText(abilDetailname2);
        binding.detailAbilText2.setText(abiltext2);
        binding.detailEvaluationAdvantages.setText(advan);
        binding.detailEvaluationDisadvantages.setText(disadvan);
        binding.detailTip.setText(tiptext);
    }

    protected void setProfile(String NickName, int image, String name, String speed, String abilSpeed, String heartbeat, String weapon, String dlc, int abilimage, String abilname,
                              String abilDetailname, String abiltext1, String advan, String disadvan, String tiptext){
        //텍스트1만 있을때
        binding.detailKillerNickname.setText(NickName);
        binding.detailKillerImageview.setImageResource(image);
        binding.detailKillerName.setText(name);
        binding.detailKillerSpeed.setText(speed);
        binding.detailKillerAbilSpeed.setText(abilSpeed);
        binding.detailHeart.setText(heartbeat);
        binding.detailWeapon.setText(weapon);
        binding.detailDlc.setText(dlc);
        binding.detailAbilIcon.setImageResource(abilimage);
        binding.detailAbilName.setText(abilname);
        binding.detailAbilNameDetail1.setText(abilDetailname);
        binding.detailAbilText1.setText(abiltext1);
        binding.detailAbilNameDetail2.setVisibility(View.GONE);
        binding.detailAbilText2.setVisibility(View.GONE);
        binding.detailEvaluationAdvantages.setText(advan);
        binding.detailEvaluationDisadvantages.setText(disadvan);
        binding.detailTip.setText(tiptext);
    }

    protected void setProfile(String NickName, int image, String name, String speed, String abilSpeed, String heartbeat, String weapon, String dlc, int abilimage, String abilname, String abildefault,
                              String advan, String disadvan, String tiptext){
        //디폴트만 있을때
        binding.detailKillerNickname.setText(NickName);
        binding.detailKillerImageview.setImageResource(image);
        binding.detailKillerName.setText(name);
        binding.detailKillerSpeed.setText(speed);
        binding.detailKillerAbilSpeed.setText(abilSpeed);
        binding.detailHeart.setText(heartbeat);
        binding.detailWeapon.setText(weapon);
        binding.detailDlc.setText(dlc);
        binding.detailAbilIcon.setImageResource(abilimage);
        binding.detailAbilName.setText(abilname);
        binding.detailAbilDefaultText.setVisibility(View.VISIBLE);
        binding.detailAbilDefaultText.setText(abildefault);
        binding.detailAbilNameDetail1.setVisibility(View.GONE);
        binding.detailAbilText1.setVisibility(View.GONE);
        binding.detailAbilNameDetail2.setVisibility(View.GONE);
        binding.detailAbilText2.setVisibility(View.GONE);
        binding.detailEvaluationAdvantages.setText(advan);
        binding.detailEvaluationDisadvantages.setText(disadvan);
        binding.detailTip.setText(tiptext);
    }

    protected void setProfile(String NickName, int image, String name, String speed, String abilSpeed, String heartbeat, String weapon, String dlc, int abilimage, String abilname, String abildefault,
                              String abilDetailname, String abiltext1, String advan, String disadvan, String tiptext){
        //디폴트와 1만 있을때
        binding.detailKillerNickname.setText(NickName);
        binding.detailKillerImageview.setImageResource(image);
        binding.detailKillerName.setText(name);
        binding.detailKillerSpeed.setText(speed);
        binding.detailKillerAbilSpeed.setText(abilSpeed);
        binding.detailHeart.setText(heartbeat);
        binding.detailWeapon.setText(weapon);
        binding.detailDlc.setText(dlc);
        binding.detailAbilIcon.setImageResource(abilimage);
        binding.detailAbilName.setText(abilname);
        binding.detailAbilDefaultText.setVisibility(View.VISIBLE);
        binding.detailAbilDefaultText.setText(abildefault);
        binding.detailAbilNameDetail1.setText(abilDetailname);
        binding.detailAbilText1.setText(abiltext1);
        binding.detailAbilNameDetail1.setVisibility(View.GONE);
        binding.detailAbilText1.setVisibility(View.GONE);
        binding.detailAbilNameDetail2.setVisibility(View.GONE);
        binding.detailAbilText2.setVisibility(View.GONE);
        binding.detailEvaluationAdvantages.setText(advan);
        binding.detailEvaluationDisadvantages.setText(disadvan);
        binding.detailTip.setText(tiptext);
    }

    protected void initKillerData(String killerimgtext, String abilimgtext){
        killerimg = getContext().getResources().getIdentifier("drawable/" + killerimgtext, null, getContext().getPackageName());
        abilimg = getContext().getResources().getIdentifier("drawable/" + abilimgtext, null, getContext().getPackageName());
    }

    protected void setPerkString(String perkStr1, String perkStr2, String perkStr3){
        perkString1 = perkStr1;
        perkString2 = perkStr2;
        perkString3 = perkStr3;
    }

    private void loadKillerList(String nick) throws IOException, JSONException {
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
            if(killerObject.getString("nick").equals(nick)){
                killerData = new Killer(killerObject.getString("killerimg"), killerObject.getString("nick"),
                        killerObject.getString("name"), killerObject.getString("speed"), killerObject.getString("abilspeed"), killerObject.getString("heartbeat"),
                        killerObject.getString("weapon"),killerObject.getString("dlc"), killerObject.getString("abildefaulttext"), killerObject.getString("abiltext1"),
                        killerObject.getString("abiltext2"),killerObject.getString("adv"), killerObject.getString("disadv"),killerObject.getString("tiptext"),
                        killerObject.getString("abilimg"),killerObject.getString("abilname"), killerObject.getString("abiltitle1"),killerObject.getString("abiltitle2"));

            }
        }

        Log.i("DBDInfo", "살인마 : " + killerData.getNick());
    }

    private void loadKillerPerkList(String nick) throws IOException, JSONException{
        AssetManager am = getContext().getAssets();
        InputStream is = am.open("killer_perk.json");
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String str;
        StringBuffer buffer = new StringBuffer();
        while ((str = rd.readLine()) != null) {
            buffer.append(str);
        }
        String receiveMsg = buffer.toString();

        JSONObject jsonObject = new JSONObject(receiveMsg);
        JSONArray jsonArray = jsonObject.getJSONArray("killer_perk");
        int[] a;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject killerPerkObject = jsonArray.getJSONObject(i);
            if(killerPerkObject.getString("for").equals(nick)){
                JSONArray colorArray = killerPerkObject.getJSONArray("intforcolor");
                a = new int[colorArray.length()];
                for(int j = 0; j< colorArray.length(); j++){
                    a[j] = colorArray.getInt(j);
                }
                Perk killerPerk = new Perk(killerPerkObject.getString("name"), killerPerkObject.getString("image"), killerPerkObject.getString("text"), a);
                perkList.add(killerPerk);
            }
        }
    }

    private void setImage(String perk1, String perk2, String perk3) throws IOException{
        AssetManager am = getResources().getAssets();
        InputStream inputStream;

        inputStream = am.open(perkList.get(0).getImg()+".png");
        binding.detailPerkImagebtn1.setImageDrawable(Drawable.createFromStream(inputStream, null));
        binding.detailPerkName1.setText(perkList.get(0).getName());

        inputStream = am.open(perkList.get(1).getImg()+".png");
        binding.detailPerkImagebtn2.setImageDrawable(Drawable.createFromStream(inputStream, null));
        binding.detailPerkName2.setText(perkList.get(1).getName());

        inputStream = am.open(perkList.get(2).getImg()+".png");
        binding.detailPerkImagebtn3.setImageDrawable(Drawable.createFromStream(inputStream, null));
        binding.detailPerkName3.setText(perkList.get(2).getName());
    }
}
