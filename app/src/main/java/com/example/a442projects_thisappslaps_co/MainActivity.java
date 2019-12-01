package com.example.a442projects_thisappslaps_co;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.MotionEvent;
import android.view.PixelCopy;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.a442projects_thisappslaps_co.Database.ProjectDatabaseHelper;
import com.example.a442projects_thisappslaps_co.Explore.Article;
import com.example.a442projects_thisappslaps_co.Explore.ExploreController;
import com.example.a442projects_thisappslaps_co.ARObjects.ARObjectsFragment;
import com.example.a442projects_thisappslaps_co.ARObjects.AddObjectListener;
import com.example.a442projects_thisappslaps_co.Gallery.Project;
import com.example.a442projects_thisappslaps_co.Gallery.ViewPhotoFragment;
import com.example.a442projects_thisappslaps_co.Settings.SettingsChangedListener;
import com.example.a442projects_thisappslaps_co.Shop.ShopController;
import com.example.a442projects_thisappslaps_co.Shop.ShopFragment;
import com.example.a442projects_thisappslaps_co.Settings.SettingsFragment;
import com.example.a442projects_thisappslaps_co.Explore.ExploreFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a442projects_thisappslaps_co.Gallery.GalleryFragment;
import com.example.a442projects_thisappslaps_co.Shop.ShopItem;
import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Trackable;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.collision.Ray;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.ArrayList;
import java.util.List;
import com.google.ar.sceneform.ArSceneView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.GalleryTable.Cols.TIMESTAMP;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.GalleryTable.Cols.URI;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.GalleryTable.NAME;
import static com.example.a442projects_thisappslaps_co.Settings.SettingsFragment.PLANE_RENDERER;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        ModelLoaderInterface,
        AddObjectListener,
        SettingsChangedListener {

    public static final String PREFS_NAME = "SharedPrefs";
    private static int MY_CAMERA_PERMISSIONS;

    private PointerDrawable pointer = new PointerDrawable();
    private ModelLoader modelLoader;

    private boolean isTracking;
    private boolean isHitting;

    private ImageButton mARObjectsImageButton;
    private ImageButton mGalleryImageButton;
    private ImageButton mShopImageButton;
    private ImageButton mSettingsImageButton;
    private ImageButton mExploreImageButton;
    private ImageButton mShutterImageButton;
    private ImageButton mDeleteImageButton;

    private ArFragment mARFragment;

    private static SQLiteDatabase sSQLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);

        if (sharedPreferences.getBoolean("first_time", true)) {
            ExploreController exploreController = ExploreController.getInstance(this);
            ShopController shopController = ShopController.getInstance(this);

            for (Article article : exploreController.prepareArticles()) {
                exploreController.addArticleToDatabase(article);
            }

            for (ShopItem shopItem : shopController.createDummyList()) {
                shopController.addToDatabase(shopItem);
            }

            sharedPreferences.edit().putBoolean("first_time", false).apply();
        }

        try{
            Thread.sleep(4000);
        }
        catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sSQLiteDatabase = new ProjectDatabaseHelper(getApplicationContext()).getWritableDatabase();

        initializeViewVariables();
        setListeners();

        mARFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
            mARFragment.onUpdate(frameTime);
            onUpdate();
        });

        mARFragment.getArSceneView().getPlaneRenderer().setVisible(sharedPreferences.getBoolean(PLANE_RENDERER, false));

        modelLoader = new ModelLoader(this, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        requestPermissionForCamera();
    }

    private void initializeViewVariables() {
        mARObjectsImageButton = findViewById(R.id.ar_objects_image_btn);
        mGalleryImageButton = findViewById(R.id.gallery_image_btn);
        mShopImageButton = findViewById(R.id.shop_image_button);
        mSettingsImageButton = findViewById(R.id.settings_image_btn);
        mExploreImageButton = findViewById(R.id.explore_image_btn);
        mShutterImageButton = findViewById(R.id.shutter_image_btn);
        mDeleteImageButton = findViewById(R.id.delete_image_btn);
        mARFragment = (ArFragment)
                getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);
    }
    public Scene.OnPeekTouchListener touchListener;
    private void setListeners() {
        mARObjectsImageButton.setOnClickListener(this);
        mGalleryImageButton.setOnClickListener(this);
        mShopImageButton.setOnClickListener(this);
        mSettingsImageButton.setOnClickListener(this);
        mExploreImageButton.setOnClickListener(this);
        mShutterImageButton.setOnClickListener(this);
        mDeleteImageButton.setOnClickListener(this);
//        for deletion of objects:
        mDeleteImageButton.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(view.getContext(),"Tap on objects to delete", Toast.LENGTH_SHORT).show();
                mDeleteImageButton.setImageResource(R.drawable.ic_delete_red);
                mARFragment.getArSceneView().getScene().addOnPeekTouchListener(touchListener = new Scene.OnPeekTouchListener(){
                    @Override
                    public void onPeekTouch(HitTestResult hitTestResult, MotionEvent motionEvent) {
                        if(hitTestResult.getNode()!= null) {
                            Node hitNode = hitTestResult.getNode();
                            hitNode.setParent(null);
                        }
                    }
                });
                return true;
            }
        });
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
                startFragment(new ARObjectsFragment(this), true);
                break;
            case R.id.gallery_image_btn:
                startFragment(new GalleryFragment(), true);
                break;
            case R.id.shop_image_button:
                startFragment(new ShopFragment(), true);
                break;
            case R.id.settings_image_btn:
                startFragment(new SettingsFragment(this), true);
                break;
            case R.id.explore_image_btn:
                startFragment(new ExploreFragment(), true);
                break;
            case R.id.shutter_image_btn:
                takePhoto();
                break;
            case R.id.delete_image_btn:
                Toast.makeText(view.getContext(),"Hold to delete", Toast.LENGTH_SHORT).show();
                mDeleteImageButton.setImageResource(R.drawable.ic_delete_white);
                mARFragment.getArSceneView().getScene().removeOnPeekTouchListener(touchListener);
                break;
            default:
                break;
        }
    }

    private void startFragment(Fragment fragment, boolean shouldAddToBackStack) {
        startFragment(fragment, getSupportFragmentManager(), shouldAddToBackStack);
    }

    public static void startFragment(
            Fragment fragment,
            FragmentManager fragmentManager,
            boolean shouldAddToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if (shouldAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
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

    // Using ARCore's camera state and returns true if tracking state has changed since last call
    private boolean updateTracking() {
        Frame frame = mARFragment.getArSceneView().getArFrame();
        boolean wasTracking = isTracking;
        isTracking = frame != null &&
                frame.getCamera().getTrackingState() == TrackingState.TRACKING;
        return isTracking != wasTracking;
    }

    // Looks for a hit
    private boolean updateHitTest() {
        Frame frame = mARFragment.getArSceneView().getArFrame();
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
        View vw = findViewById(android.R.id.content);
        return new android.graphics.Point(vw.getWidth()/2, vw.getHeight()/2);
    }

    // Uses the hit test to place where in the 3D world the object should be placed.
    @Override
    public void addObject(Uri model) {
        Frame frame = mARFragment.getArSceneView().getArFrame();
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

    /*
      Builds AnchorNode and TransformableNode and attaches them to the ArSceneView's scene object.
      Anchor nodes are positioned based on the pose of an ARCore Anchor. Basically the object stays in place.
      Transform Node allows the user to interact with the object.
    */
    @Override
    public void addNodeToScene(Anchor anchor, ModelRenderable renderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(mARFragment.getTransformationSystem());
        node.setRenderable(renderable);
        node.setParent(anchorNode);
        mARFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
    }

    @Override
    public void onException(Throwable throwable){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(throwable.getMessage())
                .setTitle("GARDEN error!");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String generateFilename() {
        String date =
                new SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.getDefault()).format(new Date());
        return Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES) + File.separator + "Sceneform/" + date + "_screenshot.jpg";
    }

    public static void saveBitmapToDisk(Bitmap bitmap, Project project) throws IOException {

        File out = new File(project.getUri());
        if (!out.getParentFile().exists()) {
            out.getParentFile().mkdirs();
        }
        System.out.println(project.getUri());
        try (FileOutputStream outputStream = new FileOutputStream(project.getUri());
             ByteArrayOutputStream outputData = new ByteArrayOutputStream()) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputData);
            outputData.writeTo(outputStream);
            outputStream.flush();
            addProjectToDatabase(project);
        } catch (IOException ex) {
            throw new IOException("Failed to save bitmap to disk", ex);
        }
    }

    private void takePhoto() {
        final String filename = generateFilename();
        ArSceneView view = mARFragment.getArSceneView();

        // Create a bitmap the size of the scene view.
        final Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);

        // Create a handler thread to offload the processing of the image.
        final HandlerThread handlerThread = new HandlerThread("PixelCopier");
        handlerThread.start();
        // Make the request to copy.
        PixelCopy.request(view, bitmap, (copyResult) -> {
            if (copyResult == PixelCopy.SUCCESS) {
                startFragment(new ViewPhotoFragment(
                        new Project(filename, new Date().getTime()), bitmap, null, false), true);
            } else {
                Toast toast = Toast.makeText(MainActivity.this,
                        "Failed to copyPixels: " + copyResult, Toast.LENGTH_LONG);
                toast.show();
            }
            handlerThread.quitSafely();
        }, new Handler(handlerThread.getLooper()));
    }

    private static void addProjectToDatabase(Project project) {
        sSQLiteDatabase.insert(NAME, null, getProjectContentValues(project));
    }

    private static ContentValues getProjectContentValues(Project project) {
        ContentValues values = new ContentValues();
        values.put(URI, project.getUri());
        values.put(TIMESTAMP, project.getTimestamp());
        return values;
    }

    @Override
    public void enablePlaneRendering(boolean enabled) {
        mARFragment.getArSceneView().getPlaneRenderer().setVisible(enabled);
    }
}
