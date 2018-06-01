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

    public int viewtype;
    public int flashtime;
    private DanmakuContext danmakuContext;
    private boolean showDanmaku;
    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    private DanmakuView mDanmakuView;
    private DanmakuSurfaceView mDanmakuSurfaceView;
    private DanmakuTextureView mDanmakuTextureView;

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

        mFloatBallParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;

        // 设置悬浮窗口长宽数据
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        mFloatBallParams.height = WindowManager.LayoutParams.WRAP_CONTENT; //490
        mFloatBallParams.width = dm.widthPixels; //WindowManager.LayoutParams.WRAP_CONTENT;

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

    public void createDanmakuView(int viewtype, int x, int y) {
        this.viewtype = viewtype;
        danmakuContext = DanmakuContext.create();
        if (viewtype == 1) {
            mDanmakuView = new DanmakuView(mContext);
            mDanmakuView.enableDanmakuDrawingCache(true);
            mDanmakuView.setCallback(new DrawHandler.Callback() {
                @Override
                public void prepared() {
                    showDanmaku = true;
                    mDanmakuView.start();
                    generateSomeDanmaku();
                }
                @Override
                public void updateTimer(DanmakuTimer timer) {  }
                @Override
                public void danmakuShown(BaseDanmaku danmaku) {  }
                @Override
                public void drawingFinished() {  }
            });
            mDanmakuView.prepare(parser, danmakuContext);
        } else if (viewtype == 2) {
            mDanmakuSurfaceView = new DanmakuSurfaceView(mContext);
            mDanmakuSurfaceView.enableDanmakuDrawingCache(true);
            mDanmakuSurfaceView.setCallback(new DrawHandler.Callback() {
                @Override
                public void prepared() {
                    showDanmaku = true;
                    mDanmakuSurfaceView.start();
                    generateSomeDanmaku();
                }
                @Override
                public void updateTimer(DanmakuTimer timer) {  }
                @Override
                public void danmakuShown(BaseDanmaku danmaku) {  }
                @Override
                public void drawingFinished() {  }
            });
            mDanmakuSurfaceView.prepare(parser, danmakuContext);
        } else if (viewtype == 3) {
            mDanmakuTextureView = new DanmakuTextureView(mContext);
            mDanmakuTextureView.enableDanmakuDrawingCache(true);
            mDanmakuTextureView.setCallback(new DrawHandler.Callback() {
                @Override
                public void prepared() {
                    showDanmaku = true;
                    mDanmakuTextureView.start();
                    generateSomeDanmaku();
                }
                @Override
                public void updateTimer(DanmakuTimer timer) {  }
                @Override
                public void danmakuShown(BaseDanmaku danmaku) {  }
                @Override
                public void drawingFinished() {  }
            });
            mDanmakuTextureView.prepare(parser, danmakuContext);
        }
        mFloatBallParams.x = x;
        mFloatBallParams.y = y;
    }

    /**
     * 随机生成一些弹幕内容以供测试
     */
    private void generateSomeDanmaku() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(showDanmaku) {
                    int time = new Random().nextInt(300);
                    String content = "" + time + time;
                    addDanmaku(content, false);
                    try {
                        Thread.sleep(flashtime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void addDanmaku(String content, boolean withBorder) {
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = sp2px(20);
        danmaku.textColor = Color.RED;
        //danmaku.time = mDanmakuView.getCurrentTime() + 1200; //显示时间
        if (withBorder) {
            danmaku.borderColor = Color.GREEN;
        }

        if (this.viewtype == 1) {
            danmaku.setTime(mDanmakuView.getCurrentTime());
            mDanmakuView.addDanmaku(danmaku);
        } else if (this.viewtype == 2) {
            danmaku.setTime(mDanmakuSurfaceView.getCurrentTime());
            mDanmakuSurfaceView.addDanmaku(danmaku);
        } else if (this.viewtype == 3) {
            danmaku.setTime(mDanmakuTextureView.getCurrentTime());
            mDanmakuTextureView.addDanmaku(danmaku);
        }
    }

    public int sp2px(float spValue) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 添加图片
     */
    public void addImageView() {
        mWindowManager.addView(mImageView, mFloatBallParams);
    }

    public void addDanmakuView() {
        if(mDanmakuView.getParent()==null) {
            mWindowManager.addView(mDanmakuView, mFloatBallParams);
        }
    }

    public void addDanmakuSurfaceView() {
        if(mDanmakuSurfaceView.getParent()==null) {
            mWindowManager.addView(mDanmakuSurfaceView, mFloatBallParams);
        }
    }

    public void addDanmakuTextureView() {
        if(mDanmakuTextureView.getParent()==null) {
            mWindowManager.addView(mDanmakuTextureView, mFloatBallParams);
        }
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
        mDanmakuView.setOnClickListener(listener);
        mDanmakuSurfaceView.setOnClickListener(listener);
        mDanmakuTextureView.setOnClickListener(listener);
    }

    /**
     * 返回图片实例
     *
     * @return
     */
    public ImageView getImageView() {
        return mImageView;
    }

    public DanmakuView getDanmakuView() {
        return mDanmakuView;
    }

    public DanmakuSurfaceView getDanmakuSurfaceView() {
        return mDanmakuSurfaceView;
    }

    public DanmakuTextureView getDanmakuTextureView() {
        return mDanmakuTextureView;
    }

    /**
     * 更新
     */
    public void updateWindowManager() {
        mWindowManager.updateViewLayout(mImageView, mFloatBallParams);
        mWindowManager.updateViewLayout(mDanmakuView, mFloatBallParams);
        mWindowManager.updateViewLayout(mDanmakuSurfaceView, mFloatBallParams);
        mWindowManager.updateViewLayout(mDanmakuTextureView, mFloatBallParams);
    }
}
