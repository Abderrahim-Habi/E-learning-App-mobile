package com.example.signuploginrealtime;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CourseAdapter extends ArrayAdapter<Course> {
    private Context context;
    private int resource;
    private List<Course> courses;

    public CourseAdapter(Context context, int resource, List<Course> courses) {
        super(context, resource, courses);
        this.context = context;
        this.resource = resource;
        this.courses = courses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, parent, false);
        }

        Course course = getItem(position);

        if (course != null) {
            TextView titleTextView = view.findViewById(R.id.courseTitle);
            TextView subtitleTextView = view.findViewById(R.id.courseSubtitle);
            ImageView imageView = view.findViewById(R.id.courseImage);

            titleTextView.setText(course.getTitle());
            subtitleTextView.setText(course.getSubtitle());

            // Load image using Picasso
            Picasso.get().load(course.getImageUrl()).into(imageView);

            // Set click listener for the item
            view.setOnClickListener(v -> {
                // Affiche un toast pour la démonstration
                Toast.makeText(context, "Clicked on: " + course.getTitle(), Toast.LENGTH_SHORT).show();

                // Vérifie que le contexte est une activité qui supporte les fragments
                if (context instanceof FragmentActivity) {
                    FragmentActivity activity = (FragmentActivity) context;

                    // Crée un fragment de détails et passe les données
                    DetailsFragment detailsFragment = new DetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("courseTitle", course.getTitle());
                    bundle.putString("courseSubtitle", course.getSubtitle());
                    bundle.putString("courseImageUrl", course.getImageUrl());
                    detailsFragment.setArguments(bundle);

                    // Remplace le fragment actuel par le fragment de détails
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.welcomeLayout, detailsFragment)
                            .addToBackStack(null)
                            .commit();
                } else {
                    Toast.makeText(context, "Error: Context is not a FragmentActivity!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }
}
