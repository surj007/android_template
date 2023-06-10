package com.g26.myeducationalapp.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.g26.myeducationalapp.R;
import com.g26.myeducationalapp.databinding.ActivityLoginBinding;
import com.g26.myeducationalapp.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    private static final String TAG = "LoginActivity";

    @Override
    protected ActivityLoginBinding createViewBinding(LayoutInflater inflater) {
        return ActivityLoginBinding.inflate(inflater);
    }

    @Override
    protected void initView() {
        super.initView();

        this.binding.loginBtn.setOnClickListener(view -> {
            String username = this.binding.usernameEditText.getText().toString();
            String password = this.binding.passwordEditText.getText().toString();
            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(LoginActivity.this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(this, MainActivity.class));
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
    }
}
