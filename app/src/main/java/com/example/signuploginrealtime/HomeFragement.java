package com.example.signuploginrealtime;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragement extends Fragment {
    private TextView welcomeText;
    private ListView coursesListView;
    private CourseAdapter courseAdapter;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        welcomeText = view.findViewById(R.id.user_name);


        // Récupérer les arguments passés depuis l'activité
        Bundle bundle = getArguments();
        /*if (bundle != null) {
            String userName = bundle.getString("userName");
            if (userName != null) {
                welcomeText.setText("Welcome, " + userName);
            } else {
                welcomeText.setText("Welcome, User");
            }
        }*/
        coursesListView = view.findViewById(R.id.coursesListView);

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Interior Design", "25 courses", "https://cdn.usegalileo.ai/stability/5cb1a0ad-74cb-417f-8941-43aec18b6f38.png"));
        courses.add(new Course("Fashion Design", "22 courses", "https://cdn.usegalileo.ai/stability/7d0812c4-b42f-4ae7-9420-ee1bae90eacb.png"));
        courses.add(new Course("Product Design", "15 courses", "https://cdn.usegalileo.ai/stability/e16856cc-b9c1-41db-a153-831e6bf7ce9b.png"));
        courses.add(new Course("Product Design", "15 courses", "https://cdn.usegalileo.ai/stability/e16856cc-b9c1-41db-a153-831e6bf7ce9b.png"));
        courses.add(new Course("Product Design", "15 courses", "https://cdn.usegalileo.ai/stability/e16856cc-b9c1-41db-a153-831e6bf7ce9b.png"));
        courses.add(new Course("Product Design", "15 courses", "https://cdn.usegalileo.ai/stability/e16856cc-b9c1-41db-a153-831e6bf7ce9b.png"));
        courses.add(new Course("Product Design", "15 courses", "https://cdn.usegalileo.ai/stability/e16856cc-b9c1-41db-a153-831e6bf7ce9b.png"));


        courseAdapter = new CourseAdapter(getActivity(), R.layout.list_item_course, courses);
        coursesListView.setAdapter(courseAdapter);


        return view;
    }

}

