package com.example.a442projects_thisappslaps_co;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.PixelCopy;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.a442projects_thisappslaps_co.ARObjects.ARFragment;

import com.example.a442projects_thisappslaps_co.Database.ProjectDatabaseHelper;
import com.example.a442projects_thisappslaps_co.Gallery.Project;
import com.example.a442projects_thisappslaps_co.Gallery.ViewPhotoFragment;
import com.example.a442projects_thisappslaps_co.Shop.ShopFragment;
import com.example.a442projects_thisappslaps_co.Settings.SettingsFragment;
import com.example.a442projects_thisappslaps_co.Explore.ExploreFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a442projects_thisappslaps_co.Gallery.GalleryFragment;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.ux.ArFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.GalleryTable.Cols.TIMESTAMP;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.GalleryTable.Cols.URI;
import static com.example.a442projects_thisappslaps_co.Database.DatabaseSchema.GalleryTable.NAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int MY_CAMERA_PERMISSIONS;

    private ImageButton mARObjectsImageButton;
    private ImageButton mGalleryImageButton;
    private ImageButton mShopImageButton;
    private ImageButton mSettingsImageButton;
    private ImageButton mExploreImageButton;
    private ImageButton mShutterImageButton;

    private ArFragment mARFragment;

    private static SQLiteDatabase sSQLiteDatabase;

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
        sSQLiteDatabase = new ProjectDatabaseHelper(getApplicationContext()).getWritableDatabase();
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
        mShutterImageButton = findViewById(R.id.shutter_image_btn);
        mARFragment = (ArFragment)
                getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);
    }

    private void setListeners() {
        mARObjectsImageButton.setOnClickListener(this);
        mGalleryImageButton.setOnClickListener(this);
        mShopImageButton.setOnClickListener(this);
        mSettingsImageButton.setOnClickListener(this);
        mExploreImageButton.setOnClickListener(this);
        mShutterImageButton.setOnClickListener(this);
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
            case R.id.shutter_image_btn:
                takePhoto();
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
                        new Project(filename, new Date().getTime()), bitmap, false), true);
            } else {
                Toast toast = Toast.makeText(MainActivity.this,
                        "Failed to copyPixels: " + copyResult, Toast.LENGTH_LONG);
                toast.show();
            }
            handlerThread.quitSafely();
        }, new Handler(handlerThread.getLooper()));
    }

    private static void addProjectToDatabase(Project project) {
        sSQLiteDatabase.insert(NAME, null, getContentValues(project));
    }

    private static ContentValues getContentValues(Project project) {
        ContentValues values = new ContentValues();
        values.put(URI, project.getUri());
        values.put(TIMESTAMP, project.getTimestamp());
        return values;
    }
}
