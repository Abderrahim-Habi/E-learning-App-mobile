package com.example.signuploginrealtime;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private TextView profileName, profileEmail, profileUsername, editProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Lier les vues
        profileName = view.findViewById(R.id.profileName);
        profileEmail = view.findViewById(R.id.profileEmail);
        profileUsername = view.findViewById(R.id.profileUsername); // Assurez-vous que cet ID existe dans votre layout
        editProfile = view.findViewById(R.id.editButton);

        // Récupérer les données du Bundle
        if (getArguments() != null) {
            String userName = getArguments().getString("userName");
            String userEmail = getArguments().getString("userEmail");

            // Mettre à jour les vues
            profileName.setText(userName);
            profileEmail.setText(userEmail);
            profileUsername.setText(userName); // Ajoutez ceci si `profileUsername` correspond à `userName`
        }

        // Listener pour le bouton "Edit Profile"
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }
        });


        return view;
    }

    public void passUserData() {
        String userUsername = profileUsername.getText().toString().trim();

        if (userUsername.isEmpty()) {
            Toast.makeText(getActivity(), "Nom d'utilisateur manquant", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    // Obtenez les données de la base de données
                    DataSnapshot userSnapshot = snapshot.getChildren().iterator().next();
                    String nameFromDB = userSnapshot.child("name").getValue(String.class);
                    String emailFromDB = userSnapshot.child("email").getValue(String.class);
                    String usernameFromDB = userSnapshot.child("username").getValue(String.class);
                    String passwordFromDB = userSnapshot.child("password").getValue(String.class);

                    // Démarrez l'activité EditProfileActivity
                    Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                    intent.putExtra("name", nameFromDB);
                    intent.putExtra("email", emailFromDB);
                    intent.putExtra("username", usernameFromDB);
                    intent.putExtra("password", passwordFromDB);

                    startActivity(intent);
                } else {
                    // Si l'utilisateur n'existe pas
                    Toast.makeText(getActivity(), "Utilisateur introuvable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Gérer les erreurs de Firebase
                Toast.makeText(getActivity(), "Erreur de la base de données : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
