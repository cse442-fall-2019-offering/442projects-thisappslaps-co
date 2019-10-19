package com.example.a442projects_thisappslaps_co.Settings;

import android.content.SharedPreferences;
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

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCH1 = "switch1";
    public static final String SWITCH2 = "switch2";

    private boolean switch1_checked_value;
    private boolean switch2_checked_value;

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
        switch2 = view.findViewById(R.id.switch_2_switch_compat);
        loadData();
        updateViews();

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveData();
                Toast.makeText(getActivity().getApplicationContext(),
                        "Switch1 has Changed", Toast.LENGTH_LONG).show();
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveData();
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

    public void saveData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(SWITCH1, switch1.isChecked());
        editor.putBoolean(SWITCH2, switch2.isChecked());

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, 0);
        switch1_checked_value = sharedPreferences.getBoolean(SWITCH1, false);
        switch2_checked_value = sharedPreferences.getBoolean(SWITCH2, false);
    }

    public void updateViews(){
        switch1.setChecked(switch1_checked_value);
        switch2.setChecked(switch2_checked_value);
    }

}
