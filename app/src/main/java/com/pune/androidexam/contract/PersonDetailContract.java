package com.pune.androidexam.contract;

import android.content.Context;

import com.pune.androidexam.model.Person;
import com.pune.androidexam.presenter.BasePresenter;
import com.pune.androidexam.view.BaseView;

public interface PersonDetailContract {
    interface Presenter extends BasePresenter {
        void onViewCreated();
    }

    interface View extends BaseView {
        Context getContext();
        void onLoadPerson(Person person);
    }
}
