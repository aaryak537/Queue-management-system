package com.example.flowq;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Button btnNext;
    private LinearLayout dotsContainer;
    private final int pageCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        btnNext = findViewById(R.id.btnNext);
        dotsContainer = findViewById(R.id.dotsContainer);
        TextView btnSkip = findViewById(R.id.btnSkip);

        viewPager.setAdapter(new OnboardingAdapter(this));
        createDots(0);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem();
                if (current < pageCount - 1) {
                    viewPager.setCurrentItem(current + 1, true);
                } else {
                    openLogin();
                }
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                createDots(position);
                btnNext.setText(position == pageCount - 1 ? "Get Started  →" : "Continue");
            }
        });
    }

    private void createDots(int selected) {
        dotsContainer.removeAllViews();
        for (int i = 0; i < pageCount; i++) {
            View dot = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    i == selected ? 24 : 8, 8
            );
            params.setMargins(5, 0, 5, 0);
            dot.setLayoutParams(params);
            dot.setBackgroundColor(i == selected ? Color.rgb(74, 92, 255) : Color.rgb(205, 210, 230));
            dotsContainer.addView(dot);
        }
    }

    private void openLogin() {
        getSharedPreferences("FlowQPrefs", MODE_PRIVATE)
                .edit().putBoolean("onboarding_seen", true).apply();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
