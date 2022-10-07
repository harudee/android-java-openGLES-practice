package com.cos.my3dapp.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

// 그래픽 뷰 컨테이너
public class ModelSurfaceView extends GLSurfaceView {

    private final ModelRenderer mRenderer;

    public ModelSurfaceView(Context context) {

        super(context);

        setEGLContextClientVersion(2);//openGLES 2.0을 사용한다
        mRenderer = new ModelRenderer();
        setRenderer(mRenderer);

        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY); //그리기 데이터가 변경될때 그림 그리기


    }

   /* public ModelSurfaceView(Context context, AttributeSet attrs){ super(context, attrs); }*/

    private float mPrevX;
    private float mPrevY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()){
            case MotionEvent.ACTION_MOVE:
                float dx = mPrevX - x;
                float dy = mPrevY - y;
                mRenderer.translate(dx, dy, 0f);
        }

        mPrevX = x;
        mPrevY = y;

        return true;
    }
}
