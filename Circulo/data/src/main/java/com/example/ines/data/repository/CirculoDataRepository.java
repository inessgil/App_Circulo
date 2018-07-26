package com.example.ines.data.repository;


import android.content.Context;

import com.example.ines.data.dependencyinjection.ForApp;
import com.example.ines.data.repository.datasource.ReadWriteDataSource;
import com.example.ines.domain.CirculoRepository;
import com.example.ines.domain.entities.Circulo;
import com.example.ines.domain.exception.ErrorBundle;
import com.example.ines.domain.interactor.DefaultCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class CirculoDataRepository implements CirculoRepository {

    private final ReadWriteDataSource storageDataSource;

    @Inject
    CirculoDataRepository(ReadWriteDataSource storageDataSource) {
        this.storageDataSource = storageDataSource;
    }

    @Override
    public void getCirculosByDate(String date, GetCirculosByDateCallback callback) {
        try {
            List<String> list = storageDataSource.getCirculos();
            for ( String i : list) {
                if (!i.contains(date)) {
                    list.remove(i);
                }
            }
            callback.onSuccess(list);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            };
            callback.onError(errorBundle);
        }
    }

    @Override
    public void getCirculosByName(String name, GetCirculosByNameCallback callback) {
        try {
            List<String> list = storageDataSource.getCirculos();
            for ( String i : list) {
                if (!i.contains(name)) {
                    list.remove(i);
                }
            }
            callback.onSuccess(list);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            };
            callback.onError(errorBundle);
        }
    }

    @Override
    public void getCirculos(String date, String name, GetCirculosCallback callback) {
        try {
            List<String> list = storageDataSource.getCirculos();
            if(list!=null) {
                for (int i = 0; i< list.size(); i++) {
                    if (!list.get(i).contains(date) || !list.get(i).contains(name)) {
                        list.remove(i);
                        i--;
                    }
                }
            }
            callback.onSuccess(list);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            };
            callback.onError(errorBundle);
        }
    }

    /**
     * Get the string content of the .txt file
     * @param date
     * @param name must be exact
     * @param callback
     */
    @Override
    public void getCirculo(String date, String name, GetCirculoCallback callback) {
        try {
            Circulo circulo = createCirculo(storageDataSource.getCirculo(date, name));
            callback.onSuccess(circulo);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            };
            callback.onError(errorBundle);
        }
    }

    //TODO: Review
    private Circulo createCirculo(String s) {
        Circulo circulo = new Circulo();
        Pattern pattern;
        Pattern pattern_text = Pattern.compile("(?<=<texto>)(.*?)(?=</texto>)");
        Pattern pattern_image = Pattern.compile("(?<=<imagen>)(.*?)(?=</imagen>)");
        Pattern pattern_doc = Pattern.compile("(?<=<doc>)(.*?)(?=</doc>)");
        String [] text;
        String [] images;
        String [] docs;
        List<String> aux = null;

        /** Extract comentario */
        pattern = Pattern.compile("(?<=<comentario>)(.*?)(?=</comentario>)");
        String comentario = pattern.split(s)[0];
        text = pattern_text.split(comentario);
        aux.addAll(Arrays.asList(text));
        circulo.setComentario(aux.get(0));
        aux.clear();
        images = pattern_image.split(comentario);
        aux.addAll(Arrays.asList(images));
        circulo.setImagenesComentario(aux);
        aux.clear();
        docs = pattern_doc.split(comentario);
        aux.addAll(Arrays.asList(docs));
        circulo.setDocComentario(aux);
        aux.clear();

        /** Extract norma */
        pattern = Pattern.compile("(?<=<norma>)(.*?)(?=</norma>)");
        String norma = pattern.split(s)[0];
        text = pattern_text.split(norma);
        aux.addAll(Arrays.asList(text));
        circulo.setNorma(aux.get(0));
        aux.clear();
        images = pattern_image.split(norma);
        aux.addAll(Arrays.asList(images));
        circulo.setImagenesNorma(aux);
        aux.clear();
        docs = pattern_doc.split(norma);
        aux.addAll(Arrays.asList(docs));
        circulo.setDocNorma(aux);
        aux.clear();

        /** Extract charla */
        pattern = Pattern.compile("(?<=<charla>)(.*?)(?=</charla>)");
        String charla = pattern.split(s)[0];
        text = pattern_text.split(charla);
        aux.addAll(Arrays.asList(text));
        circulo.setCharla(aux.get(0));
        aux.clear();
        images = pattern_image.split(charla);
        aux.addAll(Arrays.asList(images));
        circulo.setImagenesCharla(aux);
        aux.clear();
        docs = pattern_doc.split(charla);
        aux.addAll(Arrays.asList(docs));
        circulo.setDocCharla(aux);
        aux.clear();

        /** Extract tertulia */
        pattern = Pattern.compile("(?<=<tertulia>)(.*?)(?=</tertulia>)");
        String tertulia = pattern.split(s)[0];
        text = pattern_text.split(tertulia);
        aux.addAll(Arrays.asList(text));
        circulo.setTertulia(aux.get(0));
        aux.clear();
        images = pattern_image.split(tertulia);
        aux.addAll(Arrays.asList(images));
        circulo.setImagenesTertulia(aux);
        aux.clear();
        docs = pattern_doc.split(tertulia);
        aux.addAll(Arrays.asList(docs));
        circulo.setDocTertulia(aux);
        aux.clear();

        return circulo;
    }

    @Override
    public void addImage(String date, String name, String topic, String content, AddImageCallback callback) {
        addElement(date, name, topic, content, "image", callback);
    }

    @Override
    public void addDoc(String date, String name, String topic, String content, AddDocCallback callback) {
        addElement(date, name, topic, content, "doc", callback);
    }

    private void addElement(String date, String name, String topic, String content, String element, DefaultCallback callback) {
        try {
            if (!storageDataSource.existsCirculo(date, name)) {
                storageDataSource.newCirculo(date, name);
            }
            String circulo = storageDataSource.getCirculo(date, name);
            String [] first = circulo.split("<" + topic + ">");
            String [] middle = first[1].split("</" + topic + ">");
            String end = middle[1];
            circulo = Arrays.toString(first) + "<" + topic + ">" +
                    Arrays.toString(middle) + "<" + element + ">" + content +
                    "</" + element + ">" + "</" + topic + ">" + end;
            storageDataSource.saveCirculo(date, name, circulo);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            }; callback.onError(errorBundle);
        }
    }

    @Override
    public void editText(String date, String name, String topic, String content, EditTextCallback callback) {
        try {
            if (!storageDataSource.existsCirculo(date, name)) {
                storageDataSource.newCirculo(date, name);
            }
            else {
                if(storageDataSource.getCirculo(date, name).equals("")) {
                    storageDataSource.removeCirculo(date, name);
                    storageDataSource.newCirculo(date, name);
                }
            }
            storageDataSource.editText(date, name, topic, content);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            }; callback.onError(errorBundle);
        }
    }

    @Override
    public void removeImage(String date, String name, String topic, String content, RemoveImageCallback callback) {
        removeElement(date, name, topic, content, "image", callback);
    }

    @Override
    public void removeDoc(String date, String name, String topic, String content, RemoveDocCallback callback) {
        removeElement(date, name, topic, content, "doc", callback);
    }

    private void removeElement(String date, String name, String topic, String content, String element, DefaultCallback callback) {
        try {
            String circulo = storageDataSource.getCirculo(date, name);
            String [] first = circulo.split("<" + topic + ">");
            String [] middle = first[1].split("</" + topic + ">");
            String end = middle[1];
            String [] elements = Pattern.compile("(?<=<"+element+">)(.*?)(?=</"+element+">)").split(middle[0]);
            String dirFound = null;
            for (String i : elements) {
                if (i.contains(content))  dirFound = content;
            }
            if(dirFound!= null) {
                String[] middle_aux = middle[0].split("<" + element + ">" + content + "</"+ element+">");
                middle[0] = middle_aux[0] + middle_aux[1];
            }
            circulo = Arrays.toString(first) + middle[0] + end;
            storageDataSource.saveCirculo(date, name, circulo);
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            }; callback.onError(errorBundle);
        }
    }

    @Override
    public void removeCirculo(String date, String name, RemoveCirculoCallback callback) {
        try {
            if (storageDataSource.existsCirculo(date, name)) {
                storageDataSource.removeCirculo(date, name);
            }
        } catch (final IOException e) {
            ErrorBundle errorBundle = new ErrorBundle() {
                @Override
                public Exception getException() {
                    return e;
                }

                @Override
                public String getErrorMessage() {
                    return e.getMessage();
                }
            }; callback.onError(errorBundle);
        }
    }


}
