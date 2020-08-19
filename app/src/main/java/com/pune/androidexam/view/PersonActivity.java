package com.pune.androidexam.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.pune.androidexam.R;
import com.pune.androidexam.adapter.PersonAdapter;
import com.pune.androidexam.contract.PersonContract;
import com.pune.androidexam.model.Person;
import com.pune.androidexam.presenter.PersonPresenter;

import java.util.List;

import static com.pune.androidexam.util.Constants.LOADED_CACHE;
import static com.pune.androidexam.util.Constants.LOADED_REMOTE;
import static com.pune.androidexam.util.Constants.PERSON_ID;

public class PersonActivity extends AppCompatActivity implements PersonContract.View, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = PersonActivity.class.getSimpleName();

    private RecyclerView recyclerViewPersons;
    private SwipeRefreshLayout swipeRefreshPersonList;

    private PersonContract.Presenter presenter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        initViews();
        setPresenter(new PersonPresenter(PersonActivity.this));
        presenter.onViewCreated();
    }

    @Override
    public void setPresenter(Object presenter) {
        this.presenter = (PersonPresenter) presenter;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onLoadPersonsToList(List<Person> persons, Boolean isRemote) {
        recyclerViewPersons.setAdapter(new PersonAdapter(persons, person -> {
            Intent intent = new Intent(getApplicationContext(), PersonDetailActivity.class);
            intent.putExtra(PERSON_ID, person.getId());
            startActivity(intent);
        }));
        swipeRefreshPersonList.setRefreshing(false);
        Log.i(TAG, isRemote ? LOADED_REMOTE: LOADED_CACHE);
    }

    @Override
    public void onErrorLoadingPersons(Throwable error) {
        swipeRefreshPersonList.setRefreshing(false);
        Log.d(TAG, error.getLocalizedMessage());
    }

    @Override
    public void onRefresh() {
        presenter.loadList();
    }

    public void initViews () {
        swipeRefreshPersonList = findViewById(R.id.person_refresh);
        swipeRefreshPersonList.setOnRefreshListener(this);
        swipeRefreshPersonList.setRefreshing(true);
        recyclerViewPersons = findViewById(R.id.person_list);
        recyclerViewPersons.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
