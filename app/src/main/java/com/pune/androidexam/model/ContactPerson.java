package com.pune.androidexam.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "contact_persons")
public class ContactPerson {
    @PrimaryKey
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    private String firstName = "";

    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    private String lastName = "";

    @ColumnInfo(name = "mobile_number")
    @SerializedName("mobile_number")
    private String mobileNumber = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
