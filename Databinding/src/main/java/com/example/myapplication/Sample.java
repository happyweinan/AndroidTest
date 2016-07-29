package com.example.myapplication;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.io.Serializable;

/**
 * Created by wnzhang on 16-6-4.
 */
public class Sample extends BaseObservable implements Serializable  {
    String name;

    public Sample(String name) {
        this.name = name;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.example.myapplication.BR.name);
    }
}
