package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ViewGroup container = (ViewGroup) btn.getParent().getParent();
                container.getOverlay().add(btn);
                ObjectAnimator transition = ObjectAnimator.ofFloat(btn, "translationY", container.getHeight());
                ObjectAnimator rotation = ObjectAnimator.ofFloat(btn, "rotation", 0, 360);
                rotation.setRepeatCount(Animation.INFINITE);
                rotation.setRepeatMode(Animation.REVERSE);
                rotation.setDuration(350);
                transition.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        container.getOverlay().remove(btn);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        container.getOverlay().remove(btn);
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                transition.setDuration(2000);

                AnimatorSet animSet = new AnimatorSet();
                animSet.playTogether(transition, rotation);
                animSet.start();
            }
        });
        final Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup container = (ViewGroup) btn2.getParent().getParent();
                ObjectAnimator transition = ObjectAnimator.ofFloat(btn2, "translationY", container.getHeight());
                transition.setDuration(2000);
                transition.start();
            }
        });
        final Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ViewGroup container = (ViewGroup) btn2.getParent();
                final ObjectAnimator transition = ObjectAnimator.ofFloat(btn3, "translationY", -container.getHeight() * 2);
                transition.setDuration(2000);
                transition.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        container.getOverlay().remove(btn3);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(btn3, "alpha", 1f, 0);
                fadeAnim.setDuration(500);
                fadeAnim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        btn3.setAlpha(1);
                        container.getOverlay().add(btn3);
                        transition.start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                fadeAnim.start();
            }
        });
    }
}
