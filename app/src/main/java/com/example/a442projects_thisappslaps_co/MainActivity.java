package com.example.a442projects_thisappslaps_co;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a442projects_thisappslaps_co.ARObjects.ARObjectsController;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.a442projects_thisappslaps_co.Gallery.GalleryFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int MY_CAMERA_PERMISSIONS;

    private BottomSheetBehavior mBottomSheetBehavior;
    private RecyclerView mARObjectsRecyclerView;
    private ImageButton mARObjectsImageButton;
    private ImageButton mGalleryImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();
        requestPermissionForCamera();

        initializeViewVariables();
        setListeners();
        setARObjectsAdapter();
    }

    private void initializeViewVariables() {
        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.ar_objects_bottom_sheet));
        mARObjectsRecyclerView = findViewById(R.id.ar_objects_recycler_view);
        mARObjectsImageButton = findViewById(R.id.ar_objects_image_btn);
        mGalleryImageButton = findViewById(R.id.gallery_image_btn);
    }

    private void setListeners() {
        mARObjectsImageButton.setOnClickListener(this);
        mGalleryImageButton.setOnClickListener(this);
    }

    private void setARObjectsAdapter() {
        mARObjectsRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mARObjectsRecyclerView.setAdapter(
                new ARObjectsAdapter(new ARObjectsController().createARObjectsDummyList()));
    }

    private void requestPermissionForCamera() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.CAMERA},
                    MY_CAMERA_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (MY_CAMERA_PERMISSIONS == requestCode) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ar_objects_image_btn:
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.gallery_image_btn:
                startFragment(new GalleryFragment(), true);
                break;
            default:
                break;
        }
    }

    private class ARObjectsHolder extends RecyclerView.ViewHolder {

        ARObjectsHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.ar_object_item, viewGroup, false));
        }

        void bind(Integer drawableRes) {
            itemView.setBackgroundResource(drawableRes);
        }
    }

    private class ARObjectsAdapter extends RecyclerView.Adapter<ARObjectsHolder> {

        private ArrayList<Integer> mARObjectDrawableResList;

        ARObjectsAdapter(ArrayList<Integer> arObjectDrawableResList) {
            mARObjectDrawableResList = arObjectDrawableResList;
        }

        @NonNull
        @Override
        public ARObjectsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ARObjectsHolder(LayoutInflater.from(getApplicationContext()), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ARObjectsHolder holder, int position) {
            holder.bind(mARObjectDrawableResList.get(position));
        }

        @Override
        public int getItemCount() {
            return mARObjectDrawableResList.size();
        }
    }

    private void startFragment(Fragment fragment, boolean shouldAddToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if (shouldAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
