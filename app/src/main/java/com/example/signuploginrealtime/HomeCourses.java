package com.example.signuploginrealtime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.annotation.SuppressLint;

import com.example.signuploginrealtime.databinding.ActivityHomeCoursesBinding;

public class HomeCourses extends AppCompatActivity {
    ActivityHomeCoursesBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeCoursesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Charge le fragment initial
        // Récupérer les données passées depuis LoginActivity
        String userName = getIntent().getStringExtra("name");
        String userEmail = getIntent().getStringExtra("email");

        // Afficher ces données dans le fragment ou dans la vue
        // Exemple : passer les données au fragment
        HomeFragement fragment = new HomeFragement();
        Bundle bundle = new Bundle();
        bundle.putString("userName", userName);
        bundle.putString("userEmail", userEmail);
        fragment.setArguments(bundle);
        replaceFragment(fragment,bundle);

        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            bundle.putString("userName", getIntent().getStringExtra("name"));
            bundle.putString("userEmail", getIntent().getStringExtra("email"));

            if (itemId == R.id.home) {
                replaceFragment(new HomeFragement(), bundle);
            } else if (itemId == R.id.shorts) {
                replaceFragment(new ShortsFragment(), null);
            } else if (itemId == R.id.subscriptions) {
                replaceFragment(new SubscriptionFragment(), null);
            } else if (itemId == R.id.library) {
                replaceFragment(new ProfileFragment(), null);
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment, Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Si le bundle est non nul, ajoutez-le au fragment
        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}
