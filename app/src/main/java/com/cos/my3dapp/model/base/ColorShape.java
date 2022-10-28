package com.cos.my3dapp.model.base;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

//색상 설정
public abstract class ColorShape extends Shape {

    protected abstract String getColorHandleName();
    protected abstract float[] getColors();
    protected int colorHandle;

    protected FloatBuffer colorBuffer;


    @Override
    public void onSurfaceChanged(int width, int height) {
        super.onSurfaceChanged(width, height);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(getColors().length * SIZE_OF_FLOAT);
        byteBuffer.order(ByteOrder.nativeOrder());

        colorHandle = GLES20.glGetAttribLocation(program, getColorHandleName());
        colorBuffer = byteBuffer.asFloatBuffer();
        colorBuffer.put(getColors());
        colorBuffer.position(0);

    }

    @Override
    protected void onDrawFrame(float[] matrix) {

        //onDraw
        GLES20.glUseProgram(program);
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, getCoordsPerVertex(), GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

        //setColors
        GLES20.glEnableVertexAttribArray(colorHandle);
        GLES20.glVertexAttribPointer(colorHandle, SIZE_OF_FLOAT, GLES20.GL_FLOAT, false, colorStride, colorBuffer);


        //setCamera
        Matrix.multiplyMM(mvpMatrix, 0, matrix, 0, modelMatrix,0);
        GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, mvpMatrix, 0);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, getIndices().length, GLES20.GL_UNSIGNED_BYTE, indexBuffer);


    }
}
