package com.example.ines.circulo.resultado;

import javax.inject.Inject;

public class ResultadoPresenter {

    private final ResultadoView resultadoView;

    @Inject
    public ResultadoPresenter(ResultadoView resultadoView) {
        this.resultadoView = resultadoView;
    }

    public void loadCirculo(String name) {
        String date = name.split("_")[0];
        String type = name.split("_")[1];
        resultadoView.loadCirculo(date, type);
    }
}
