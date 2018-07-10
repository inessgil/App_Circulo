package com.example.ines.circulo.dependencyinjection.application;


import android.content.Context;
import android.support.annotation.UiThread;

import com.example.ines.circulo.App;
import com.example.ines.circulo.UIThread;
import com.example.ines.data.dependencyinjection.dependencyinjection.DataModule;
import com.example.ines.data.dependencyinjection.dependencyinjection.ForApp;
import com.example.ines.data.dependencyinjection.executor.JobExecutor;
import com.example.ines.domain.executor.PostExecutionThread;
import com.example.ines.domain.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DataModule.class})
public class ApplicationModule {

    private final App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    @ForApp
    public Context providesContext(){
        return app;
    }

    @Provides
    @Singleton
    public ThreadExecutor provideThreadExecutor(JobExecutor executor) {
        return executor;
    }

    @Provides
    @Singleton
    public PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }
}
