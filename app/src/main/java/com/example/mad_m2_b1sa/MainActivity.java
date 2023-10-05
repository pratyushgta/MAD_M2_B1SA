package com.example.mad_m2_b1sa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.mad_m2_b1sa.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selected = null;
            if (item.getItemId() == R.id.nav_home) {
                selected = new HomeFragment();
            } else if (item.getItemId() == R.id.nav_book) {
                selected = new BookFragment();
            }

            if (selected != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, selected).commit();
            }
            return true;
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout, new HomeFragment()).commit();
    }
}