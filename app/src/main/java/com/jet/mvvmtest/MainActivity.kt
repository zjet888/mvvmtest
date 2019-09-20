package com.jet.mvvmtest

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import com.jet.mvvmtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var activityMainBinding: ActivityMainBinding? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding!!.viewModel = LoginViewModel()
        activityMainBinding!!.executePendingBindings()
    }

    companion object {

        @BindingAdapter("clicked")
        fun checkClicked(view: View, clicked: Boolean) {
            val activity = getActivity(view.context) ?: return
            val mainActivity = activity as MainActivity
            if (clicked) {
                val textViewUsername = mainActivity.activityMainBinding!!.username
                if (TextUtils.isEmpty(textViewUsername.text)) {
                    textViewUsername.error = view.context.getString(R.string.username_error)
                    return
                }
                val textViewPassword = mainActivity.activityMainBinding!!.password
                if (TextUtils.isEmpty(textViewPassword.text)) {
                    textViewPassword.error = view.context.getString(R.string.password_error)
                    return
                }

                mainActivity.activityMainBinding!!.progress.visibility = View.VISIBLE
                Handler().postDelayed({
                    Toast.makeText(view.context, R.string.log_in_succeed, Toast.LENGTH_SHORT).show()
                    mainActivity.activityMainBinding!!.progress.visibility = View.GONE
                }, 2000)

            }
        }

        fun getActivity(context: Context?): Activity? {
            if (context == null) {
                return null
            } else if (context is ContextWrapper) {
                return if (context is Activity) {
                    context
                } else {
                    getActivity(context.baseContext)
                }
            }

            return null
        }
    }

}
