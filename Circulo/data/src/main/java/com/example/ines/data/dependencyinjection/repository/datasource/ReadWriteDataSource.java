package com.example.ines.data.dependencyinjection.repository.datasource;

import com.example.ines.domain.entities.Circulo;

import java.io.IOException;
import java.util.List;

public interface ReadWriteDataSource {

    List<String> getCirculosByDate (String date) throws IOException;
    List<String> getCirculosByName (String name) throws IOException;
    List<String> getCirculos (String date, String name) throws IOException;

    void newCirculo (String date, String name) throws IOException;

    void editText (String date, String name, String topic, String text) throws IOException;
    void addImage (String date, String name, String topic, String dir) throws IOException;
    void addDoc (String date, String name, String topic, String dir) throws IOException;

    void deleteImage (String date, String name, String topic, String dir) throws IOException;
    void deleteDoc (String date, String name, String topic, String dir) throws IOException;

    Circulo getCirculo (String date, String name) throws IOException;

    void removeCirculo (String date, String name) throws IOException;
}
