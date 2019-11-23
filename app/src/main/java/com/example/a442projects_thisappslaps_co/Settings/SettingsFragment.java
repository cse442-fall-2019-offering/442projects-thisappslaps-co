package com.example.a442projects_thisappslaps_co.Settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.a442projects_thisappslaps_co.R;

import static com.example.a442projects_thisappslaps_co.MainActivity.PREFS_NAME;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private SettingsChangedListener mSettingsChangedListener;

    private SwitchCompat dark_mode_switch;
    private SwitchCompat two_factor_switch;
    private SwitchCompat save_photos_switch;
    private SwitchCompat mPlaneRendererSwitch;
    private TextView username_text_view;
    private EditText username_edit_text;
    private Button username_saveButton;
    private EditText password_edit_text;
    private Button password_saveButton;

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String DARK_MODE = "dark_mode";
    public static final String TWO_FACTOR = "two_factor";
    public static final String SAVE_PHOTO = "save_photo";
    public static final String PLANE_RENDERER = "plane_renderer";

    private String  username_value;
    private String  password_value;
    private boolean dark_mode_checked_value;
    private boolean two_factor_checked_value;
    private boolean save_photo_checked_value;
    private boolean enable_plane_renderer;

    public SettingsFragment(SettingsChangedListener settingsChangedListener) {
        mSettingsChangedListener = settingsChangedListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(this);

        dark_mode_switch = view.findViewById(R.id.dark_mode_switch_compat);
        two_factor_switch = view.findViewById(R.id.two_factor_switch_compat);
        save_photos_switch = view.findViewById(R.id.save_photos_switch_compat);
        mPlaneRendererSwitch = view.findViewById(R.id.enable_plane_renderer_switch_compat);

        username_saveButton = view.findViewById(R.id.user_name_save_button);
        username_text_view = view.findViewById(R.id.user_name_text_view);
        username_edit_text = view.findViewById(R.id.user_name_edit_text);

        password_edit_text = view.findViewById(R.id.password_edit_text);
        password_saveButton = view.findViewById(R.id.password_save_button);

        username_saveButton.setOnClickListener(view1 -> {
            username_text_view.setText(username_edit_text.getText().toString());
            saveData();
        });

        password_saveButton.setOnClickListener(view12 -> {
            username_text_view.setText(username_edit_text.getText().toString());
            saveData();
        });

        loadData();
        updateViews();

        dark_mode_switch.setOnCheckedChangeListener((buttonView, isChecked) -> saveData());
        two_factor_switch.setOnCheckedChangeListener((buttonView, isChecked) -> saveData());
        save_photos_switch.setOnCheckedChangeListener((buttonView, isChecked) -> saveData());
        mPlaneRendererSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            saveData();
            mSettingsChangedListener.enablePlaneRendering(b);
        });

        return view;
    }
    
    @Override
    public void onClick(View view) {
        assert getFragmentManager() != null;
        getFragmentManager().popBackStackImmediate();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USERNAME, username_edit_text.getText().toString());
        editor.putString(PASSWORD, password_edit_text.getText().toString());
        editor.putBoolean(DARK_MODE, dark_mode_switch.isChecked());
        editor.putBoolean(TWO_FACTOR, two_factor_switch.isChecked());
        editor.putBoolean(SAVE_PHOTO, save_photos_switch.isChecked());
        editor.putBoolean(PLANE_RENDERER, mPlaneRendererSwitch.isChecked());

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, 0);
        username_value = sharedPreferences.getString(USERNAME, "User Name");
        password_value = sharedPreferences.getString(PASSWORD, "");
        dark_mode_checked_value = sharedPreferences.getBoolean(DARK_MODE, false);
        two_factor_checked_value = sharedPreferences.getBoolean(TWO_FACTOR, false);
        save_photo_checked_value = sharedPreferences.getBoolean(SAVE_PHOTO, false);
        enable_plane_renderer = sharedPreferences.getBoolean(PLANE_RENDERER, false);
    }

    public void updateViews(){
        dark_mode_switch.setChecked(dark_mode_checked_value);
        two_factor_switch.setChecked(two_factor_checked_value);
        save_photos_switch.setChecked(save_photo_checked_value);
        mPlaneRendererSwitch.setChecked(enable_plane_renderer);
        username_text_view.setText(username_value);
    }

}
