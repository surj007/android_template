package com.g26.myeducationalapp.ui.course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.g26.myeducationalapp.R;
import com.g26.myeducationalapp.bean.Course;
import com.g26.myeducationalapp.databinding.CourseListItemBinding;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.IViewHolder> {
    private static final String TAG = "CourseAdapter";
    private static OnItemClickListener listener;
    private final Context context;
    private List<Course> courses;

    public interface OnItemClickListener {
        public void onFollowButtonClick(int position);
        public void onMessageButtonClick(int position);
    }

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    public void updateItems(List<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CourseListItemBinding binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.getContext()),
            R.layout.course_list_item,
            parent,
            false
        );
        return new IViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IViewHolder holder, int position) {
        holder.bind(this.courses.get(position));
        String url = this.courses.get(position).getImgUrl();
        Glide.with(this.context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.placeholder_image)
                .into(holder.binding.courseImageView);
    }

    @Override
    public int getItemCount() {
        return this.courses == null ? 0 : this.courses.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        CourseAdapter.listener = listener;
    }

    public static class IViewHolder extends RecyclerView.ViewHolder {
        private final CourseListItemBinding binding;

        public IViewHolder(CourseListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.followBtn.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    CourseAdapter.listener.onFollowButtonClick(position);
                }
            });
            binding.messageBtn.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    CourseAdapter.listener.onMessageButtonClick(position);
                }
            });
        }

        public void bind(Course course) {
            binding.setCourse(course);
        }
    }
}
