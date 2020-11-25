package com.example.whateat.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "foods")
public class Food {

    @PrimaryKey(autoGenerate = true)
    public final int id;

    @ColumnInfo(name = "food_name")
    public final String foodName;

    public Food(int id, String foodName) {
        this.id = id;
        this.foodName = foodName;

    }
}
