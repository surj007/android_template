package com.g26.myeducationalapp.ui;

import android.view.LayoutInflater;
import com.g26.myeducationalapp.R;
import com.g26.myeducationalapp.databinding.ActivityMainBinding;
import com.g26.myeducationalapp.ui.base.BaseActivity;
import com.g26.myeducationalapp.ui.course.CourseFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private static final String TAG = "MainActivity";

    @Override
    protected ActivityMainBinding createViewBinding(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(inflater);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();

        var fragmentManager = getSupportFragmentManager();
        var courseFragment = new CourseFragment();
        var userFragment = new UserFragment();

        this.binding.bottomNavigation.setOnItemSelectedListener(item -> {
            var transaction = fragmentManager.beginTransaction();
            var itemId = item.getItemId();

            if (itemId == R.id.item_course) {
                transaction.replace(R.id.fragment_container, courseFragment).commit();
                return true;
            } else if (itemId == R.id.item_user) {
                transaction.replace(R.id.fragment_container, userFragment).commit();
                return true;
            }

            return true;
        });

        fragmentManager.beginTransaction().replace(R.id.fragment_container, courseFragment).commit();
    }
}
