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

    void getCirculosByDate(String date, GetCirculosByDateCallback callback);
    void getCirculosByName(String name, GetCirculosByNameCallback callback);
    void getCirculos(String date, String name, GetCirculosCallback callback);

    void getCirculo(String date, String name, GetCirculoCallback callback);

    void addImage(String date, String name, String topic, String content, AddImageCallback callback);
    void addDoc(String date, String name, String topic, String content, AddDocCallback callback);
    void editText(String date, String name, String topic, String content, EditTextCallback callback);
    void removeImage(String date, String name, String topic, String content, RemoveImageCallback callback);
    void removeDoc(String date, String name, String topic, String content, RemoveDocCallback callback);
    void removeCirculo(String date, String name, RemoveCirculoCallback callback);
}
