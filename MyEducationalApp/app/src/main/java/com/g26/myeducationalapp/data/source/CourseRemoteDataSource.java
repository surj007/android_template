package com.g26.myeducationalapp.data.source;

import androidx.annotation.NonNull;

import com.g26.myeducationalapp.bean.Course;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class CourseRemoteDataSource {
    private interface CourseApiService {
        @GET("getComputingCourses")
        Call<List<Course>> getComputingCourses(@Query("name") String name);
    }

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.fastmock.site/mock/6d3526ff9b9411c221d073001ebf8665/android/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private final CourseApiService courseApiService = this.retrofit.create(CourseApiService.class);

    @Inject
    public CourseRemoteDataSource() {
    }

    public void getComputingCourses(String name, com.g26.myeducationalapp.util.callback.Callback<List<Course>> callback) {
        this.courseApiService.getComputingCourses(name).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<Course>> call, @NonNull Response<List<Course>> res) {
                if (res.isSuccessful()) {
                    callback.onSuccess(res.body());
                } else {
                    callback.onFailed();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Course>> call, @NonNull Throwable t) {
                callback.onFailed();
            }
        });
    }
}
