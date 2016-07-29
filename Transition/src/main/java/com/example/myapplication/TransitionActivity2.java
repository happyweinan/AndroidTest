package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by wnzhang on 16-5-29.
 */
public class TransitionActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        setupWindowAnimations();
        TextView tv = (TextView) findViewById(R.id.content_tv);
        tv.setText("TransitionActivity2");
        findViewById(R.id.btn1).setVisibility(View.INVISIBLE);
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });
    }

    private void setupWindowAnimations() {
        Slide slide1 = new Slide(Gravity.BOTTOM);
        getWindow().setEnterTransition(slide1);

        Fade fade = new Fade(Fade.OUT);
        getWindow().setReturnTransition(fade);
    }
}
