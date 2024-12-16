package com.example.signuploginrealtime;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class HomeFragement extends Fragment {
    private TextView welcomeText;
    private ListView coursesListView;
    private CourseAdapter_Crud courseAdapter;
    private DataBaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        welcomeText = view.findViewById(R.id.user_name);
        coursesListView = view.findViewById(R.id.coursesListView);

        // Initialize the database helper
        dbHelper = new DataBaseHelper(getActivity());

        // Retrieve courses from the database
        ArrayList<Course_Crud> courses = getCoursesFromDatabase();

        // Set up the adapter with the data
        courseAdapter = new CourseAdapter_Crud(getActivity(), courses);
        coursesListView.setAdapter(courseAdapter);

        // Optionally set the welcome message
        /*Bundle bundle = getArguments();
        if (bundle != null) {
            String userName = bundle.getString("userName");
            if (userName != null) {
                welcomeText.setText("Welcome, " + userName);
            } else {
                welcomeText.setText("Welcome, User");
            }
        }*/

        // Set item click listener
        coursesListView.setOnItemClickListener((parent, view1, position, id) -> {
            Course_Crud selectedCourse = courseAdapter.getItem(position);

            // Create a new instance of DetailsFragment and pass the data
            DetailsFragment detailsFragment = new DetailsFragment();
            Bundle courseBundle = new Bundle();
            courseBundle.putString("courseTitle", selectedCourse.getTitle());
            courseBundle.putString("courseCategory", selectedCourse.getCategory());
            courseBundle.putString("courseDescription", selectedCourse.getDescription());
            courseBundle.putString("courseImageUrl", selectedCourse.getImage());
            courseBundle.putString("youtubeUrl",selectedCourse.getYoutubeUrl());
            detailsFragment.setArguments(courseBundle);

            // Replace the current fragment with DetailsFragment
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.welcomeLayout, detailsFragment);
            transaction.addToBackStack(null); // Add to back stack to enable navigation back
            transaction.commit();
        });

        return view;
    }

    private ArrayList<Course_Crud> getCoursesFromDatabase() {
        ArrayList<Course_Crud> courses = new ArrayList<>();
        Cursor cursor = dbHelper.getAllCourses(); // Assuming this method returns a Cursor with course data

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                // Extract data from the Cursor
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_ID));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_TITLE));
                @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_CATEGORY));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_DESCRIPTION));
                @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_IMAGE));
                @SuppressLint("Range") String youtubeUrl = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_VIDEO_YOUTUBE));

                // Create a new Course_Crud object and add it to the list
                courses.add(new Course_Crud(id, title, category, description, image, youtubeUrl));
            }
            cursor.close();
        } else {
            Toast.makeText(getActivity(), "No courses found!", Toast.LENGTH_SHORT).show();
        }

        return courses;
    }
}
