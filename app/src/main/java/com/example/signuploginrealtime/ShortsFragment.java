package com.example.signuploginrealtime;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class ShortsFragment extends Fragment {
    private ListView comingSoonCoursesList;
    private CourseAdapter_Crud adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shorts, container, false);

        comingSoonCoursesList = view.findViewById(R.id.comingSoonCoursesList);

        // Create sample data for the "Coming Soon" courses
        ArrayList<Course_Crud> comingSoonCourses = new ArrayList<>();
        comingSoonCourses.add(new Course_Crud(1, "The Beatles: The Music, the Myth, the Legend", "By University of Rochester", "Music, Art and Culture", "https://cdn.usegalileo.ai/stability/0d139b1e-d64b-4c63-b64e-82eeeaa60cf0.png", ""));


        // Set up the adapter
        adapter = new CourseAdapter_Crud(getActivity(), comingSoonCourses);
        comingSoonCoursesList.setAdapter(adapter);

        return view;
    }
}
