package com.example.signuploginrealtime;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {
    private TextView titleTextView;
    private TextView subtitleTextView;
    private ImageView courseImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        titleTextView = view.findViewById(R.id.detailsTitle);
        subtitleTextView = view.findViewById(R.id.detailsSubtitle);
        courseImageView = view.findViewById(R.id.detailsImage);

        // Récupérer les arguments passés depuis l'adaptateur
        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("courseTitle");
            String subtitle = bundle.getString("courseSubtitle");
            String imageUrl = bundle.getString("courseImageUrl");

            titleTextView.setText(title);
            subtitleTextView.setText(subtitle);
            // Charger l'image avec Picasso
            Picasso.get().load(imageUrl).into(courseImageView);
        }

        return view;
    }
}
