package com.cos.my3dapp.model.old;

import android.opengl.GLES20;

import com.cos.my3dapp.utils.GlUtil;
import com.cos.my3dapp.view.ModelRenderer;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class Square extends ShapeBase {

    private FloatBuffer vertexBuffer;
    private ByteBuffer indexBuffer;

    //#Shader Code
    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    //"attribute vec4 aColor;" +  // 각 vertex에 있을 색상(rgba)값 버퍼
                    //"varying vec4 ourColor;" +  // fragement shader로 넘어갈 보간 값
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    //색 또는 텍스처 적용
    private final String fragmentShaderCode = "precision mediump float;" +
            "uniform vec4 vColor;" +
            //"varying vec4 ourColor;"+
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}";


    static final int COORDS_PER_VERTEX = 4;
    private final int vertexStride_square = COORDS_PER_VERTEX * 4; // 4byte per vertex


    static float squareCoords[] = {
            -0.5f,  0.5f, 0.0f,   // top left
            -0.5f, -0.5f, 0.0f,   // bottom left
             0.5f, -0.5f, 0.0f,   // bottom right
             0.5f,  0.5f, 0.0f,}; // top right

    private short drawOrder[] = {0, 1, 2, //triangle1
                                 0, 2, 3 }; //triangle2


    float color[] = {
            1f, 0, 0, 1f,
            0, 1f, 0, 1f,
            0, 0, 1f, 1f };


    public Square(){

        int vertexShader = GlUtil.getShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = GlUtil.getShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        setVertices(squareCoords);
        setOrders(drawOrder);
        setProgram(vertexShader, fragmentShader);

    }

    @Override
    public void draw(float[] mvpMatrix) {
        super.draw(mvpMatrix);

        vertexCount = squareCoords.length / COORDS_PER_VERTEX;

        GLES20.glUseProgram(mProgram);


        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride_square, mVertexBuffer);

        //setColors 부분
        colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(colorHandle, 1, color, 0);
        GLES20.glDrawElements( //drawArrays 대신
                GLES20.GL_TRIANGLES, drawOrder.length,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        GLES20.glDisableVertexAttribArray(positionHandle);



    }
}
