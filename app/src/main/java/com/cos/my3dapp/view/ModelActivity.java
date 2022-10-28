package com.cos.my3dapp.view;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.cos.my3dapp.RendererType;

//그래픽뷰를 올리는 Activity
public class ModelActivity extends Activity {

    private static final String TAG = "ModelActivity";
    private ModelActivity mContext= this;
    private GLSurfaceView glView;
    private RendererType type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setTitle(type.getName());
        glView = new ModelSurfaceView(this);
        setContentView(glView);

    }

    @Override
    protected void onPause() {
        super.onPause();
        glView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glView.onResume();
    }
}
