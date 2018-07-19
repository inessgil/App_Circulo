package com.example.ines.circulo.guion;

import com.example.ines.domain.exception.ErrorBundle;
import com.example.ines.domain.interactor.DefaultCallback;
import com.example.ines.domain.interactor.circulo.EditTextInteractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class GuionPresenter {

    private final GuionView guionView;
    private final EditTextInteractor editTextInteractor;
    private final String date;
    private final String type;

    @Inject
    public GuionPresenter(GuionView guionView, EditTextInteractor editTextInteractor, String date, String type) {
        this.guionView = guionView;
        this.editTextInteractor = editTextInteractor;
        this.date = date;
        this.type = type;
    }

    public List<GuionPart> createGuion(String type) {
        List<GuionPart> list = new ArrayList<>();
        switch (type) {
            case ("estudiantes"):

                break;
        }
        return list;
    }

    public List<GuionPart> loadGuion(String date, String type) {
        List<GuionPart> list = createGuion(type);
        //TODO: Access data to search for the circulo and load it
        return list;
    }

    public void saveText(String text, int adapterPosition) {
        String topic = getTopic(type, adapterPosition);
        Map<String, String> map = new HashMap<>();
        map.put("date", date);
        map.put("name", type);
        map.put("topic", topic);
        map.put("content", text);
        editTextInteractor.execute(map, new DefaultCallback<Void>() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                guionView.showError(1);
            }

            @Override
            public void onSuccess(Void returnParam) {
                guionView.showError(2);
            }
        });
    }
    //TODO: Get topics
    private String getTopic(String type, int adapterPosition) {
        String topic = null;
        switch (type) {
            case ("estudiantes"):

                break;
        }
        return topic;
    }
}
