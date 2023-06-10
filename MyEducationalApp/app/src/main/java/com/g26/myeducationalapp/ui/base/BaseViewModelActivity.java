package com.g26.myeducationalapp.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseViewModelActivity<B extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {
    protected B binding;
    protected VM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = DataBindingUtil.setContentView(this, getLayoutResId());

        this.viewModel = new ViewModelProvider(this).get(this.getViewModelClass());
        this.binding.setVariable(BR.viewmodel, this.viewModel);
        this.binding.setLifecycleOwner(this);

        this.initView();
        this.initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
        this.viewModel = null;
    }

    protected abstract B createViewBinding(LayoutInflater inflater);

    protected abstract int getLayoutResId();

    protected abstract Class<VM> getViewModelClass();

    protected void initView() {
    }

    protected void initData() {
    }
}
