package com.g26.myeducationalapp.ui.course;

import android.view.LayoutInflater;
import android.widget.SearchView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.g26.myeducationalapp.R;
import com.g26.myeducationalapp.data.repository.CourseRepository;
import com.g26.myeducationalapp.databinding.FragmentCourseBinding;
import com.g26.myeducationalapp.ui.base.BaseViewModelFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CourseFragment extends BaseViewModelFragment<FragmentCourseBinding, CourseViewModel> implements CourseAdapter.OnItemClickListener {
//    @Inject
//    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected FragmentCourseBinding createViewBinding(LayoutInflater inflater) {
        return FragmentCourseBinding.inflate(inflater);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_course;
    }

    @Override
    protected Class<CourseViewModel> getViewModelClass() {
        return CourseViewModel.class;
    }

    @Override
    protected void initView() {
        super.initView();

        this.binding.coursesList.setLayoutManager(new LinearLayoutManager(getContext()));
        CourseAdapter adapter = new CourseAdapter(getContext(), new ArrayList<>());
        adapter.setOnItemClickListener(this);
        this.binding.coursesList.setAdapter(adapter);
        this.viewModel.getCourses().observe(this, adapter::updateItems);

        this.binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String str) {
                CourseFragment.this.viewModel.getCourses(str);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String str) {
                return true;
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        this.viewModel.getCourses("");
    }

    @Override
    public void onMessageButtonClick(int position) {
    }

    @Override
    public void onFollowButtonClick(int position) {
    }
}
