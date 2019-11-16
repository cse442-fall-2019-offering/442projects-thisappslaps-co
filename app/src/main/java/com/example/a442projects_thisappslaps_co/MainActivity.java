package com.example.a442projects_thisappslaps_co;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.a442projects_thisappslaps_co.ARObjects.ARObjectsFragment;
import com.example.a442projects_thisappslaps_co.Shop.ShopFragment;
import com.example.a442projects_thisappslaps_co.Settings.SettingsFragment;
import com.example.a442projects_thisappslaps_co.Explore.ExploreFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.a442projects_thisappslaps_co.Gallery.GalleryFragment;
import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Trackable;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.lang.ref.WeakReference;
import java.net.URI;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int MY_CAMERA_PERMISSIONS;

    private ArFragment fragment;
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
                            ar18Renderable,
                            ar19Renderable;

    private PointerDrawable pointer = new PointerDrawable();
    private ModelLoader modelLoader;

//    Check to see if ARCore is in tracking or hitting states
    private boolean isTracking;
    private boolean isHitting;

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

//        Initialize arObjectsFragment
        fragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);

//        Listener to  make ARCore calls and update status of pointer
        fragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
            fragment.onUpdate(frameTime);
            onUpdate();
        });

        modelLoader = new ModelLoader(new WeakReference<>(this));
        initializeGallery();
    }


    private void onUpdate() {
//        enable pointer if the phone is pointing towards a plane

        boolean trackingChanged = updateTracking();
        View contentView = findViewById(android.R.id.content);
        if(trackingChanged) {
            if (isTracking) {
                contentView.getOverlay().add(pointer);
            }
            else {
                contentView.getOverlay().remove(pointer);
            }
            contentView.invalidate();
        }
        if (isTracking) {
            boolean hitTestChanged = updateHitTest();
            if (hitTestChanged) {
                pointer.setEnabled(isHitting);
                contentView.invalidate();
            }
        }
    }

    private boolean updateTracking() {
//        Using ARCore's camera state and returns true if tracking state has changed since last call

        Frame frame = fragment.getArSceneView().getArFrame();
        boolean wasTracking = isTracking;
        isTracking = frame != null &&
                frame.getCamera().getTrackingState() == TrackingState.TRACKING;
        return isTracking != wasTracking;
    }

    private boolean updateHitTest() {
//        Looks for a hit

        Frame frame = fragment.getArSceneView().getArFrame();
        android.graphics.Point pt = getScreenCenter();
        List<HitResult> hits;
        boolean wasHitting = isHitting;
        isHitting = false;
        if (frame != null) {
            hits = frame.hitTest(pt.x, pt.y);
            for (HitResult hit : hits) {
                Trackable trackable = hit.getTrackable();
                if (trackable instanceof Plane &&
                        ((Plane) trackable).isPoseInPolygon(hit.getHitPose())) {
                    isHitting = true;
                    break;
                }
            }
        }
        return wasHitting != isHitting;
    }

    private android.graphics.Point getScreenCenter() {
//        grab center of the screen

        View vw = findViewById(android.R.id.content);
        return new android.graphics.Point(vw.getWidth()/2, vw.getHeight()/2);
    }

    @Override
    public void onResume() {
        super.onResume();
        requestPermissionForCamera();

        initializeViewVariables();
        setListeners();
    }

    private void initializeViewVariables() {
//        mARObjectsImageButton = findViewById(R.id.ar_objects_image_btn);
//        mGalleryImageButton = findViewById(R.id.gallery_image_btn);
        mShopImageButton = findViewById(R.id.shop_image_button);
        mSettingsImageButton = findViewById(R.id.settings_image_btn);
//        mExploreImageButton = findViewById(R.id.explore_image_btn);
    }

    private void setListeners() {
//        mARObjectsImageButton.setOnClickListener(this);
//        mGalleryImageButton.setOnClickListener(this);
        mShopImageButton.setOnClickListener(this);
        mSettingsImageButton.setOnClickListener(this);
//        mExploreImageButton.setOnClickListener(this);
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
//            case R.id.ar_objects_image_btn:
//                startFragment(new ARObjectsFragment(), true);
//                break;
//            case R.id.gallery_image_btn:
//                startFragment(new GalleryFragment(), true);
//                break;
            case R.id.shop_image_button:
                startFragment(new ShopFragment(), true);
                break;
            case R.id.settings_image_btn:
                startFragment(new SettingsFragment(), true);
                break;
//            case R.id.explore_image_btn:
//                startFragment(new ExploreFragment(), true);
//                break;
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

    private void initializeGallery() {
//        only being used to load the gallery liner layout thing at the bottom
//        you probably only need to use the set on click function call with the uri parse thing

        LinearLayout gallery = findViewById(R.id.gallery_layout);

        ImageView ar1 = new ImageView(this);
        ar1.setImageResource(R.drawable.ar01);
        ar1.setContentDescription("ar object 1");
        ar1.setOnClickListener(view -> {addObject(Uri.parse("ar01.sfb"));});
        gallery.addView(ar1);

        ImageView ar2 = new ImageView(this);
        ar2.setImageResource(R.drawable.ar01);
        ar2.setContentDescription("ar object 1");
        ar2.setOnClickListener(view -> {addObject(Uri.parse("ar02.sfb"));});
        gallery.addView(ar2);

        ImageView ar3 = new ImageView(this);
        ar3.setImageResource(R.drawable.ar01);
        ar3.setContentDescription("ar object 1");
        ar3.setOnClickListener(view -> {addObject(Uri.parse("ar03.sfb"));});
        gallery.addView(ar3);

        ImageView ar4 = new ImageView(this);
        ar4.setImageResource(R.drawable.ar01);
        ar4.setContentDescription("ar object 1");
        ar4.setOnClickListener(view -> {addObject(Uri.parse("ar04.sfb"));});
        gallery.addView(ar4);
    }

    private void addObject(Uri model) {
//      Uses the hit test to place where in the 3D world the object should be placed.

        Frame frame = fragment.getArSceneView().getArFrame();
        android.graphics.Point pt = getScreenCenter();
        List<HitResult> hits;
        if (frame != null) {
            hits = frame.hitTest(pt.x, pt.y);
            for (HitResult hit : hits) {
                Trackable trackable = hit.getTrackable();
                if (trackable instanceof Plane &&
                        ((Plane) trackable).isPoseInPolygon(hit.getHitPose())) {
                    modelLoader.loadModel(hit.createAnchor(), model);
                    break;
                }
            }
        }
    }

    public void addNodeToScene(Anchor anchor, ModelRenderable renderable) {
//        Builds AnchorNode and TransformableNode and attaches them to the ArSceneView's scene object.
//


//      Anchor nodes are positioned based on the pose of an ARCore Anchor. Basically the object stays in place.
//      Transform Node allows the user to interact with the object.
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(fragment.getTransformationSystem());
        node.setRenderable(renderable);
        node.setParent(anchorNode);
        fragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
    }

    public void onException(Throwable throwable){
//      Helps with debugging
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(throwable.getMessage())
                .setTitle("GARDEN error!");
        AlertDialog dialog = builder.create();
        dialog.show();
        return;
    }

}