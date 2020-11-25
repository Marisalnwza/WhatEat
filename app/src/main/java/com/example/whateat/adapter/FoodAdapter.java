package com.example.whateat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whateat.Model.Food;
import com.example.whateat.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder>{
    private Context mContext;
    private Food[] mFoods;

    public FoodAdapter(Context context, Food[] foods) {
        this.mContext = context;
        this.mFoods = foods;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Food food = mFoods[position];

        holder.foodtextView.setText(food.foodName);

    }

    @Override
    public int getItemCount() {
        return mFoods.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView foodtextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.foodtextView = itemView.findViewById(R.id.foodtextView);
        }
    }
}
