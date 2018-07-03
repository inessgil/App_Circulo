package com.example.ines.circulo.dependencyinjection.activity;

import android.app.Activity;
import android.content.Context;

import com.example.ines.circulo.dependencyinjection.qualifier.ForActivity;
import com.example.ines.circulo.dependencyinjection.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    @PerActivity
    @ForActivity
    Context providesContext(){
        return activity;
    }
}
