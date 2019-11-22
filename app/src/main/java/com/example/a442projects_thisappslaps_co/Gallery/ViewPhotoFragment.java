package com.example.a442projects_thisappslaps_co.Gallery;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.a442projects_thisappslaps_co.MainActivity;
import com.example.a442projects_thisappslaps_co.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class ViewPhotoFragment extends Fragment {

    private Project mCurrentProject;
    private Bitmap mBitmap;
    private GalleryController mGalleryController;
    private boolean mIsViewedFromGallery;

    public ViewPhotoFragment(
            Project project,
            Bitmap bitmap,
            GalleryController galleryController,
            boolean isViewedFromGallery) {
        mCurrentProject = project;
        mBitmap = bitmap;
        mGalleryController = galleryController;
        mIsViewedFromGallery = isViewedFromGallery;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.view_photo_fragment, container, false);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), mCurrentProject.getUri());
        view.setBackground(bitmapDrawable);

        ImageButton exitImageButton = view.findViewById(R.id.exit_image_btn);
        exitImageButton.setOnClickListener(view1 -> {
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        if (!mIsViewedFromGallery) {
            view.setBackground(new BitmapDrawable(getResources(), mBitmap));
            FloatingActionButton acceptFloatingActionButton =
                    view.findViewById(R.id.accept_floating_action_btn);
            acceptFloatingActionButton.setVisibility(View.VISIBLE);
            acceptFloatingActionButton.setOnClickListener(view12 -> {
                acceptPhoto();
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStackImmediate();
                }
            });
        }
        else {
            ImageButton deleteImageButton = view.findViewById(R.id.delete_image_btn);
            deleteImageButton.setVisibility(View.VISIBLE);
            deleteImageButton.setOnClickListener(view13 -> {
                mGalleryController.deleteProject(mCurrentProject);
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStackImmediate();
                }
            });
        }

        return view;
    }

    private void acceptPhoto() {
        try {
            MainActivity.saveBitmapToDisk(mBitmap, mCurrentProject);
        } catch (IOException e) {
            Toast.makeText(getContext(), "Photo was not saved",
                    Toast.LENGTH_LONG).show();
        }
    }
}
