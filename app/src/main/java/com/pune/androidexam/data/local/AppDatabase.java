package com.pune.androidexam.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.pune.androidexam.data.local.converter.ContactPersonConverter;
import com.pune.androidexam.data.local.dao.PersonDao;
import com.pune.androidexam.model.Person;

@TypeConverters(ContactPersonConverter.class)
@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}
