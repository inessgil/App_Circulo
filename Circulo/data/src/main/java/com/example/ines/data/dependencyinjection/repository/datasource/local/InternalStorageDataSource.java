package com.example.ines.data.dependencyinjection.repository.datasource.local;

import android.content.Context;

import com.example.ines.data.dependencyinjection.repository.datasource.ReadWriteDataSource;
import com.example.ines.domain.entities.Circulo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class InternalStorageDataSource implements ReadWriteDataSource {

    Context context;

    @Inject
    InternalStorageDataSource (Context context) {
        this.context = context;
    }

    @Override
    public List<String> getCirculosByDate(String date) throws IOException {
        String [] circulos = context.fileList();
        List<String> result = null;
        for (String c : circulos) {
            if ( c.contains(date)) result.add(c);
        }
        return result;
    }

    @Override
    public List<String> getCirculosByName(String name) throws IOException {
        String [] circulos = context.fileList();
        List<String> result = null;
        for (String c : circulos) {
            if ( c.contains(name)) result.add(c);
        }
        return result;
    }

    @Override
    public List<String> getCirculos(String date, String name) throws IOException {
        String [] circulos = context.fileList();
        List<String> result = null;
        for (String c : circulos) {
            if ( c.contains(name) && c.contains(date)) result.add(c);
        }
        return result;
    }

    @Override
    public void newCirculo(String date, String name) throws IOException {
        File file = new File(context.getFilesDir(), date + "_" + name);
        String content = "<comentario></comentario><norma></norma><charla></charla><tertulia></tertulia>";
        FileOutputStream outputStream;
        outputStream = context.openFileOutput(date + "_" + name, context.MODE_PRIVATE);
        outputStream.write(content.getBytes());
        outputStream.close();
    }

    @Override
    public void writeCirculo(String date, String name, String topic, String type, String data) throws IOException {
        String [] circulos = context.fileList();
        List<String> result = null;
        for (String c : circulos) {
            if ( c.contains(name) && c.contains(date)) result.add(c);
        }
        StringBuffer content = new StringBuffer("");
        FileInputStream file = context.openFileInput(result.get(0));
        InputStreamReader inputStreamReader = new InputStreamReader(file);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String readString = bufferedReader.readLine();
        while (readString != null) {
            content.append(readString);
            readString = bufferedReader.readLine();
        }
        inputStreamReader.close();

        String[] start = content.toString().split("<" + topic + ">");
        String[] middle = start[1].split("</" + topic + ">");
        String end = middle[1];

        String toSave = start[0] + "<" + topic + ">" + middle[0] + "<" + type + "/>" + data + "</" + topic + ">" + end;

        FileOutputStream outputStream;
        outputStream = context.openFileOutput(date + "_" + name, context.MODE_PRIVATE);
        outputStream.write(toSave.getBytes());
        outputStream.close();
    }

    @Override
    public Circulo getCirculo(String date, String name) throws IOException {
        String [] circulos = context.fileList();
        List<String> result = null;
        for (String c : circulos) {
            if ( c.contains(name) && c.contains(date)) result.add(c);
        }
        if ( result.isEmpty()) return null;

        StringBuffer content = new StringBuffer("");
        FileInputStream file = context.openFileInput(result.get(0));
        InputStreamReader inputStreamReader = new InputStreamReader(file);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String readString = bufferedReader.readLine();
        while (readString != null) {
            content.append(readString);
            readString = bufferedReader.readLine();
        }
        inputStreamReader.close();

        return createCirculo (content.toString());
    }

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
        circulo.setComentario(aux);
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
        circulo.setNorma(aux);
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
        circulo.setCharla(aux);
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
        circulo.setTertulia(aux);
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
    public void removeCirculo(String date, String name) throws IOException {

    }
}
