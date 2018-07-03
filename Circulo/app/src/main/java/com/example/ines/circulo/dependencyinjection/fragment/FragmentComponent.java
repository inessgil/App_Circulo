package com.example.ines.circulo.dependencyinjection.fragment;

import com.example.ines.circulo.dependencyinjection.scope.PerFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = {FragmentModule.class, FragmentViewModule.class})
public interface FragmentComponent {
}
