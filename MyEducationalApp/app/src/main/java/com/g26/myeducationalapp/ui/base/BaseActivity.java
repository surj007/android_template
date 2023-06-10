package com.g26.myeducationalapp.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {
    protected B binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.binding = DataBindingUtil.setContentView(this, this.getLayoutResId());

        this.initView();
        this.initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    protected abstract B createViewBinding(LayoutInflater inflater);

    protected abstract int getLayoutResId();

    protected void initView() {
    }

    protected void initData() {
    }
}
