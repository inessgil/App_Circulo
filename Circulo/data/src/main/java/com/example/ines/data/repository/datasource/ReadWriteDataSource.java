package com.example.ines.data.repository.datasource;

import com.example.ines.domain.entities.Circulo;

import java.io.IOException;
import java.util.List;

public interface ReadWriteDataSource {

    List<String> getCirculos () throws IOException;
    void saveCirculo (String date, String name, String content) throws IOException;

    void newCirculo (String date, String name) throws IOException;
    Boolean existsCirculo (String date, String name) throws IOException;

    void editText (String date, String name, String topic, String text) throws IOException;

    String getCirculo (String date, String name) throws IOException;

    void removeCirculo (String date, String name) throws IOException;
}
