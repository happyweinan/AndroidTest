package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by wnzhang on 16-5-29.
 */
public class TransitionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        setupWindowAnimations();
        TextView tv = (TextView) findViewById(R.id.content_tv);
        tv.setText("TransitionActivity");
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransitionActivity.this, TransitionActivity2.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(TransitionActivity.this, null);
                startActivity(intent, options.toBundle());
            }
        });
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });
    }

    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(500);
        getWindow().setEnterTransition(fade);

        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);

        Slide slide = new Slide(Gravity.RIGHT);
        slide.setDuration(500);
        getWindow().setReturnTransition(slide);
    }
}
