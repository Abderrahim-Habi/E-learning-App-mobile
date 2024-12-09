package com.example.signuploginrealtime;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {
    private TextView titleTextView;
    private TextView subtitleTextView;
    private TextView descriptionTextView;
    private ImageView courseImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        titleTextView = view.findViewById(R.id.detailsTitle);
        subtitleTextView = view.findViewById(R.id.detailsSubtitle);
        descriptionTextView = view.findViewById(R.id.detailsDescription);
        courseImageView = view.findViewById(R.id.detailsImage);

        // Retrieve arguments passed from the adapter
        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("courseTitle");
            String category = bundle.getString("courseCategory");
            String description = bundle.getString("courseDescription");
            String imageUrl = bundle.getString("courseImageUrl");

            titleTextView.setText(title);
            subtitleTextView.setText(category);
            descriptionTextView.setText(description);
            Picasso.get().load(imageUrl).into(courseImageView);
        }

        return view;
    }
}
