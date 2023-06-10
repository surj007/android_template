package com.g26.myeducationalapp.domain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.g26.myeducationalapp.bean.Course;
import com.g26.myeducationalapp.data.repository.CourseRepository;
import com.g26.myeducationalapp.util.callback.Callback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class FormatCourseDataUserCase {
    private final CourseRepository courseRepository;

    @Inject
    public FormatCourseDataUserCase(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getCourses(String name, Callback<List<Course>> callback) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<List<Course>> future1 = new CompletableFuture<>();
        executorService.submit(() -> {
            this.courseRepository.getComputingCourses(name, new Callback<>() {
                @Override
                public void onSuccess(List<Course> result) {
                    future1.complete(result);
                }
            });
        });

        CompletableFuture<List<Course>> future2 = new CompletableFuture<>();
        executorService.submit(() -> {
            this.courseRepository.getEngineerCourses(name, new Callback<>() {
                @Override
                public void onSuccess(List<Course> result) {
                    future2.complete(result);
                }
            });
        });

        CompletableFuture.allOf(future1, future2).whenComplete((ignored, throwable) -> {
            executorService.shutdown();
            if (throwable != null) {
                callback.onFailed();
            } else {
                var result = new ArrayList<Course>();
                result.addAll(future1.join());
                result.addAll(future2.join());
                callback.onSuccess(result);
            }
        });
    }
}
