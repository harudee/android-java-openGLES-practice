package com.cos.my3dapp.utils;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import com.cos.my3dapp.view.ModelRenderer;


public class GlUtil {

    private static final String TAG = "GlUtil";


    public static final float[] IDENTITY_MATRIX;
    static {
        IDENTITY_MATRIX = new float[16];
        Matrix.setIdentityM(IDENTITY_MATRIX, 0);
    }

    public static final int SIZE_OF_FLOAT = 4;


    private GlUtil(){}

    //setProgram
    public static int createProgram(String vertexSource, String fragmentSource){

        int vertexShader = getShader(GLES20.GL_VERTEX_SHADER, vertexSource);
        if(vertexShader == 0 ) return 0;

        int fragmentShader = getShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
        if(fragmentShader == 0) return 0;

        int program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);


        return program;
    }


    //loadShader
    public static int getShader(int shaderType, String source){

        int shader = GLES20.glCreateShader(shaderType);

        GLES20.glShaderSource(shader, source);
        GLES20.glCompileShader(shader);

        return shader;
    }

}
