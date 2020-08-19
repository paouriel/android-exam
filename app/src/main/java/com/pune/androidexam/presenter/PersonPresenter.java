package com.pune.androidexam.presenter;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.pune.androidexam.contract.PersonContract;
import com.pune.androidexam.data.local.AppDatabase;
import com.pune.androidexam.data.remote.PersonService;
import com.pune.androidexam.data.remote.RetrofitClient;
import com.pune.androidexam.model.Person;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonPresenter implements PersonContract.Presenter {
    private static final String TAG = PersonPresenter.class.getSimpleName();

    private PersonContract.View view;
    private Context context;
    private AppDatabase db;

    public PersonPresenter(PersonContract.View view) {
        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public void onViewCreated() {
        loadPersons(false);
    }

    @Override
    public void loadList() {
        if (view != null) {
            loadPersons(true);
        }
    }

    @Override
    public void onDestroy() {
        if (view != null) view = null;
    }

    private void loadPersons (Boolean isRemote) {
        if (view != null) {
            if (isRemote) {
                loadPersonsFromRemote();
            } else {
                db = Room.databaseBuilder(context, AppDatabase.class, "persons").allowMainThreadQueries().build();
                List<Person> persons = db.personDao().getAll();
                if (persons.size() == 0) {
                    loadPersonsFromRemote();
                } else {
                    view.onLoadPersonsToList(persons, false);
                }
            }
        } else {
            Log.e(TAG, "View not available");
        }
    }

    private void loadPersonsFromRemote () {
        PersonService service =  RetrofitClient.getInstance().create(PersonService.class);
        service.loadPersons().enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(@NotNull Call<List<Person>> call, @NotNull Response<List<Person>> response) {
                if (response.isSuccessful()) {
                    List<Person> persons =  response.body();
                    if (persons != null) {
                        db.personDao().insertAll(persons);
                        view.onLoadPersonsToList(persons, true);
                    }
                } else {
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Person>> call, @NotNull Throwable t) {
                view.onErrorLoadingPersons(t);
            }
        });
    }
}
