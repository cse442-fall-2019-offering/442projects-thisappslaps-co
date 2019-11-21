package com.example.a442projects_thisappslaps_co;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.rendering.ModelRenderable;


public class ModelLoader {

    private final ModelLoaderInterface owner;
    private Context mContext;
    private static final String TAG = "ModelLoader";

    ModelLoader(ModelLoaderInterface owner, Context context) {
        this.owner = owner;
        mContext = context;
    }

    void loadModel(Anchor anchor, Uri uri) {
        if (owner == null) {
            Log.d(TAG, "Activity is null.  Cannot load model.");
            return;
        }
        ModelRenderable.builder()
                .setSource(mContext, uri)
                .build()
                .handle((renderable, throwable) -> {
                    if (owner == null) {
                        return null;
                    } else if (throwable != null) {
                        owner.onException(throwable);
                    } else {
                        owner.addNodeToScene(anchor, renderable);
                    }
                    return null;
                });
    }
}
