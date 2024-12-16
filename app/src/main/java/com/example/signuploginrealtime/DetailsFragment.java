package com.example.signuploginrealtime;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {
    private TextView titleTextView;
    private TextView subtitleTextView;
    private TextView descriptionTextView;
    private ImageView courseImageView;
    private Button enroll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        titleTextView = view.findViewById(R.id.detailsTitle);
        subtitleTextView = view.findViewById(R.id.detailsSubtitle);
        descriptionTextView = view.findViewById(R.id.courseDescription);
        courseImageView = view.findViewById(R.id.detailsImage);
        enroll = view.findViewById(R.id.enrollButton);

        // Retrieve arguments passed from the adapter
        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("courseTitle");
            String category = bundle.getString("courseCategory");
            String description = bundle.getString("courseDescription");
            String imageUrl = bundle.getString("courseImageUrl");
            String YoutubeUrl = bundle.getString("youtubeUrl");

            // Convertir l'URL YouTube en format embed si nécessaire
            if (YoutubeUrl != null && YoutubeUrl.contains("watch?v=")) {
                YoutubeUrl = YoutubeUrl.replace("watch?v=", "embed/");
            }
            YoutubeUrl = YoutubeUrl + "?autoplay=1&controls=1";
            WebView webView = view.findViewById(R.id.webView);
            // Activer JavaScript pour que YouTube fonctionne correctement
            webView.getSettings().setJavaScriptEnabled(true);
            // Définir un client Web pour gérer le contenu multimédia
            webView.setWebChromeClient(new WebChromeClient());
            // Charger l'URL formatée
            if (YoutubeUrl != null) {
                webView.loadUrl(YoutubeUrl);
            }

            // Remplir les autres éléments de l'interface
            titleTextView.setText(title);
            subtitleTextView.setText(category);
            descriptionTextView.setText(description);

            // Charger l'image avec Picasso
            if (imageUrl != null) {
                Picasso.get().load(imageUrl).into(courseImageView);
            }
        }


        return view;
    }
}
