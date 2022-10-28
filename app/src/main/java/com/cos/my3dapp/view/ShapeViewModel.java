package com.cos.my3dapp.view;

import com.cos.my3dapp.RendererType;
import com.cos.my3dapp.model.base.ShapeRenderer;

public class ShapeViewModel {
    private ShapeRenderer<?> renderer;

    public ShapeViewModel(RendererType type){
        renderer = type.getRenderer();
    }

    public ShapeRenderer<?> getRenderer(){
        return renderer;
    }

    public void onResume(){
        renderer.onResume();
    }

    public void onPause(){
        renderer.onPause();
    }


}
