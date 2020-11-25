package com.example.whateat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.whateat.Model.Food;
import com.example.whateat.Util.AppExecutors;
import com.example.whateat.adapter.FoodAdapter;
import com.example.whateat.db.AppDatabase;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random r = new Random(); //สำหรับการสุ่มตัวเลข
    int random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button randomButton = findViewById(R.id.randomButton); //ปุ่มสุ่มอาหาร
        randomButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                final AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                        final Food[] allFood = db.foodDao().getAllFoods();
                        random = r.nextInt(allFood.length); //สุ่มตัวเลขตามจำนวนข้อมูลดาต้าเบส
                        Food food = db.foodDao().getFoodById(random); //get ID โดยทำการสุ่มตัวเลข ID
                        final String message = String.format(food.foodName);
                        executors.mainThread().execute(new Runnable() {
                            @Override
                            public void run() { // main thread
                                new AlertDialog.Builder(MainActivity.this)
                                        .setMessage(message)
                                        .setPositiveButton("กินอันนี้แหละ", null)
                                        .setNegativeButton("ไม่กินอ่ะ สุ่มใหม่",new DialogInterface.OnClickListener(){
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //ทำการสุ่มเลขใหม่
                                                random = r.nextInt(allFood.length);
                                            }
                                        })
                                        .show();
                            }
                        });
                    }

                });


//                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
//                dialog.setTitle("กินอะไรดีนะ");
//                dialog.setMessage(foodRandom);
//                dialog.setPositiveButton("กินอันนี้แหละ", null);
//
//                dialog.setNegativeButton("ไม่กินน่ะ สุ่มใหม่", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //todo: ทำการสุ่มเลขใหม่
//
//
//                    }
//                });
//                dialog.show();
            }
        });

        Button listButton = findViewById(R.id.listButton);//ปุ่มไปยังหน้ารายการอาหาร
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FoodActivity.class);
                startActivity(intent);
            }
        });

    }
}