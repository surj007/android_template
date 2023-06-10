package com.g26.myeducationalapp.ui.course;

import android.widget.Button;

import androidx.databinding.BindingAdapter;

import com.g26.myeducationalapp.R;
import com.g26.myeducationalapp.bean.Course;

import java.util.Random;

public class BackgroundImageBindingAdapter {
    @BindingAdapter("backgroundImageResource")
    public static void setBackgroundImageResource(Button button, Course course) {
        if (new Random().nextDouble() > 0.5) {
            button.setBackgroundResource(R.drawable.follow);
        } else {
            button.setBackgroundResource(R.drawable.unfollow);
        }
    }
}
