package com.example.ines.data.dependencyinjection;

import android.content.Context;

import com.example.ines.data.repository.CirculoDataRepository;
import com.example.ines.data.repository.datasource.ReadWriteDataSource;
import com.example.ines.data.repository.datasource.local.StorageDataSource;
import com.example.ines.domain.CirculoRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    public CirculoRepository providesCirculoRepository (CirculoDataRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    public ReadWriteDataSource providesReadWriteDataSource (StorageDataSource dataSource) {
        return dataSource;
    }
    @Provides
    public Context providesContext (@ForApp Context context) {
        return context;
    }
}
