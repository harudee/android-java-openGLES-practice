package com.cos.my3dapp.model;

import android.graphics.drawable.shapes.Shape;
import android.opengl.GLES20;

import com.cos.my3dapp.view.ModelRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

public class Triangle extends ShapeBase {
    //기본도형 정의_삼각형

    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
            "void main() {" +
            "  gl_Position = uMVPMatrix * vPosition;" +
            "}";
    private final String fragmentShaderCode = "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}";


    static final int COORDS_PER_VERTEX = 3; // vertex : 꼭지점
    static float triangleVertices[] = { //도형 꼭지점
            0.0f, 0.577350269f, 0.0f,     // top
            -0.5f, -0.288675134f, 0.0f,   // bottom left
            0.5f, -0.288675134f, 0.0f     // bottom right
    };

    //도형 색상
    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

    public Triangle(){

        setVertices(triangleVertices);

        int vertexShader = ModelRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = ModelRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        setProgram(vertexShader, fragmentShader);
    }

    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4byte per vertex

    @Override
    public void draw(float[] mvpMatrix) {

        vertexCount = triangleVertices.length / COORDS_PER_VERTEX;


        GLES20.glUseProgram(mProgram);

        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, mVertexBuffer);


        //삼각형 색 채우기
        setColors(color, vertexCount);

        //카메라 변환 적용
        setCamera(mvpMatrix,vertexCount);

    }

}
