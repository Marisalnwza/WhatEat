package com.example.whateat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.whateat.Model.Food;
import com.example.whateat.Util.AppExecutors;
import com.example.whateat.db.AppDatabase;

import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        Button saveButton = findViewById(R.id.addFoodButton); //เพิ่มชื่ออาหารลงในดาต้าเบส
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText foodnameEditText = findViewById(R.id.foodnameEditText);
                String foodName = foodnameEditText.getText().toString();;

                final Food food = new Food(0,foodName);

                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() { // worker thread
                        AppDatabase db = AppDatabase.getInstance(AddActivity.this);
                        db.foodDao().addFood(food);
                        finish();
                    }
                });
            }
        });
    }
}