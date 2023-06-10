package com.g26.myeducationalapp.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;

//@AndroidEntryPoint
public abstract class BaseViewModelFragment<B extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {
    protected B binding;
    protected VM viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, this.getLayoutResId(), container, false);;

        this.viewModel = new ViewModelProvider(this).get(this.getViewModelClass());;
        this.binding.setVariable(BR.viewmodel, this.viewModel);
        this.binding.setLifecycleOwner(getViewLifecycleOwner());

        this.initView();
        this.initData();

        return this.binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
        this.viewModel = null;
    }

    protected abstract B createViewBinding(LayoutInflater inflater);

    protected abstract Class<VM> getViewModelClass();

    protected abstract int getLayoutResId();

    protected void initView() {
    }

    protected void initData() {
    }
}
