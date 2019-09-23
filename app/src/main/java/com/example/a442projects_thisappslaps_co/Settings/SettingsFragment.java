package com.example.a442projects_thisappslaps_co.Settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.a442projects_thisappslaps_co.R;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    public SettingsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        ImageButton homeImageButton = view.findViewById(R.id.home_image_button);
        homeImageButton.setOnClickListener(this);

        return view;
    }
    
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.home_image_button) {
            assert getFragmentManager() != null;
            getFragmentManager().popBackStackImmediate();
        }
    }
}
