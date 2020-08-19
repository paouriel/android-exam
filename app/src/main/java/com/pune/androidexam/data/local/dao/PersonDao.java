package com.pune.androidexam.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.pune.androidexam.model.Person;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM persons")
    List<Person> getAll();

    @Query("SELECT * FROM persons WHERE id = :id")
    Person findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Person> persons);
}
