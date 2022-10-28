package com.cos.my3dapp;

import androidx.annotation.StringRes;

import com.cos.my3dapp.model.Triangle1;
import com.cos.my3dapp.model.base.ShapeRenderer;
import com.cos.my3dapp.model.old.Square;

public enum RendererType {//enum 클래스

    Triangle(R.string.triangle){
        @Override
        public ShapeRenderer<Triangle1> getRenderer(){
            return new ShapeRenderer<>(new Triangle1());
        }

    };

    @StringRes
    private int name;

    public abstract ShapeRenderer<?> getRenderer();

    RendererType(@StringRes int name) {
        this.name = name;
    }

    @StringRes
    public int getName(){
        return name;
    }


}
