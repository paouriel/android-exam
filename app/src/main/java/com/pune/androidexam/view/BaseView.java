package com.pune.androidexam.view;

import android.content.Context;
import android.content.Intent;

public interface BaseView<T> {
    Context getContext();
    Intent getIntent();
    void setPresenter(T presenter);
}
