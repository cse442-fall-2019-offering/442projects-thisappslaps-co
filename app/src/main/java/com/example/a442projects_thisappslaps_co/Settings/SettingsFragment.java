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

    private SettingsController mSettingsController;

    public SettingsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettingsController = new SettingsController();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.settings, container, false);

        RecyclerView settingsRecyclerView = view.findViewById(R.id.gallery_recycler_view);
        settingsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), getSpanCount()));
        SettingsAdapter settingsAdapter = new SettingsAdapter(mSettingsController.createDummyList());
        settingsRecyclerView.setAdapter(settingsAdapter);

        ImageButton homeImageButton = view.findViewById(R.id.home_image_button);
        homeImageButton.setOnClickListener(this);

        return view;
    }

    private int getSpanCount() {
        int spanCount;
        int screenWidthDp = (int) DeviceSpecUtil.getScreenWidthDp(getContext());

        if (screenWidthDp < 350) {
            spanCount = 2;
        }
        else if (screenWidthDp < 500) {
            spanCount = 3;
        }
        else if (screenWidthDp < 750) {
            spanCount = 4;
        }
        else {
            spanCount = 5;
        }

        return spanCount;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.home_image_button) {
            assert getFragmentManager() != null;
            getFragmentManager().popBackStackImmediate();
        }
    }

    private class SettingsViewHolder extends RecyclerView.ViewHolder {

        SettingsViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.gallery_item, viewGroup, false));
        }

        void bind(int color) {
            Drawable drawable =
                    AppCompatResources.getDrawable(getContext(), R.drawable.rectangle_border);
            Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(wrappedDrawable, getContext().getColor(color));
            itemView.setBackground(drawable);
        }
    }

    private class SettingsAdapter extends RecyclerView.Adapter<SettingsViewHolder> {

        private List<Integer> mSettingsColorList;

        private SettingsAdapter(List<Integer> settingsColorList) {
            mSettingsColorList = settingsColorList;
        }

        @NonNull
        @Override
        public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SettingsViewHolder(LayoutInflater.from(getActivity()), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
            holder.bind(mSettingsColorList.get(position));
        }

        @Override
        public int getItemCount() {
            return mSettingsColorList.size();
        }
    }
}
