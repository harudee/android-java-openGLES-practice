package com.cos.my3dapp.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

// 그래픽 뷰 컨테이너
public class ModelSurfaceView extends GLSurfaceView {

    private final ModelRenderer renderer;

    public ModelSurfaceView(Context context) {

        super(context);

        setEGLContextClientVersion(2);//openGLES 2.0을 사용한다
        renderer = new ModelRenderer();

        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY); //그리기 데이터가 변경될때 그림 그리기
        setRenderer(renderer);

    }

   /* public ModelSurfaceView(Context context, AttributeSet attrs){ super(context, attrs); }*/



}
