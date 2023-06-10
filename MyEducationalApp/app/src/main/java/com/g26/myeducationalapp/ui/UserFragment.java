package com.g26.myeducationalapp.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.LayoutInflater;

import com.g26.myeducationalapp.R;
import com.g26.myeducationalapp.databinding.FragmentUserBinding;
import com.g26.myeducationalapp.service.BoundService;
import com.g26.myeducationalapp.service.StartedService;
import com.g26.myeducationalapp.ui.base.BaseFragment;

public class UserFragment extends BaseFragment<FragmentUserBinding> {
    private static final String TAG = "UserFragment";
    private BoundService.MyBinder binder;
    private boolean isServiceBound = false;
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            UserFragment.this.binder = (BoundService.MyBinder) iBinder;
            UserFragment.this.isServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            UserFragment.this.isServiceBound = false;
        }
    };

    @Override
    protected FragmentUserBinding createViewBinding(LayoutInflater inflater) {
        return FragmentUserBinding.inflate(inflater);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {
        this.binding.logoutBtn.setOnClickListener(item -> {
            startActivity(new Intent(getContext(), LoginActivity.class));
        });
        this.binding.startStartedServiceBtn.setOnClickListener(item -> {
            Intent startedServiceIntent = new Intent(getContext(), StartedService.class);
            requireContext().startService(startedServiceIntent);
        });
        this.binding.stopStartedServiceBtn.setOnClickListener(item -> {
            Intent startedServiceIntent = new Intent(getContext(), StartedService.class);
            requireContext().stopService(startedServiceIntent);
        });
        this.binding.bindBoundServiceBtn.setOnClickListener(item -> {
            Intent boundServiceIntent = new Intent(getContext(), BoundService.class);
            requireContext().bindService(boundServiceIntent, this.serviceConnection, Context.BIND_AUTO_CREATE);
        });
        this.binding.startBoundServiceBtn.setOnClickListener(item -> {
            if (this.isServiceBound) {
                this.binder.fetchWeather();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.isServiceBound) {
            requireContext().unbindService(this.serviceConnection);
            this.isServiceBound = false;
        }
    }
}
