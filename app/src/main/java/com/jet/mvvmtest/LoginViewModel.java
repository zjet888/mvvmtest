package com.jet.mvvmtest;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
public class LoginViewModel extends BaseObservable {

    @Bindable
    private String username = null;

    @Bindable
    private String password = null;

    @Bindable
    private Boolean clicked = false;

    @Bindable
    private int progressbarStatus = View.GONE;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

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

    public int getProgressbarStatus() {
        return progressbarStatus;
    }

    public void setProgressbarStatus(int progressbarStatus) {
        this.progressbarStatus = progressbarStatus;
        notifyPropertyChanged(BR.progressbarStatus);
    }
}
