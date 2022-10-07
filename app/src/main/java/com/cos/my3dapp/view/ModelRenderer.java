package com.cos.my3dapp.view;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import com.cos.my3dapp.model.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

//GLSurfaceView 에서 그려지는 내용을 control
public class ModelRenderer implements GLSurfaceView.Renderer {

    private Triangle mTriangle;

    private final float[] mTranslateMatrix = new float[16];
    private int screenHeight;
    private int screenWidth;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //환경설정을 위해서 한번만 호출

        //검은색배경
        GLES20.glClearColor(0.0f,0.0f,0.0f,1.0f);

        mTriangle = new Triangle();
        Matrix.setIdentityM(mTranslateMatrix, 0);

    }


    private final float[] vPMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];
    private final float[] viewMatrix = new float[16];
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        this.screenHeight = height;
        this.screenWidth = width;

        //뷰의 도형이 변경되면 호출
        GLES20.glViewport(0,0,width,height);

        //투영 변환 데이터 계산
        float ratio = (float) width/height;
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

    }


    private float[] rotationMatrix = new float[16];

    @Override
    public void onDrawFrame(GL10 gl) {
        //뷰를 다시 그릴때마다 호출

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);


        //카메라 view 정의
        Matrix.setLookAtM(viewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0); //calculate the projection

        //mTriangle.draw(vPMatrix);

        //도형회전
        float[] scratch = new float[16];

        long time = SystemClock.uptimeMillis() % 4000L;
        float angle = 0.090f * ((int) time);
        Matrix.setRotateM(rotationMatrix, 0, angle, 0, 0, -1.0f);
        //Matrix.multiplyMM(scratch, 0, vPMatrix, 0, rotationMatrix, 0);

        Matrix.multiplyMM(scratch, 0, vPMatrix, 0, mTranslateMatrix, 0);

        mTriangle.draw(scratch);



    }

    public static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }


    public void translate(float dx, float dy, float dz){

        Matrix.translateM(mTranslateMatrix, 0,
                dx*2f /screenHeight,
                dy *2f / screenHeight,
                dz *2f / screenHeight);

    }

}
