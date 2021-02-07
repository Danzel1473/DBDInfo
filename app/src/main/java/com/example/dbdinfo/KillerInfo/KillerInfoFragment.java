package com.example.dbdinfo.KillerInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.dbdinfo.R;
import com.example.dbdinfo.databinding.FragmentKillerInfoBinding;

public class KillerInfoFragment extends Fragment implements View.OnClickListener {
    FragmentKillerInfoBinding binding;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_killer_info, container, false);
        View view = binding.getRoot();
        binding.killerInfoTrapperBtn.setOnClickListener(this);
        binding.killerInfoWraithBtn.setOnClickListener(this);
        binding.killerInfoHillbillyBtn.setOnClickListener(this);
        binding.killerInfoNurseBtn.setOnClickListener(this);
        binding.killerInfoShapeBtn.setOnClickListener(this);
        binding.killerInfoHagBtn.setOnClickListener(this);
        binding.killerInfoDoctorBtn.setOnClickListener(this);
        binding.killerInfoHuntressBtn.setOnClickListener(this);
        binding.killerInfoCannibalBtn.setOnClickListener(this);
        binding.killerInfoNightmareBtn.setOnClickListener(this);
        binding.killerInfoPigBtn.setOnClickListener(this);
        binding.killerInfoClownBtn.setOnClickListener(this);
        binding.killerInfoSpiritBtn.setOnClickListener(this);
        binding.killerInfoLegionBtn.setOnClickListener(this);
        binding.killerInfoPlagueBtn.setOnClickListener(this);
        binding.killerInfoGhostfaceBtn.setOnClickListener(this);
        binding.killerInfoDemogorgonBtn.setOnClickListener(this);
        binding.killerInfoOniBtn.setOnClickListener(this);
        binding.killerInfoDeathslingerBtn.setOnClickListener(this);
        binding.killerInfoExecutionerBtn.setOnClickListener(this);
        binding.killerInfoBlightBtn.setOnClickListener(this);
        binding.killerInfoTwinsBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.killerInfo_trapper_btn:
                bundle.putString("Killer", "트래퍼");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_wraith_btn:
                bundle.putString("Killer", "레이스");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_Hillbilly_btn:
                bundle.putString("Killer", "힐빌리");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_Nurse_btn:
                bundle.putString("Killer", "너스");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_shape_btn:
                bundle.putString("Killer", "쉐이프");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_hag_btn:
                bundle.putString("Killer", "해그");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_doctor_btn:
                bundle.putString("Killer", "닥터");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_huntress_btn:
                bundle.putString("Killer", "헌트리스");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_cannibal_btn:
                bundle.putString("Killer", "카니발");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_nightmare_btn:
                bundle.putString("Killer", "나이트메어");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_pig_btn:
                bundle.putString("Killer", "피그");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_clown_btn:
                bundle.putString("Killer", "클라운");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_spirit_btn:
                bundle.putString("Killer", "스피릿");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_legion_btn:
                bundle.putString("Killer", "군단");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_plague_btn:
                bundle.putString("Killer", "역병");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_ghostface_btn:
                bundle.putString("Killer", "고스트 페이스");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_demogorgon_btn:
                bundle.putString("Killer", "데모고르곤");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_oni_btn:
                bundle.putString("Killer", "악귀");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_deathslinger_btn:
                bundle.putString("Killer", "데스슬링거");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_executioner_btn:
                bundle.putString("Killer", "처형자");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_blight_btn:
                bundle.putString("Killer", "블라이트");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;
            case R.id.killerInfo_twins_btn:
                bundle.putString("Killer", "쌍둥이");
                ((KillerInfoActivity)getActivity()).setFrag(0, bundle);
                break;

        }
    }
}
