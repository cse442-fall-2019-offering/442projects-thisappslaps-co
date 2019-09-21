package com.example.a442projects_thisappslaps_co;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.a442projects_thisappslaps_co.Gallery.GalleryFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int MY_CAMERA_PERMISSIONS;

    private ImageButton mGalleryImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViewVariable();
        setListeners();
    }

    private void initializeViewVariable() {
        mGalleryImageButton = findViewById(R.id.gallery_image_btn);
    }

    private void setListeners() {
        mGalleryImageButton.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        requestPermissionForCamera();
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
            case R.id.gallery_image_btn:
                startFragment(new GalleryFragment(), true);
                break;
            default:
                break;
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
