package com.example.a442projects_thisappslaps_co;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.rendering.ModelRenderable;

public interface ModelLoaderInterface {
    void onException(Throwable throwable);
    void addNodeToScene(Anchor anchor, ModelRenderable renderable);
}
