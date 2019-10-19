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


import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.a442projects_thisappslaps_co.R;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    SwitchCompat switch3;
    SwitchCompat switch4;
    SwitchCompat switch5;
    TextView username_text_view;
    EditText username_edit_text;
    Button username_saveButton;
    EditText password_edit_text;
    Button password_saveButton;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String SWITCH3 = "switch3";
    public static final String SWITCH4 = "switch4";
    public static final String SWITCH5 = "switch5";

    private String  username_value;
    private String  password_value;
    private boolean switch3_checked_value;
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


        switch3 = view.findViewById(R.id.switch_3_switch_compat);
        switch4 = view.findViewById(R.id.switch_4_switch_compat);
        switch5 = view.findViewById(R.id.switch_5_switch_compat);

        username_saveButton = view.findViewById(R.id.user_name_save_button);
        username_text_view = view.findViewById(R.id.user_name_text_view);
        username_edit_text = view.findViewById(R.id.user_name_edit_text);

        password_edit_text = view.findViewById(R.id.password_edit_text);

        username_saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username_text_view.setText(username_edit_text.getText().toString());
                saveData();
            }
        });

        loadData();
        updateViews();

        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveData();
            }
        });

        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveData();
            }
        });

        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

        editor.putString(USERNAME, username_edit_text.getText().toString());
        editor.putString(PASSWORD, password_edit_text.getText().toString());
        editor.putBoolean(SWITCH3, switch3.isChecked());
        editor.putBoolean(SWITCH4, switch4.isChecked());
        editor.putBoolean(SWITCH5, switch5.isChecked());

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, 0);
        username_value = sharedPreferences.getString(USERNAME, "User Name");
        password_value = sharedPreferences.getString(PASSWORD, "");
        switch3_checked_value = sharedPreferences.getBoolean(SWITCH3, false);
        switch4_checked_value = sharedPreferences.getBoolean(SWITCH4, false);
        switch5_checked_value = sharedPreferences.getBoolean(SWITCH5, false);

    }

    public void updateViews(){
        switch3.setChecked(switch3_checked_value);
        switch4.setChecked(switch4_checked_value);
        switch5.setChecked(switch5_checked_value);
        username_text_view.setText(username_value);
    }

}
