package com.pune.androidexam.presenter;

import android.content.Context;
import android.content.Intent;

import androidx.room.Room;

import com.pune.androidexam.contract.PersonDetailContract;
import com.pune.androidexam.data.local.AppDatabase;
import com.pune.androidexam.model.Person;


import static com.pune.androidexam.util.Constants.PERSON_ID;
import static com.pune.androidexam.util.Constants.PERSON_TABLE;

public class PersonDetailPresenter implements PersonDetailContract.Presenter {

    private PersonDetailContract.View view;
    private Context context;
    private Intent intent;

    public PersonDetailPresenter(PersonDetailContract.View view) {
        this.view = view;
        this.context = view.getContext();
        this.intent = view.getIntent();
    }

    @Override
    public void onViewCreated() {
        loadPerson();
    }

    @Override
    public void onDestroy() {
        if (view != null) view = null;
    }

    private void loadPerson () {
        int personId = intent.getIntExtra(PERSON_ID, 0);
        AppDatabase appDatabase = Room.databaseBuilder(context, AppDatabase.class, PERSON_TABLE).allowMainThreadQueries().build();
        Person person = appDatabase.personDao().findById(personId);
        view.onLoadPerson(person);
    }
}
