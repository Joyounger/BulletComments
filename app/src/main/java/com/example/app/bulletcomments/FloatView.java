package com.example.app.bulletcomments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuSurfaceView;
import master.flame.danmaku.ui.widget.DanmakuTextureView;
import master.flame.danmaku.ui.widget.DanmakuView;
import master.flame.danmaku.ui.widget.FakeDanmakuView;

public class FloatView {
    /**
     * 窗口布局参数
     */
    private WindowManager.LayoutParams mFloatBallParams;

    private ImageView mImageView;

    private WindowManager mWindowManager;

    private Context mContext;

    public FloatView(Context context) {
        mContext = context;
        initFloatBallParams(mContext);
    }

    /**
     * 获取悬浮球的布局参数
     */
    private void initFloatBallParams(Context context) {
        mFloatBallParams = new WindowManager.LayoutParams();
        mFloatBallParams.flags = mFloatBallParams.flags
                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mFloatBallParams.dimAmount = 0.2f;

//      mFloatBallParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;

        mFloatBallParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mFloatBallParams.width = WindowManager.LayoutParams.WRAP_CONTENT;

        mFloatBallParams.gravity = Gravity.LEFT | Gravity.TOP;
        mFloatBallParams.format = PixelFormat.RGBA_8888;
        // 设置整个窗口的透明度
        mFloatBallParams.alpha = 1.0f;
        // 显示悬浮球在屏幕左上角
        mFloatBallParams.x = 0;
        mFloatBallParams.y = 0;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 贴图片
     *
     * @param resourceId 图片的资源id
     * @param x          宽度
     * @param y          高度
     */
    public void createImageView(int resourceId, int x, int y) {
        mImageView = new ImageView(mContext);
        mImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), resourceId));
        mFloatBallParams.x = x;
        mFloatBallParams.y = y;
    }

    /**
     * 添加图片
     */
    public void addImageView() {
        mWindowManager.addView(mImageView, mFloatBallParams);
    }

    /**
     * 隐藏图片
     */
    public void dismissFloatView() {
        mWindowManager.removeView(mImageView);
    }

    /**
     * 添加事件监听
     *
     * @param listener
     */
    public void setOnClickListener(View.OnClickListener listener) {
        mImageView.setOnClickListener(listener);
    }

    /**
     * 返回图片实例
     *
     * @return
     */
    public ImageView getImageView() {
        return mImageView;
    }

    /**
     * 更新
     */
    public void updateWindowManager() {
        mWindowManager.updateViewLayout(mImageView, mFloatBallParams);
    }
}
