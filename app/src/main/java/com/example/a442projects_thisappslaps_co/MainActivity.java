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

import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.example.a442projects_thisappslaps_co.ARObjects.ARFragment;

import com.example.a442projects_thisappslaps_co.Shop.ShopFragment;
import com.example.a442projects_thisappslaps_co.Settings.SettingsFragment;
import com.example.a442projects_thisappslaps_co.Explore.ExploreFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.a442projects_thisappslaps_co.Gallery.GalleryFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int MY_CAMERA_PERMISSIONS;

    private ImageButton mARObjectsImageButton;
    private ImageButton mGalleryImageButton;
    private ImageButton mShopImageButton;
    private ImageButton mSettingsImageButton;
    private ImageButton mExploreImageButton;
    private AWSAppSyncClient mAWSAppSyncClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            Thread.sleep(4000);
        }
        catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mAWSAppSyncClient = AWSAppSyncClient.builder()
//                .context(getApplicationContext())
//                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
//                .build();
    }

    @Override
    public void onResume() {
        super.onResume();
        requestPermissionForCamera();

        initializeViewVariables();
        setListeners();
    }

    private void initializeViewVariables() {
        mARObjectsImageButton = findViewById(R.id.ar_objects_image_btn);
        mGalleryImageButton = findViewById(R.id.gallery_image_btn);
        mShopImageButton = findViewById(R.id.shop_image_button);
        mSettingsImageButton = findViewById(R.id.settings_image_btn);
        mExploreImageButton = findViewById(R.id.explore_image_btn);
    }

    private void setListeners() {
        mARObjectsImageButton.setOnClickListener(this);
        mGalleryImageButton.setOnClickListener(this);
        mShopImageButton.setOnClickListener(this);
        mSettingsImageButton.setOnClickListener(this);
        mExploreImageButton.setOnClickListener(this);
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
                startFragment(new ARFragment(), true);
                break;
            case R.id.gallery_image_btn:
                startFragment(new GalleryFragment(), true);
                break;
            case R.id.shop_image_button:
                startFragment(new ShopFragment(), true);
                break;
            case R.id.settings_image_btn:
                startFragment(new SettingsFragment(), true);
                break;
            case R.id.explore_image_btn:
                startFragment(new ExploreFragment(), true);
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
