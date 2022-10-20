package com.cos.my3dapp.model.base;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ShapeRenderer<T extends Shape> implements GLSurfaceView.Renderer {

    private T item;
    private final float[] projectionMatrix;
    private final float[] viewMatrix;
    private final float[] vpMatrix;


    public ShapeRenderer(T item){
        this.item = item;

        projectionMatrix = new float[16];
        viewMatrix = new float[16];
        vpMatrix = new float[16];

        //초기화
        Matrix.setIdentityM(projectionMatrix, 0);
        Matrix.setIdentityM(viewMatrix, 0);
        Matrix.setIdentityM(vpMatrix, 0);

    }




    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //surface가 생성될 때
        item.onSurfaceCreated();

    }

    /*
        setLookAtM : 바로보는 시점 (카메라 위치)
        frustumM : 비쳐지는 영역 (카메라 렌즈 )
    */

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //surface size가 변경되었을 때

        GLES20.glViewport(0,0,width,height);



        //투영 정의
        float ratio = (float) width/height;
        Matrix.frustumM(projectionMatrix,0,-ratio,ratio,-1,1,3,7);


        //카메라 view 정의
        Matrix.setLookAtM(viewMatrix, 0,0, 0, -3, 0f, 0f, 0f,0f, 1.0f, 0.0f);
        Matrix.multiplyMM(vpMatrix, 0, projectionMatrix, 0, viewMatrix, 0);


        item.onSurfaceChanged(width, height);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //rendering 수행
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        item.onDrawFrame(vpMatrix);

    }



}
