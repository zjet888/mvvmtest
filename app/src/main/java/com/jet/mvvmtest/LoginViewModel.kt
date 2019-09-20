package com.jet.mvvmtest

import android.os.Handler
import android.text.TextUtils
import android.view.View

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class LoginViewModel : BaseObservable() {

    @Bindable
    var clicked: Boolean? = false
        set(clicked) {
            field = clicked
            notifyPropertyChanged(BR.clicked)
        }

    fun onLoginClicked() {
        clicked = true
    }


}
