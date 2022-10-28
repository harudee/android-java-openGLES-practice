package com.cos.my3dapp.view;

import android.opengl.GLSurfaceView;

public class ShapeBindings {
    public static void setRenderer(GLSurfaceView view, GLSurfaceView.Renderer renderer){
        view.setEGLContextClientVersion(2);
        view.setRenderer(renderer);
    }
}
