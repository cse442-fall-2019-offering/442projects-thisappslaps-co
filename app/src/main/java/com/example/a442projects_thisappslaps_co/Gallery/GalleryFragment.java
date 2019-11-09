package com.example.a442projects_thisappslaps_co.Gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a442projects_thisappslaps_co.DeviceSpecUtil;
import com.example.a442projects_thisappslaps_co.R;

import java.util.List;

import static com.example.a442projects_thisappslaps_co.MainActivity.startFragment;

public class GalleryFragment extends Fragment implements View.OnClickListener {

    private GalleryController mGalleryController;
    private RecyclerView mGalleryRecyclerView;

    public GalleryFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGalleryController = new GalleryController(getContext());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.gallery_fragment, container, false);

        mGalleryRecyclerView = view.findViewById(R.id.gallery_recycler_view);
        mGalleryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), getSpanCount()));

        ImageButton backImageButton = view.findViewById(R.id.back_button);
        backImageButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        GalleryAdapter galleryAdapter = new GalleryAdapter(mGalleryController.getProjectList());
        mGalleryRecyclerView.setAdapter(galleryAdapter);
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
        if (view.getId() == R.id.back_button) {
            assert getFragmentManager() != null;
            getFragmentManager().popBackStackImmediate();
        }
    }

    private class GalleryViewHolder extends RecyclerView.ViewHolder {

        GalleryViewHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.gallery_item, viewGroup, false));
        }

        void bind(Project project) {
            Bitmap bitmap = BitmapFactory.decodeFile(project.getUri(), new BitmapFactory.Options());
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
            roundedBitmapDrawable.setCornerRadius(20f);
            itemView.setBackground(roundedBitmapDrawable);
            itemView.setOnClickListener(view -> {
                if (getFragmentManager() != null) {
                    startFragment(new ViewPhotoFragment(project, null, true), getFragmentManager(), true);
                }
            });
        }
    }

    private class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {

        private List<Project> mGalleryList;

        private GalleryAdapter(List<Project> galleryColorList) {
            mGalleryList = galleryColorList;
        }

        @NonNull
        @Override
        public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new GalleryViewHolder(LayoutInflater.from(getActivity()), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
            holder.bind(mGalleryList.get(position));
        }

        @Override
        public int getItemCount() {
            return mGalleryList.size();
        }
    }
}
