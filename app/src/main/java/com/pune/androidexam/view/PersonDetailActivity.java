package com.pune.androidexam.view;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.pune.androidexam.R;
import com.pune.androidexam.contract.PersonDetailContract;
import com.pune.androidexam.model.Person;
import com.pune.androidexam.presenter.PersonDetailPresenter;

public class PersonDetailActivity extends AppCompatActivity implements PersonDetailContract.View {
    private static final String TAG = PersonDetailActivity.class.getSimpleName();

    private TextView tvName;
    private TextView tvBirthDate;
    private TextView tvEmail;
    private TextView tvMobileNumber;
    private TextView tvAddress;
    private TextView tvContactPersonName;
    private TextView tvContactPersonMobileNumber;

    private PersonDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvName = findViewById(R.id.person_detail_name);
        tvBirthDate = findViewById(R.id.person_detail_birth_date);
        tvEmail = findViewById(R.id.person_detail_email);
        tvMobileNumber = findViewById(R.id.person_detail_mobile_number);
        tvAddress = findViewById(R.id.person_detail_address);
        tvContactPersonName = findViewById(R.id.person_detail_contact_name);
        tvContactPersonMobileNumber = findViewById(R.id.person_detail_contact_mobile_number);

        setPresenter(new PersonDetailPresenter(PersonDetailActivity.this));
        presenter.onViewCreated();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(Object presenter) {
        this.presenter = (PersonDetailPresenter) presenter;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onLoadPerson(Person person) {
        if (person != null) {
            initPersonDetail(person);
        } else {
            Log.e(TAG, "Person is null");
        }
    }

    private void initPersonDetail (Person person) {
        tvName.setText(person.getName());
        tvBirthDate.setText(person.getFormattedBirthDate());
        tvEmail.setText(person.getEmailAddress());
        tvMobileNumber.setText(person.getMobileNumber());
        tvAddress.setText(person.getAddress());
        tvContactPersonName.setText(person.getContactPerson().getName());
        tvContactPersonMobileNumber.setText(person.getContactPerson().getMobileNumber());
    }
}
