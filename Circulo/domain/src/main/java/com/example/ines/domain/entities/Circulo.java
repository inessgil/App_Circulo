package com.example.ines.domain.entities;


import java.util.List;

public class Circulo {

    private String comentario;
    private List<String> imagenesComentario;
    private List<String> docComentario;
    private String norma;
    private List<String> imagenesNorma;
    private List<String> docNorma;
    private String charla;
    private List<String> imagenesCharla;
    private List<String> docCharla;
    private String tertulia;
    private List<String> imagenesTertulia;
    private List<String> docTertulia;

    public Circulo () {}

    public Circulo(String comentario, List<String> imagenesComentario, List<String> docComentario,
                   String norma, List<String> imagenesNorma, List<String> docNorma,
                   String charla, List<String> imagenesCharla, List<String> docCharla,
                   String tertulia, List<String> imagenesTertulia, List<String> docTertulia) {
        this.comentario = comentario;
        this.imagenesComentario = imagenesComentario;
        this.docComentario = docComentario;
        this.norma = norma;
        this.imagenesNorma = imagenesNorma;
        this.docNorma = docNorma;
        this.charla = charla;
        this.imagenesCharla = imagenesCharla;
        this.docCharla = docCharla;
        this.tertulia = tertulia;
        this.imagenesTertulia = imagenesTertulia;
        this.docTertulia = docTertulia;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<String> getImagenesComentario() {
        return imagenesComentario;
    }

    public void setImagenesComentario(List<String> imagenesComentario) {
        this.imagenesComentario = imagenesComentario;
    }

    public String getNorma() {
        return norma;
    }

    public void setNorma(String norma) {
        this.norma = norma;
    }

    public List<String> getImagenesNorma() {
        return imagenesNorma;
    }

    public void setImagenesNorma(List<String> imagenesNorma) {
        this.imagenesNorma = imagenesNorma;
    }

    public String getCharla() {
        return charla;
    }

    public void setCharla(String charla) {
        this.charla = charla;
    }

    public List<String> getImagenesCharla() {
        return imagenesCharla;
    }

    public void setImagenesCharla(List<String> imagenesCharla) {
        this.imagenesCharla = imagenesCharla;
    }

    public String getTertulia() {
        return tertulia;
    }

    public void setTertulia(String tertulia) {
        this.tertulia = tertulia;
    }

    public List<String> getImagenesTertulia() {
        return imagenesTertulia;
    }

    public void setImagenesTertulia(List<String> imagenesTertulia) {
        this.imagenesTertulia = imagenesTertulia;
    }

    public List<String> getDocComentario() {
        return docComentario;
    }

    public void setDocComentario(List<String> docComentario) {
        this.docComentario = docComentario;
    }

    public List<String> getDocNorma() {
        return docNorma;
    }

    public void setDocNorma(List<String> docNorma) {
        this.docNorma = docNorma;
    }

    public List<String> getDocCharla() {
        return docCharla;
    }

    public void setDocCharla(List<String> docCharla) {
        this.docCharla = docCharla;
    }

    public List<String> getDocTertulia() {
        return docTertulia;
    }

    public void setDocTertulia(List<String> docTertulia) {
        this.docTertulia = docTertulia;
    }
}
