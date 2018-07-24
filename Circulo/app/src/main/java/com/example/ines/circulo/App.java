package com.example.ines.circulo;


import android.app.Application;

import com.example.ines.circulo.dependencyinjection.application.ApplicationComponent;
import com.example.ines.circulo.dependencyinjection.application.ApplicationModule;
import com.example.ines.circulo.dependencyinjection.application.DaggerApplicationComponent;


public class App extends Application {

    ApplicationComponent component = null;

    public ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }
}
