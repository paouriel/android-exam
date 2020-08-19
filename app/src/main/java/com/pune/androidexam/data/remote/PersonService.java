package com.pune.androidexam.data.remote;

import com.pune.androidexam.model.Person;
import com.pune.androidexam.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PersonService {
    @GET(Constants.URL_BUCKET)
    Call<List<Person>> loadPersons();
}
