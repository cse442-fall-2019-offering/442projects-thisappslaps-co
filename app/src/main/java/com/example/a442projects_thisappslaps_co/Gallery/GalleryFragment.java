package com.example.a442projects_thisappslaps_co.Gallery;

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

public class GalleryFragment extends Fragment implements View.OnClickListener {

    private GalleryController mGalleryController;

    public GalleryFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGalleryController = new GalleryController();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.gallery_fragment, container, false);

        RecyclerView galleryRecyclerView = view.findViewById(R.id.gallery_recycler_view);
        galleryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), getSpanCount()));
        GalleryAdapter galleryAdapter = new GalleryAdapter(mGalleryController.createDummyList());
        galleryRecyclerView.setAdapter(galleryAdapter);

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

    private class GallerViewHolder extends RecyclerView.ViewHolder {

        GallerViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
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

    private class GalleryAdapter extends RecyclerView.Adapter<GallerViewHolder> {

        private List<Integer> mGalleryColorList;

        private GalleryAdapter(List<Integer> galleryColorList) {
            mGalleryColorList = galleryColorList;
        }

        @NonNull
        @Override
        public GallerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new GallerViewHolder(LayoutInflater.from(getActivity()), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull GallerViewHolder holder, int position) {
            holder.bind(mGalleryColorList.get(position));
        }

        @Override
        public int getItemCount() {
            return mGalleryColorList.size();
        }
    }
}
