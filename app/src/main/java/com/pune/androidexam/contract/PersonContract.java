package com.pune.androidexam.contract;

import com.pune.androidexam.model.Person;
import com.pune.androidexam.presenter.BasePresenter;
import com.pune.androidexam.view.BaseView;

import java.util.List;

public interface PersonContract {
    interface Presenter extends BasePresenter {
        void onViewCreated();
        void loadList();
    }

    interface View extends BaseView {
        void onLoadPersonsToList(List<Person> persons, Boolean isRemote);
        void onErrorLoadingPersons(Throwable error);
    }
}
