package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by wnzhang on 16-7-9.
 */
public class RocketService extends Service {
    private static final String TAG = "RocketService";
    private int widthPixels;
    private int heightPixels;
    private WindowManager mWindowMgr;
    private View view;
    private WindowManager.LayoutParams params;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (params.y > 0) {
                params.y -= 20;
                mWindowMgr.updateViewLayout(view, params);
                Message message2 = Message.obtain();
                sendEmptyMessageDelayed(0, 100);
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.e(TAG,"onCreate");
        super.onCreate();
        mWindowMgr = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        mWindowMgr.getDefaultDisplay().getMetrics(metrics);
        widthPixels = metrics.widthPixels;
        heightPixels = metrics.heightPixels;

        view = View.inflate(getApplicationContext(), R.layout.rocket, null);
        ImageView rocketIv = (ImageView) view.findViewById(R.id.iv_rocket);

        params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
        // 不设置这个弹出框的透明遮罩显示为黑色
        params.format = PixelFormat.TRANSLUCENT;
        // 不设置这个FLAG_NOT_FOCUSABLE的话，home页的划屏会有问题
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.gravity = Gravity.LEFT | Gravity.TOP;

        AnimationDrawable animationDrawable = (AnimationDrawable) rocketIv.getBackground();
        animationDrawable.start();
        mWindowMgr.addView(view, params);
        setTouch();
    }

    private void setTouch() {
        view.setOnTouchListener(new View.OnTouchListener() {
            private int startX;
            private int startY;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int newX = (int) event.getRawX();
                        int newY = (int) event.getRawY();
                        int dx = newX - startX;
                        int dy = newY - startY;
                        params.x += dx;
                        params.y += dy;
                        if (params.y < 0) {
                            params.y = 0;
                        }
                        mWindowMgr.updateViewLayout(view, params);
                        startX = newX;
                        startY = newY;
                        break;
                    case MotionEvent.ACTION_UP:
                        sendRocket();
                        break;
                }
                return true;
            }

            private void sendRocket() {
                mHandler.sendEmptyMessage(0);
            }
        });
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestroy");
        mWindowMgr.removeView(view);
        super.onDestroy();
    }
}
