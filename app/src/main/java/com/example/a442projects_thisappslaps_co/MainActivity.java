package com.example.a442projects_thisappslaps_co;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
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

import com.example.a442projects_thisappslaps_co.Shop.ShopFragment;
import com.example.a442projects_thisappslaps_co.Settings.SettingsFragment;
import com.example.a442projects_thisappslaps_co.Explore.ExploreFragment;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.a442projects_thisappslaps_co.Gallery.GalleryFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.ux.ArFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int MY_CAMERA_PERMISSIONS;

    private ImageButton mARObjectsImageButton;
    private ImageButton mGalleryImageButton;
    private ImageButton mShopImageButton;
    private ImageButton mSettingsImageButton;
    private ImageButton mExploreImageButton;

    private ArFragment mARFragment;

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
        mARFragment = (ArFragment)
                getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);
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
            case R.id.shutter_image_btn:
                takePhoto();
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


    private String generateFilename() {
        String date =
                new SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.getDefault()).format(new Date());
        return Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES) + File.separator + "Sceneform/" + date + "_screenshot.jpg";
    }

    private void saveBitmapToDisk(Bitmap bitmap, String filename) throws IOException {

        File out = new File(filename);
        if (!out.getParentFile().exists()) {
            out.getParentFile().mkdirs();
        }
        try (FileOutputStream outputStream = new FileOutputStream(filename);
             ByteArrayOutputStream outputData = new ByteArrayOutputStream()) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputData);
            outputData.writeTo(outputStream);
            outputStream.flush();
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
                try {
                    saveBitmapToDisk(bitmap, filename);
                } catch (IOException e) {
                    Toast toast = Toast.makeText(MainActivity.this, e.toString(),
                            Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                        "Photo saved", Snackbar.LENGTH_LONG);
                snackbar.setAction("Open in Photos", v -> {
                    File photoFile = new File(filename);

                    Uri photoURI = FileProvider.getUriForFile(MainActivity.this,
                            MainActivity.this.getPackageName() + ".ar.codelab.name.provider",
                            photoFile);
                    Intent intent = new Intent(Intent.ACTION_VIEW, photoURI);
                    intent.setDataAndType(photoURI, "image/*");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(intent);

                });
                snackbar.show();
            } else {
                Toast toast = Toast.makeText(MainActivity.this,
                        "Failed to copyPixels: " + copyResult, Toast.LENGTH_LONG);
                toast.show();
            }
            handlerThread.quitSafely();
        }, new Handler(handlerThread.getLooper()));
    }
}
