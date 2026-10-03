package com.example.flowq;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.PageHolder> {

    private final android.content.Context context;

    public OnboardingAdapter(android.content.Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @NonNull
    @Override
    public PageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = viewType == 0 ? R.layout.item_onboarding_1 :
                viewType == 1 ? R.layout.item_onboarding_2 : R.layout.item_onboarding_3;
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        return new PageHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull PageHolder holder, int position) {
    }

    static class PageHolder extends RecyclerView.ViewHolder {
        PageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
