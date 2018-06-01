package com.example.app.bulletcomments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatView mFloatView;
        mFloatView = new FloatView(getApplicationContext());
        int floatHeight = mFloatView.getStatusBarHeight();
        mFloatView.createDanmakuView(1,0, floatHeight);
        mFloatView.addDanmakuView();
    }
}
