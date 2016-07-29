package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv)
    TextView tv;

    @Bind(R.id.btn1)
    Button btn1;

    @Bind(R.id.btn2)
    Button btn2;

    @Bind(R.id.btn3)
    Button btn3;

    @Bind(R.id.btn4)
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv)
    public void clickTv() {
        tv.setTextColor(Color.RED);
    }

    //不会改变left right,只是改变了内容的位置,新位置可以响应点击事件
    @OnClick(R.id.btn1)
    public void clickBtn1() {
        tv.scrollBy(-100, 0);
//        tv.setPadding(100, 0, 0, 0);
    }

    //会改变left right,新位置可以响应点击事件
    @OnClick(R.id.btn2)
    public void cliclBtn2() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tv.getLayoutParams();
        params.leftMargin += 300;
        tv.requestLayout();
    }

    //不会改变left right,只是在新位置又绘制了一个view,新位置不可以响应点击事件
    @OnClick(R.id.btn3)
    public void cliclBtn3() {
        TranslateAnimation animation = new TranslateAnimation(0, 300, 0, 0);
        animation.setFillAfter(true);
        tv.startAnimation(animation);
    }

    //不会改变left right,会改变translationX x,新位置可以响应点击事件
    @OnClick(R.id.btn4)
    public void clickBtn4() {
        tv.setTranslationX(300);
    }
}
