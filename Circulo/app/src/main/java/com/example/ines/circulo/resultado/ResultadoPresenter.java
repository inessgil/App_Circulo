package com.example.ines.circulo.resultado;

import javax.inject.Inject;

public class ResultadoPresenter {

    private final ResultadoView resultadoView;

    @Inject
    public ResultadoPresenter(ResultadoView resultadoView) {
        this.resultadoView = resultadoView;
    }
}
