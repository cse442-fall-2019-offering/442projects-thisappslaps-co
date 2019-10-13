package com.example.a442projects_thisappslaps_co.Settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.CompoundButton;
import android.widget.Toast;


import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.a442projects_thisappslaps_co.R;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    SwitchCompat switch1;
    SwitchCompat switch2;
    SwitchCompat switch3;
    SwitchCompat switch4;
    SwitchCompat switch5;

    public SettingsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        ImageButton backImageButton = view.findViewById(R.id.back_image_btn);
        backImageButton.setOnClickListener(this);

        switch1 = view.findViewById(R.id.switch_1_switch_compat);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                }
                else {

                }
            }
        });

        switch2 = view.findViewById(R.id.switch_2_switch_compat);
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }
                else {

                }
            }
        });
        switch3 = view.findViewById(R.id.switch_3_switch_compat);
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }
                else {

                }
            }
        });

        switch4 = view.findViewById(R.id.switch_4_switch_compat);
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }
                else {

                }
            }
        });
        switch5 = view.findViewById(R.id.switch_5_switch_compat);
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }
                else {

                }
            }
        });

        return view;
    }
    
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_image_btn) {
            assert getFragmentManager() != null;
            getFragmentManager().popBackStackImmediate();
        }
    }

}
