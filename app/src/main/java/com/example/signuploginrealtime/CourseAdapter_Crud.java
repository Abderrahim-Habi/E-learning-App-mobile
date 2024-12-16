package com.example.signuploginrealtime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class CourseAdapter_Crud extends ArrayAdapter<Course_Crud> {
    public CourseAdapter_Crud(Context context, ArrayList<Course_Crud> courses) {
        super(context, 0, courses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_course, parent, false);
        }

        Course_Crud course = getItem(position);

        TextView titleTextView = convertView.findViewById(R.id.courseTitle);
        TextView categoryTextView = convertView.findViewById(R.id.courseCategory);
        ImageView courseImageView = convertView.findViewById(R.id.courseImage);
        Button updateButton = convertView.findViewById(R.id.updateButton);

        titleTextView.setText(course.getTitle());
        categoryTextView.setText(course.getCategory());
        Picasso.get().load(course.getImage()).into(courseImageView);


        return convertView;
    }
}
