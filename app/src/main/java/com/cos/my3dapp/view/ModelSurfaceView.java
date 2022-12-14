package com.cos.my3dapp.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.cos.my3dapp.RendererType;
import com.cos.my3dapp.model.Triangle1;
import com.cos.my3dapp.model.base.Shape;
import com.cos.my3dapp.model.base.ShapeRenderer;
import com.cos.my3dapp.model.old.Triangle;

// 그래픽 뷰 컨테이너
public class ModelSurfaceView extends GLSurfaceView {

    /*
    private ShapeRenderer<?> renderer;
    private ModelActivity parent;

    private RendererType type;

    public ModelSurfaceView(Context context) {
        super(context);
        renderer = type.getRenderer();

        setEGLContextClientVersion(2);
        setRenderer(renderer);

    }*/

    private final ModelRenderer mRenderer;
    //private final ShapeRenderer<?> mRenderer;
    private RendererType type;

    public ModelSurfaceView(Context context) {

        super(context);

        setEGLContextClientVersion(2);//openGLES 2.0을 사용한다

        //mRenderer = new ShapeRenderer<>(new Triangle1());
        //mRenderer = type.getRenderer();
        mRenderer = new ModelRenderer();

        setRenderer(mRenderer);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

   /* public ModelSurfaceView(Context context, AttributeSet attrs){ super(context, attrs); }*/

    /*
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
    }*/


}
