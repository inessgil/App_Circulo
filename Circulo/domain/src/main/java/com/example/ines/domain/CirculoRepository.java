package com.example.ines.domain;


import com.example.ines.domain.entities.Circulo;
import com.example.ines.domain.interactor.DefaultCallback;

import java.util.List;

public interface CirculoRepository {

    interface GetCirculosByDateCallback extends DefaultCallback<List<String>> {}
    interface GetCirculosByNameCallback extends DefaultCallback<List<String>> {}
    interface GetCirculosCallback extends DefaultCallback<List<String>> {}

    interface GetCirculoCallback extends DefaultCallback<Circulo> {}

    interface AddImageCallback extends DefaultCallback<Void> {}
    interface AddDocCallback extends DefaultCallback<Void> {}
    interface EditTextCallback extends DefaultCallback<Void> {}
    interface RemoveImageCallback extends DefaultCallback<Void> {}
    interface RemoveDocCallback extends DefaultCallback<Void> {}
    interface RemoveCirculoCallback extends DefaultCallback<Void> {}

    void GetCirculosByDate (String date, GetCirculosByDateCallback callback);
    void GetCirculosByName (String name, GetCirculosByNameCallback callback);
    void GetCirculos (String date, String name, GetCirculosCallback callback);

    void GetCirculo (String date, String name, GetCirculoCallback callback);

    void AddImage (String date, String name, String topic, String content, AddImageCallback callback);
    void AddDoc (String date, String name, String topic, String content, AddDocCallback callback);
    void EditText (String date, String name, String topic, String content, EditTextCallback callback);
    void RemoveImage (String date, String name, String topic, String content, RemoveImageCallback callback);
    void RemoveDoc (String date, String name, String topic, String content, RemoveDocCallback callback);
    void RemoveCirculo (String date, String name, RemoveCirculoCallback callback);
}
