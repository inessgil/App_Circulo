package com.example.ines.circulo.dependencyinjection.activity;

import com.example.ines.circulo.dependencyinjection.application.ViewModule;
import com.example.ines.circulo.dependencyinjection.scope.PerActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {ActivityModule.class, ViewModule.class})
public interface ActivityComponent {
}
