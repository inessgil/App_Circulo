package com.example.ines.domain.interactor;


import com.example.ines.domain.exception.ErrorBundle;
import com.example.ines.domain.executor.PostExecutionThread;

public class BaseUseCase<T> {
    private final PostExecutionThread postExecutionThread;

    public BaseUseCase(PostExecutionThread postExecutionThread) {
        this.postExecutionThread = postExecutionThread;
    }

    public void notifyOnError(final ErrorBundle errorBundle, final DefaultCallback<T> callback) {
        postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(errorBundle);
            }
        });
    }

    public void notifyOnSuccess(final T param, final DefaultCallback<T> callback) {
        postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(param);
            }
        });
    }
}
