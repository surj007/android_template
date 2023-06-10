package com.g26.myeducationalapp.data.source;

import com.g26.myeducationalapp.bean.Course;
import com.g26.myeducationalapp.util.callback.Callback;

import java.util.List;

import javax.inject.Inject;

public class CourseLocalDataSource {
    @Inject
    public CourseLocalDataSource() {
    }

    public void getEngineerCourses(String name, Callback<List<Course>> callback) {
        callback.onSuccess(List.of(new Course("4", "PP1", "https://firebasestorage.googleapis.com/v0/b/my-educational-app-82675.appspot.com/o/pp1.jpg?alt=media&token=a463fb01-37df-4616-ab6d-b5649751bc71&_gl=1*y6udaj*_ga*MjA5NzA1MTk5OC4xNjgwNTg0NDQx*_ga_CW55HF8NVT*MTY4NjMxMzE2NC40OS4xLjE2ODYzMTMxODkuMC4wLjA.")));
    }
}
