package com.example.app.bulletcomments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    public int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button BtnDanmukuView = (Button)findViewById(R.id.button1);
        Button BtnDanmukuSurfaceView = (Button)findViewById(R.id.button2);
        Button BtnDanmukuTextureView = (Button)findViewById(R.id.button3);
        final  LinearLayout operationLayout = (LinearLayout) findViewById(R.id.operation_layout);
        final Button setFlashTime = (Button) findViewById(R.id.button0);
        final EditText editText = (EditText) findViewById(R.id.flashtime);

        setFlashTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editText.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    time = Integer.parseInt(content);
                }
                editText.setText("");
                //addDanmaku(content, true);
            }
        });

        BtnDanmukuView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FloatView mFloatView;
                mFloatView = new FloatView(getApplicationContext());
                mFloatView.flashtime = time;
                int floatHeight = mFloatView.getStatusBarHeight();
                mFloatView.createDanmakuView(1,0, floatHeight);
                mFloatView.addDanmakuView();
            }
        });
        BtnDanmukuSurfaceView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FloatView mFloatView;
                mFloatView = new FloatView(getApplicationContext());
                mFloatView.flashtime = time;
                int floatHeight = mFloatView.getStatusBarHeight();
                mFloatView.createDanmakuView(2,0, floatHeight);
                mFloatView.addDanmakuSurfaceView();
            }
        });
        BtnDanmukuTextureView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FloatView mFloatView;
                mFloatView = new FloatView(getApplicationContext());
                mFloatView.flashtime = time;
                int floatHeight = mFloatView.getStatusBarHeight();
                mFloatView.createDanmakuView(3,0, floatHeight);
                mFloatView.addDanmakuTextureView();
            }
        });
    }
}
