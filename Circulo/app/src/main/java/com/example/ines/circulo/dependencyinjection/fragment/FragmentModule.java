package com.example.ines.circulo.dependencyinjection.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.ines.circulo.dependencyinjection.qualifier.ForFragmentActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment){
        this.fragment = fragment;
    }

    @ForFragmentActivity
    @Provides
    public Context providesContext () {
        return fragment.getActivity();
    }
}
