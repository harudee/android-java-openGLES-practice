package com.cos.my3dapp.model.old;


//#1. Shader 소스
//#2. vertex 데이터
//#3. 버퍼와 shader 연결
//#4. 렌더링
public class Pyramid extends ShapeBase {

    //사각뿔
    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
            "attribute vec4 aColor;" +  // 각 vertex에 있을 색상(rgba)값 버퍼
            "varying vec4 ourColor;" +  // fragement shader로 넘어갈 보간 값
            "void main() {" +
            "    gl_Position = uMVPMatrix * vPosition;" +
            "    ourColor = aColor;" +
            "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "varying vec4 ourColor;" +  // 넘겨받은 보간 값
                    "void main() {" +
                    "    gl_FragColor = ourColor;" +
                    "}";


    static final int COORDS_PER_VERTEX = 5;
    float[] pyramidVertices = {
            //front
            0.0f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.5f,
            0.5f,-0.5f, 0.5f,

            //right
            0.0f, 0.5f, 0.0f,
            0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, -0.5f,

            //back
            0.0f, 0.5f, 0.0f,
            0.5f, -0.5f, -0.5f,
            -0.5f, -0.5f, -0.5f,

            //left
            0.0f, 0.5f, 0.0f,
            -0.5f, -0.5f, -0.5f,
            -0.5f, -0.5f, 0.5f


    };

    final int COLORS_PER_VERTEX = 5;
    float colors[] = {
            1f, 1f, 1f, 1f, // 점 A의 색상 rgba(1, 1, 1, 1)
            1f, 0f, 0f, 1f, // 점 B
            0f, 1f, 0f, 1f, // 점 C
            0f, 0f, 1f, 1f, // 점 D

    };

    final short[] drawOrder= {
            0, 1, 2,
            0, 2, 3,
            0, 3, 1,
            1, 3, 2
    };

    public Pyramid(){

        setVertices(pyramidVertices);


    }



    @Override
    public void draw(float[] mvpMatrix) {
        super.draw(mvpMatrix);


    }
}
