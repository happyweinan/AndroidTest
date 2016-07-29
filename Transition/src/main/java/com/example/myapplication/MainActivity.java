package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
        Button btn = (Button) findViewById(R.id.trans_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransitionActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, null);
                startActivity(intent, options.toBundle());
            }
        });

        final ImageView iv = (ImageView) findViewById(R.id.circle_iv);

        final Button btn2 = (Button) findViewById(R.id.share_element_btn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShareElementActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, Pair.create((View) iv, getString(R.string.square_blue_name)), Pair.create((View) btn2, "hello"));
                startActivity(intent, options.toBundle());
            }
        });
    }

    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.LEFT);
        slide.setDuration(500);
        getWindow().setReenterTransition(slide);
        Fade fade = new Fade(Fade.OUT);
        fade.setDuration(500);
        getWindow().setExitTransition(fade);
    }
}
