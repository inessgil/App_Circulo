package com.example.ines.domain.entities;


import java.util.List;

public class Circulo {

    private List<String> comentario;
    private List<String> imagenesComentario;
    private List<String> docComentario;
    private List<String> norma;
    private List<String> imagenesNorma;
    private List<String> docNorma;
    private List<String> charla;
    private List<String> imagenesCharla;
    private List<String> docCharla;
    private List<String> tertulia;
    private List<String> imagenesTertulia;
    private List<String> docTertulia;

    public Circulo () {}

    public Circulo(List<String> comentario, List<String> imagenesComentario, List<String> docComentario,
                   List<String> norma, List<String> imagenesNorma, List<String> docNorma,
                   List<String> charla, List<String> imagenesCharla, List<String> docCharla,
                   List<String> tertulia, List<String> imagenesTertulia, List<String> docTertulia) {
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

    public List<String> getComentario() {
        return comentario;
    }

    public void setComentario(List<String> comentario) {
        this.comentario = comentario;
    }

    public List<String> getImagenesComentario() {
        return imagenesComentario;
    }

    public void setImagenesComentario(List<String> imagenesComentario) {
        this.imagenesComentario = imagenesComentario;
    }

    public List<String> getNorma() {
        return norma;
    }

    public void setNorma(List<String> norma) {
        this.norma = norma;
    }

    public List<String> getImagenesNorma() {
        return imagenesNorma;
    }

    public void setImagenesNorma(List<String> imagenesNorma) {
        this.imagenesNorma = imagenesNorma;
    }

    public List<String> getCharla() {
        return charla;
    }

    public void setCharla(List<String> charla) {
        this.charla = charla;
    }

    public List<String> getImagenesCharla() {
        return imagenesCharla;
    }

    public void setImagenesCharla(List<String> imagenesCharla) {
        this.imagenesCharla = imagenesCharla;
    }

    public List<String> getTertulia() {
        return tertulia;
    }

    public void setTertulia(List<String> tertulia) {
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
