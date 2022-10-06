package com.cos.my3dapp;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.cos.my3dapp.view.ModelActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity222";
    private MainActivity mContext = this;

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
}