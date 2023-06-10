package com.g26.myeducationalapp.data.repository;

import com.g26.myeducationalapp.bean.Course;
import com.g26.myeducationalapp.data.source.CourseLocalDataSource;
import com.g26.myeducationalapp.data.source.CourseRemoteDataSource;
import com.g26.myeducationalapp.util.callback.Callback;

import java.util.List;

import javax.inject.Inject;

public class CourseRepository {
    private final CourseRemoteDataSource courseRemoteDataSource;
    private final CourseLocalDataSource courseLocalDataSource;

    @Inject
    public CourseRepository(CourseRemoteDataSource courseRemoteDataSource, CourseLocalDataSource courseLocalDataSource) {
        this.courseRemoteDataSource = courseRemoteDataSource;
        this.courseLocalDataSource = courseLocalDataSource;
    }

    public void getComputingCourses(String name, Callback<List<Course>> callback) {
        this.courseRemoteDataSource.getComputingCourses(name, callback);
    }

    public void getEngineerCourses(String name, Callback<List<Course>> callback) {
        this.courseLocalDataSource.getEngineerCourses(name, callback);
    }
}
