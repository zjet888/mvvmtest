package com.jet.mvvmtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

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

    private static LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setViewModel(loginViewModel = new LoginViewModel());
        activityMainBinding.executePendingBindings();
    }

    @BindingAdapter({"progressbarStatus"})
    public static void progressbarStatusCheck(ProgressBar view, int status) {
        view.setVisibility(status);
    }
    @BindingAdapter({"clicked"})
    public static void checkClicked(View view, boolean clicked) {
        if(clicked) {
            if (TextUtils.isEmpty(textViewUsername.getText())) {
                textViewUsername.setError(view.getContext().getString(R.string.username_error));
                return;
            }
            if (TextUtils.isEmpty(textViewPassword.getText())) {
                textViewPassword.setError(view.getContext().getString(R.string.password_error));
                return;
            }

            loginViewModel.setProgressbarStatus(View.VISIBLE);
            new Handler().postDelayed(() -> {
                Toast.makeText(view.getContext(), R.string.log_in_succeed, Toast.LENGTH_SHORT).show();
                loginViewModel.setProgressbarStatus(View.GONE);
            }, 2000);

        }
    }

    private static TextView textViewUsername;
    private static TextView textViewPassword;

    @BindingAdapter({"username"})
    public static void usernameCheck(TextView view, String username) {
        textViewUsername = view;
    }

    @BindingAdapter({"password"})
    public static void passwordCheck(TextView view, String password) {
        textViewPassword = view;
    }
}
