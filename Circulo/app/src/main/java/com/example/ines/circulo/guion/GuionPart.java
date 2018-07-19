package com.example.ines.circulo.guion;

import java.util.ArrayList;
import java.util.List;

public class GuionPart {

    String topic;
    String text;
    String editable_text;
    List<String> images;
    List<String> docs;

    public GuionPart () {
        topic = "";
        text = "";
        editable_text = "";
        images = new ArrayList<>();
        docs = new ArrayList<>();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public String getEditable_text() {
        return editable_text;
    }

    public List<String> getDocs() {
        return docs;
    }

    public List<String> getImages() {
        return images;
    }

    public void setDocs(List<String> docs) {
        this.docs = docs;
    }

    public void setEditable_text(String editable_text) {
        this.editable_text = editable_text;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setText(String text) {
        this.text = text;
    }
}
