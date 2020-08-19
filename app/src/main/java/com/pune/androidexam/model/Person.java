package com.pune.androidexam.model;

import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "persons")
public class Person {
    private static final String TAG = Person.class.getSimpleName();

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    private String firstName = "";

    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    private String lastName = "";

    @ColumnInfo(name = "birthdate")
    @SerializedName("birthdate")
    private String birthDate = "";

    @ColumnInfo(name = "mobile_number")
    @SerializedName("mobile_number")
    private String mobileNumber = "";

    @ColumnInfo(name = "email_address")
    @SerializedName("email_address")
    private String emailAddress = "";

    @ColumnInfo(name = "address")
    @SerializedName("address")
    private String address = "";

    @ColumnInfo(name = "contact_person")
    @SerializedName("contact_person")
    private ContactPerson contactPerson = null;

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

    public String getFormattedBirthDate () {
        SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy", Locale.US);
        Date date = null;
        try {
            date = df.parse(birthDate);
        } catch (ParseException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }

        if (date != null) {
            SimpleDateFormat dfWorded = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
            return dfWorded.format(date);
        }

        return birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }
}
