package com.example.whateat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.whateat.Model.Food;
import com.example.whateat.Util.AppExecutors;
import com.example.whateat.adapter.FoodAdapter;
import com.example.whateat.db.AppDatabase;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        mRecyclerView = findViewById(R.id.food_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(FoodActivity.this));

        Button addFoodButton = findViewById(R.id.addFoodButton); //ปุ่มเพิ่มอาหาร
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });


    }

    private static final String TAG = FoodActivity.class.getName();

    private RecyclerView mRecyclerView;

    @Override
    protected void onResume() {
        super.onResume();

        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(FoodActivity.this);
                final Food[] foods = db.foodDao().getAllFoods(); //แสดงข้อมูลทั้งหมดในดาต้าเบส

                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        FoodAdapter adapter = new FoodAdapter(FoodActivity.this, foods);
                        mRecyclerView.setAdapter(adapter);
                    }
                });


            }
        });
    }
}