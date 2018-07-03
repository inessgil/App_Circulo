package com.example.ines.domain.interactor;


import com.example.ines.domain.exception.ErrorBundle;

public interface DefaultCallback<T> {

    void onError (ErrorBundle errorBundle);
    void onSuccess (T returnParam);
}
