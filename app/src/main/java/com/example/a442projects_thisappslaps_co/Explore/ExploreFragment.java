package com.example.a442projects_thisappslaps_co.Explore;

import androidx.fragment.app.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a442projects_thisappslaps_co.DeviceSpecUtil;
import com.example.a442projects_thisappslaps_co.R;

import java.util.List;




public class ExploreFragment extends Fragment implements View.OnClickListener {

    private ExploreController mExploreControler;

    public ExploreFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExploreControler = new ExploreController();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.explore_fragment, container, false);

        RecyclerView exploreRecyclerView = view.findViewById(R.id.explore_recycler_view);
        exploreRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), getSpanCount()));
        ExploreAdapter exploreAdapter = new ExploreAdapter(mExploreControler.createDummyList());
        exploreRecyclerView.setAdapter(exploreAdapter);

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

    private class ExploreViewHolder extends RecyclerView.ViewHolder {

        ExploreViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.explore_item, viewGroup, false));
        }

        void bind(int color) {
            Drawable drawable =
                    AppCompatResources.getDrawable(getContext(), R.drawable.rectangle_border);
            Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(wrappedDrawable, getContext().getColor(color));
            itemView.setBackground(drawable);
        }
    }

    private class ExploreAdapter extends RecyclerView.Adapter<ExploreViewHolder> {

        private List<Integer> mExploreColorList;

        private ExploreAdapter(List<Integer> exploreColorList) {
            mExploreColorList = exploreColorList;
        }

        @NonNull
        @Override
        public ExploreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ExploreViewHolder(LayoutInflater.from(getActivity()), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ExploreViewHolder holder, int position) {
            holder.bind(mExploreColorList.get(position));
        }

        @Override
        public int getItemCount() {
            return mExploreColorList.size();
        }
    }
}


