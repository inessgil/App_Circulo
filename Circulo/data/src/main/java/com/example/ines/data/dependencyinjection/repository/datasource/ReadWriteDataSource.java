package com.example.ines.data.dependencyinjection.repository.datasource;

import com.example.ines.domain.entities.Circulo;

import java.io.IOException;
import java.util.List;

public interface ReadWriteDataSource {

    List<String> getCirculosByDate (String date) throws IOException;
    List<String> getCirculosByName (String name) throws IOException;
    List<String> getCirculos (String date, String name) throws IOException;

    void newCirculo (String date, String name) throws IOException;

    void writeCirculo (String date, String name, String topic, String type, String content) throws IOException;

    Circulo getCirculo (String date, String name) throws IOException;

    void removeCirculo (String date, String name) throws IOException;
}
