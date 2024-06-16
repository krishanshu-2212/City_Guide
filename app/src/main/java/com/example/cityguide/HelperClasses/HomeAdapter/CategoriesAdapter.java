package com.example.cityguide.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguide.R;

import java.util.ArrayList;
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.AdapterAllCategoriesViewHolder> {
    ArrayList<CategoriesHelperClass> mostViewedLocations;
    public CategoriesAdapter(ArrayList<CategoriesHelperClass> mostViewedLocations) {
        this.mostViewedLocations = mostViewedLocations;
    }

    @NonNull
    @Override
    public AdapterAllCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card_design, parent, false);
        return new AdapterAllCategoriesViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterAllCategoriesViewHolder holder, int position) {
        CategoriesHelperClass helperClass = mostViewedLocations.get(position);
        holder.imageView.setImageResource(helperClass.getImage());
        holder.textView.setText(helperClass.getTitle());
        holder.backgroundGradient.setBackground(helperClass.getbackgroundGradient());

    }
    @Override
    public int getItemCount() {

        return mostViewedLocations.size();
    }
    public static class AdapterAllCategoriesViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout backgroundGradient;
        ImageView imageView;
        TextView textView;
        public AdapterAllCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            imageView = itemView.findViewById(R.id.c_image);
            textView = itemView.findViewById(R.id.c_text);
            backgroundGradient = itemView.findViewById(R.id.c_relativeLayout);
        }
    }
}