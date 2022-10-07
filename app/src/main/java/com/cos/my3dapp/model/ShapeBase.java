package com.cos.my3dapp.model;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

abstract class ShapeBase {

    public FloatBuffer mVertexBuffer;
    public FloatBuffer mColorBuffer;
    public ByteBuffer mIndexBuffer;
    public int mNumOfIndex;
    public float mRotateAngle;

    public int colorHandle;
    public int positionHandle;
    public int vPMatrixHandle;

    public int mProgram;
    public int vertexCount;

    public void draw(float[] mvpMatrix){};

    protected void setVertices(float[] vertex) {
        // a float is 4 bytes, therefore we multiply the number if
        // vertices with 4.
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertex.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asFloatBuffer();
        mVertexBuffer.put(vertex);
        mVertexBuffer.position(0);
    }

    protected void setIndices(byte[] index) {
        // short is 2 bytes, therefore we multiply the number if
        // vertices with 2.
        mIndexBuffer = ByteBuffer.allocateDirect(index.length);
        mIndexBuffer.put(index);
        mIndexBuffer.position(0);
        mNumOfIndex = index.length;
    }

    protected void setColors(float[] color, int vertexCount) {
        // float has 4 bytes.
        /*
        ByteBuffer cbb = ByteBuffer.allocateDirect(color.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asFloatBuffer();
        mColorBuffer.put(color);
        mColorBuffer.position(0);*/

        colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(colorHandle, 1, color, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        GLES20.glDisableVertexAttribArray(positionHandle);


    }

    protected void setProgram(int vertexShader, int fragmentShader){

        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);

    }

    protected  void setCamera(float[]mvpMatrix, int vertexCount){


        vPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        GLES20.glDisableVertexAttribArray(positionHandle);


    }

}
