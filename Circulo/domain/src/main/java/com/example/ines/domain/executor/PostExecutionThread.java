package com.example.ines.domain.executor;


public interface PostExecutionThread {
    void post(Runnable runnable);
}
