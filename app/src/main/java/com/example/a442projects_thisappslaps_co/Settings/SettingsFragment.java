package com.example.a442projects_thisappslaps_co.Settings;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a442projects_thisappslaps_co.DeviceSpecUtil;
import com.example.a442projects_thisappslaps_co.R;

import java.util.List;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    public SettingsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.settings, container, false);

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
