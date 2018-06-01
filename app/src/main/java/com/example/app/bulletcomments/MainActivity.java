package com.example.app.bulletcomments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button BtnDanmukuView = (Button)findViewById(R.id.button1);
        Button BtnDanmukuSurfaceView = (Button)findViewById(R.id.button2);
        Button BtnDanmukuTextureView = (Button)findViewById(R.id.button3);

        BtnDanmukuView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FloatView mFloatView;
                mFloatView = new FloatView(getApplicationContext());
                int floatHeight = mFloatView.getStatusBarHeight();
                mFloatView.createDanmakuView(1,0, floatHeight);
                mFloatView.addDanmakuView();
            }
        });
        BtnDanmukuSurfaceView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FloatView mFloatView;
                mFloatView = new FloatView(getApplicationContext());
                int floatHeight = mFloatView.getStatusBarHeight();
                mFloatView.createDanmakuView(2,0, floatHeight);
                mFloatView.addDanmakuSurfaceView();
            }
        });
        BtnDanmukuTextureView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FloatView mFloatView;
                mFloatView = new FloatView(getApplicationContext());
                int floatHeight = mFloatView.getStatusBarHeight();
                mFloatView.createDanmakuView(3,0, floatHeight);
                mFloatView.addDanmakuTextureView();
            }
        });
    }
}
