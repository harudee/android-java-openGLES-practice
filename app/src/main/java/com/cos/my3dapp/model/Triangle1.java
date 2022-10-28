package com.cos.my3dapp.model;

import com.cos.my3dapp.model.base.ColorShape;

public class Triangle1 extends ColorShape {

    @Override
    protected String getColorHandleName() {
        return "vColorHandle";
    }

    @Override
    protected float[] getColors() {
        return new float[]{
                0.63671875f, 0.76953125f, 0.22265625f, 1.0f
        };
    }

    @Override
    protected String getVertexShader() {
        return "attribute vec4 vPosition;" +
                "void main() {" +
                "  gl_Position = vPosition;" +
                "}";
    }

    @Override
    protected String getFragmentShader() {
        return "precision mediump float;" +
                "uniform vec4 vColor;" +
                "void main() {" +
                "  gl_FragColor = vColor;" +
                "}";
    }

    @Override
    protected float[] getVertices() {
        return new float[]{
                0.0f,  0.622008459f, 0.0f, // top
                -0.5f, -0.311004243f, 0.0f, // bottom left
                0.5f, -0.311004243f, 0.0f  // bottom right
        };
    }

    @Override
    protected int getCoordsPerVertex() {
        return 3;
    }

    @Override
    protected byte[] getIndices() {
        return new byte[]{
                0, 1, 2
        };
    }

    @Override
    protected String getPositionHandleName() {
        return "vPosition";
    }

    @Override
    protected String getMVPMatHandleName() {
        return "vMVPMatrix";
    }


}
