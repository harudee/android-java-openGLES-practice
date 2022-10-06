package com.cos.my3dapp.view;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.cos.my3dapp.model.Triangle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

//GLSurfaceView 에서 그려지는 내용을 control
public class ModelRenderer implements GLSurfaceView.Renderer {

    private Triangle mTriangle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //환경설정을 위해서 한번만 호출
        init();

        //검은색배경
        GLES20.glClearColor(0.0f,0.0f,0.0f,1.0f);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //뷰의 도형이 변경되면 호출

        GLES20.glViewport(0,0,width,height);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //뷰를 다시 그릴때마다 호출

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        mTriangle.draw();

    }

    private void init(){
        mTriangle = new Triangle();

    }

    public static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
