package com.g26.myeducationalapp.ui.course;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.g26.myeducationalapp.bean.Course;
import com.g26.myeducationalapp.data.repository.CourseRepository;
import com.g26.myeducationalapp.domain.FormatCourseDataUserCase;
import com.g26.myeducationalapp.ui.base.BaseViewModel;
import com.g26.myeducationalapp.util.callback.Callback;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CourseViewModel extends BaseViewModel {
    private static final String TAG = "CourseViewModel";
    private final FormatCourseDataUserCase formatCourseDataUserCase;

    @Inject
    public CourseViewModel(FormatCourseDataUserCase formatCourseDataUserCase) {
        this.formatCourseDataUserCase = formatCourseDataUserCase;
    }

    private final MutableLiveData<List<Course>> courses = new MutableLiveData<>();

    public MutableLiveData<List<Course>> getCourses() {
        return this.courses;
    }

    public void getCourses(String name) {
        this.formatCourseDataUserCase.getCourses(name, new Callback<>() {
            @Override
            public void onSuccess(List<Course> result) {
                CourseViewModel.this.courses.setValue(result);
            }
        });
    }
}
