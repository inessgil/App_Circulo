package com.example.ines.circulo.guion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.ines.domain.entities.Circulo;
import com.example.ines.domain.exception.ErrorBundle;
import com.example.ines.domain.interactor.DefaultCallback;
import com.example.ines.domain.interactor.circulo.EditTextInteractor;
import com.example.ines.domain.interactor.circulo.GetCirculoInteractor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Tipos de circulo:
 * - Estudiantes:
 *      1. Intro + comentario
 *      2. Plan de vida + norma
 *      3. Examen + Charla
 *      4. Final
 */

public class GuionPresenter {

    private final GuionView guionView;
    private final EditTextInteractor editTextInteractor;
    private final GetCirculoInteractor getCirculoInteractor;
    private String date;
    private String type;

    @Inject
    public GuionPresenter(GuionView guionView,
                          EditTextInteractor editTextInteractor,
                          GetCirculoInteractor getCirculoInteractor) {
        this.guionView = guionView;
        this.editTextInteractor = editTextInteractor;
        this.getCirculoInteractor = getCirculoInteractor;
    }

    public void setParameters (String date, String type) {
        this.date = date;
        this.type = type;
    }

    public void createNewGuion(String type) {
        List<GuionPart> list = new ArrayList<>();
        switch (type) {
            case ("estudiantes"):
                GuionPart part = new GuionPart();

                part.setTopic("comentario");
                part.setText("Intro");
                list.add(part);

                part = new GuionPart();
                part.setTopic("norma");
                part.setText("Plan de vida");
                list.add(part);

                part = new GuionPart();
                part.setTopic("charla");
                part.setText("Examen");
                list.add(part);

                part = new GuionPart();
                part.setTopic("tertulia");
                part.setText("Final");
                list.add(part);
                break;
        }
        guionView.printGuion(list);
    }

    public List<GuionPart> createGuion(String type, Circulo circulo) {
        List<GuionPart> list = new ArrayList<>();
        switch (type) {
            case ("estudiantes"):
                GuionPart part = new GuionPart();

                part.setTopic("comentario");
                part.setText("Intro");
                part.setEditable_text(circulo.getComentario());
                part.setImages(circulo.getImagenesComentario());
                part.setDocs(circulo.getDocComentario());
                list.add(part);

                part.setTopic("norma");
                part.setText("Plan de vida");
                part.setEditable_text(circulo.getNorma());
                part.setImages(circulo.getImagenesNorma());
                part.setDocs(circulo.getDocNorma());
                list.add(part);

                part.setTopic("charla");
                part.setText("Examen");
                part.setImages(circulo.getImagenesCharla());
                part.setDocs(circulo.getDocCharla());
                list.add(part);

                part.setTopic("tertulia");
                part.setText("Final");
                part.setImages(circulo.getImagenesTertulia());
                part.setDocs(circulo.getDocTertulia());
                list.add(part);
                break;
        }
        return list;
    }

    public void loadGuion(String date, final String type) {
        Map<String, String> map = new HashMap<>();
        map.put("date", date);
        map.put("name", type);
        getCirculoInteractor.execute(map, new DefaultCallback<Circulo>() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                guionView.showError(1);
            }

            @Override
            public void onSuccess(Circulo returnParam) {
                List<GuionPart> list = createGuion(type, returnParam);
                guionView.printGuion(list);
            }
        });
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
                    switch (adapterPosition) {
                        case 1:
                            return "comentario";
                        case 2:
                            return "norma";
                        case 3:
                            return "charla";
                        case 4:
                            return "tertulia";
                    }
                break;
        }
        return topic;
    }

    public Bitmap loadImage(String image) {
        File imgFile = new  File(image);
        if(imgFile.exists()){
            return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        else guionView.showError(1);
        return null;
    }
}
