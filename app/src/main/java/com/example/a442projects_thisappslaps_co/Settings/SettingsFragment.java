package com.example.a442projects_thisappslaps_co.Settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.CompoundButton;
import android.widget.Toast;


import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.a442projects_thisappslaps_co.R;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    SwitchCompat switch1;
    SwitchCompat switch2;
    SwitchCompat switch4;
    SwitchCompat switch5;
    TextView username_text_view;
    Button saveButton;
    EditText username_edit_text;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCH1 = "switch1";
    public static final String SWITCH2 = "switch2";
    public static final String USERNAME = "username1";
    public static final String SWITCH4 = "switch4";
    public static final String SWITCH5 = "switch5";

    private boolean switch1_checked_value;
    private boolean switch2_checked_value;
    private String  username_value;
    private boolean switch4_checked_value;
    private boolean switch5_checked_value;

    public SettingsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        ImageButton backImageButton = view.findViewById(R.id.back_image_btn);
        backImageButton.setOnClickListener(this);


        switch1 = view.findViewById(R.id.switch_2_switch_compat);
        switch2 = view.findViewById(R.id.switch_3_switch_compat);
        switch4 = view.findViewById(R.id.switch_4_switch_compat);
        switch5 = view.findViewById(R.id.switch_5_switch_compat);
        saveButton = view.findViewById(R.id.user_name_save_button);
        username_text_view = view.findViewById(R.id.user_name_text_view);
        username_edit_text = view.findViewById(R.id.user_name_edit_text);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username_text_view.setText(username_edit_text.getText().toString());
                saveData();
                Toast.makeText(getActivity().getApplicationContext(),
                        "Updated ", Toast.LENGTH_LONG).show();
            }
        });

        loadData();
        updateViews();

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveData();
//                Toast.makeText(getActivity().getApplicationContext(),
//                        "Switch1 has Changed", Toast.LENGTH_LONG).show();
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveData();
            }
        });

        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveData();
//                Toast.makeText(getActivity().getApplicationContext(),
//                        "Switch1 has Changed", Toast.LENGTH_LONG).show();
            }
        });

        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveData();
//                Toast.makeText(getActivity().getApplicationContext(),
//                        "Switch1 has Changed", Toast.LENGTH_LONG).show();
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
        editor.putString(USERNAME, username_edit_text.getText().toString());
        editor.putBoolean(SWITCH4, switch4.isChecked());
        editor.putBoolean(SWITCH5, switch5.isChecked());

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, 0);
        switch1_checked_value = sharedPreferences.getBoolean(SWITCH1, false);
        switch2_checked_value = sharedPreferences.getBoolean(SWITCH2, false);

        username_value = sharedPreferences.getString(USERNAME, "User Name");
        Toast.makeText(getActivity().getApplicationContext(),
                "Updated to: " + username_value , Toast.LENGTH_LONG).show();
        switch4_checked_value = sharedPreferences.getBoolean(SWITCH4, false);
        switch5_checked_value = sharedPreferences.getBoolean(SWITCH5, false);

    }

    public void updateViews(){
        switch1.setChecked(switch1_checked_value);
        switch2.setChecked(switch2_checked_value);
        switch4.setChecked(switch4_checked_value);
        switch5.setChecked(switch5_checked_value);
        username_text_view.setText(username_value);
    }

}
