package com.pune.androidexam.data.local.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.pune.androidexam.model.ContactPerson;

public class ContactPersonConverter {
    @TypeConverter
    public String contactPersonToString(ContactPerson contactPerson) {
        return new Gson().toJson(contactPerson);
    }

    @TypeConverter
    public ContactPerson stringToContactPerson(String contactPerson) {
        return new Gson().fromJson(contactPerson, ContactPerson.class);
    }
}
