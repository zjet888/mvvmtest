package com.jet.mvvmtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jet.mvvmtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding getActivityMainBinding() {
        return activityMainBinding;
    }

    private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(new LoginViewModel());
        activityMainBinding.executePendingBindings();
    }

    @BindingAdapter({"clicked"})
    public static void checkClicked(View view, boolean clicked) {
        Activity activity = getActivity(view.getContext());
        if(activity == null){
            return;
        }
        MainActivity mainActivity = (MainActivity)activity;
        if(clicked) {
            EditText textViewUsername = mainActivity.getActivityMainBinding().username;
            if (TextUtils.isEmpty(textViewUsername.getText())) {
                textViewUsername.setError(view.getContext().getString(R.string.username_error));
                return;
            }
            EditText textViewPassword = mainActivity.getActivityMainBinding().password;
            if (TextUtils.isEmpty(textViewPassword.getText())) {
                textViewPassword.setError(view.getContext().getString(R.string.password_error));
                return;
            }

            mainActivity.getActivityMainBinding().progress.setVisibility(View.VISIBLE);
            new Handler().postDelayed(() -> {
                Toast.makeText(view.getContext(), R.string.log_in_succeed, Toast.LENGTH_SHORT).show();
                mainActivity.getActivityMainBinding().progress.setVisibility(View.GONE);
            }, 2000);

        }
    }

    public static Activity getActivity(Context context)
    {
        if (context == null)
        {
            return null;
        }
        else if (context instanceof ContextWrapper)
        {
            if (context instanceof Activity)
            {
                return (Activity) context;
            }
            else
            {
                return getActivity(((ContextWrapper) context).getBaseContext());
            }
        }

        return null;
    }

}
