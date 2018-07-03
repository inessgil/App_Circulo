package com.example.ines.circulo.dependencyinjection.application;


import com.example.ines.circulo.App;
import com.example.ines.circulo.dependencyinjection.activity.ActivityComponent;
import com.example.ines.circulo.dependencyinjection.activity.ActivityModule;
import com.example.ines.circulo.dependencyinjection.fragment.FragmentComponent;
import com.example.ines.circulo.dependencyinjection.fragment.FragmentModule;
import com.example.ines.circulo.dependencyinjection.fragment.FragmentViewModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(App application);

    ActivityComponent plus(ActivityModule activityModule, ViewModule viewModule);
    FragmentComponent plus(FragmentModule fragmentModule, FragmentViewModule fragmentViewModule);
}
