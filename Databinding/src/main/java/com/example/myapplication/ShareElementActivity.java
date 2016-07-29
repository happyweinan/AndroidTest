package com.example.myapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.databinding.ActivityShareElementBinding;
import com.google.common.util.concurrent.Runnables;

/**
 * Created by wnzhang on 16-5-29.
 */
public class ShareElementActivity extends AppCompatActivity {
    private Sample mSample;
    private ActivityShareElementBinding mBinding;
    private Handler mHandler = new Handler();
    private Runnable mTask = new Runnable() {
        @Override
        public void run() {
            mSample.setName(String.valueOf(System.currentTimeMillis()));
            mHandler.postDelayed(this,2000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSample = (Sample) getIntent().getSerializableExtra("sample");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_share_element);
        bindData(mSample);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mHandler.post(mTask);
    }

    private void bindData(Sample sample) {
        mBinding.setSample(sample);
    }
}
