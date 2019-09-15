package com.jet.mvvmtest;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
public class LoginViewModel extends BaseObservable {

    @Bindable
    private Boolean clicked = false;

    public Boolean getClicked() {
        return clicked;
    }

    public void setClicked(Boolean clicked) {
        this.clicked = clicked;
        notifyPropertyChanged(BR.clicked);
    }

    public void onLoginClicked() {
        setClicked(true);
    }


}
