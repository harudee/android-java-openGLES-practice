package com.cos.my3dapp;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.SurfaceView;

import com.cos.my3dapp.view.ModelActivity;
import com.cos.my3dapp.view.ModelSurfaceView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity222";
    private MainActivity mContext = this;
    private ModelSurfaceView glSurfaceView;
    private GLSurfaceView glView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init(){
        MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), ModelActivity.class));
        MainActivity.this.finish();
    }


    private void initView(){
        glSurfaceView = findViewById(R.id.gl_surface_view);
    }


}