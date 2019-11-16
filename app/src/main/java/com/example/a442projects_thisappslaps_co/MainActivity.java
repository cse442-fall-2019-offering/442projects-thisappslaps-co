package com.example.a442projects_thisappslaps_co;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.a442projects_thisappslaps_co.ARObjects.ARFragment;

import com.example.a442projects_thisappslaps_co.Shop.ShopFragment;
import com.example.a442projects_thisappslaps_co.Settings.SettingsFragment;
import com.example.a442projects_thisappslaps_co.Explore.ExploreFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.a442projects_thisappslaps_co.Gallery.GalleryFragment;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int MY_CAMERA_PERMISSIONS;

    ARFragment arFragment;
    private ModelRenderable ar1Renderable,
                            ar2Renderable,
                            ar3Renderable,
                            ar4Renderable,
                            ar5Renderable,
                            ar6Renderable,
                            ar7Renderable,
                            ar8Renderable,
                            ar9Renderable,
                            ar10Renderable,
                            ar11Renderable,
                            ar12Renderable,
                            ar13Renderable,
                            ar14Renderable,
                            ar15Renderable,
                            ar16Renderable,
                            ar17Renderable,
                            ar18Renderable;



    ViewRenderable name_plant;
    View arrayView[];
    int selected = 1;

    private ImageButton mARObjectsImageButton;
    private ImageButton mGalleryImageButton;
    private ImageButton mShopImageButton;
    private ImageButton mSettingsImageButton;
    private ImageButton mExploreImageButton;


    private static final String TAG = "MainActivity";

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
//        arFragment = (ARFragment)getSupportFragmentManager().findFragmentById(R.id.sceneform_ar_scene_view);
        ModelRenderable.builder()
                .setSource(this, R.raw.ar17)
                .build()
                .thenAccept( renderable -> ar8Renderable = renderable)
                .exceptionally(
                        throwable -> {
                            Log.e(TAG, "Unable to load Renderable.", throwable);
                            return null;
                        }
                );





//        setUpModel();

        //Need to find tapListener for AR fragment
//        arFragment.setOnTapArPlaneListener(new BaseArFragment().OnTapArPlaneListener(){
//
//            @Override
//            public void onTapPlane(HitResult hitResult, Plane plnae, MotionEvent motionEvent){
//
//
//                    Anchor anchor = hitResult.createAnchor();
//                    AnchorNode anchorNode = new AnchorNode(anchor);
//                    anchorNode.setParent(arFragment.getArSceneView().getScene());
//
//                    createModel(anchorNode, selected);
//
//            }
//        });
    }

    /*
        Load up model so that it can be used for AR placement
     */
//    private void setUpModel(){
//        ModelRenderable.builder()
//                .setSource(this, R.raw.ar02)
//                .build().thenAccept(modelRenderable -> bambooRenderable = modelRenderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//                            return null;
//                        }
//                );
//
//        ModelRenderable.builder()
//                .setSource(this, R.raw.ar04)
//                .build().thenAccept(modelRenderable -> buddhaRenderable = modelRenderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//                            return null;
//                        }
//                );
//
//        ModelRenderable.builder()
//                .setSource(this, R.raw.ar08)
//                .build().thenAccept(modelRenderable -> frogRenderable = modelRenderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//                            return null;
//                        }
//                );
//
//        ModelRenderable.builder()
//                .setSource(this, R.raw.ar09)
//                .build().thenAccept(modelRenderable -> grassRenderable = modelRenderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//                            return null;
//                        }
//                );
//
//        ModelRenderable.builder()
//                .setSource(this, R.raw.ar11)
//                .build().thenAccept(modelRenderable -> lupineRenderable = modelRenderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//                            return null;
//                        }
//                );
//
//        ModelRenderable.builder()
//                .setSource(this, R.raw.ar17)
//                .build().thenAccept(modelRenderable -> sunflowerRenderable = modelRenderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//                            return null;
//                        }
//                );
//
//        ModelRenderable.builder()
//                .setSource(this, R.raw.ar18)
//                .build().thenAccept(modelRenderable -> treeRenderable = modelRenderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//                            return null;
//                        }
//                );
//
//    }

    //Add more on for the other models TODO
//    private void createModel(AnchorNode anchorNode, int selected){
////        if(selected == 1){
////            TransformableNode ar02 = new TransformableNode(arFragment.getTransformationSystem());
////            ar02.setParent(anchorNode);
////            ar02.setRenderable(bambooRenderable);
////            ar02.select();
////        }
////        else if(selected == 2){
////            TransformableNode ar04 = new TransformableNode(arFragment.getTransformationSystem());
////            ar04.setParent(anchorNode);
////            ar04.setRenderable(bambooRenderable);
////            ar04.select();
////        }
////        else if(selected == 3){
////            TransformableNode ar08 = new TransformableNode(arFragment.getTransformationSystem());
////            ar08.setParent(anchorNode);
////            ar08.setRenderable(bambooRenderable);
////            ar08.select();
////        }
////        else if(selected == 4){
////            TransformableNode ar09 = new TransformableNode(arFragment.getTransformationSystem());
////            ar09.setParent(anchorNode);
////            ar09.setRenderable(bambooRenderable);
////            ar09.select();
////        }
////        else if(selected == 5){
////            TransformableNode ar11 = new TransformableNode(arFragment.getTransformationSystem());
////            ar11.setParent(anchorNode);
////            ar11.setRenderable(bambooRenderable);
////            ar11.select();
////        }
////        else if(selected == 6){
////            TransformableNode ar17 = new TransformableNode(arFragment.getTransformationSystem());
////            ar17.setParent(anchorNode);
////            ar17.setRenderable(bambooRenderable);
////            ar17.select();
////        }
////        else if(selected == 7){
////            TransformableNode ar18 = new TransformableNode(arFragment.getTransformationSystem());
////            ar18.setParent(anchorNode);
////            ar18.setRenderable(bambooRenderable);
////            ar18.select();
////        }
//
//
//    }

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
