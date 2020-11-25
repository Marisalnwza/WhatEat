package com.example.whateat.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.whateat.Model.Food;

@Dao
public interface FoodDao {
    @Query("SELECT * FROM foods")
    Food[] getAllFoods();

    @Query("SELECT * FROM foods WHERE id = :id")
    Food getFoodById(int id);

    @Insert
    void addFood(Food... foods);

    @Delete
    void deleteFood(Food foods);
}
