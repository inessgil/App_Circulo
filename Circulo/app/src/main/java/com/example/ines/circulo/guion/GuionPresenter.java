package com.example.ines.circulo.guion;

import javax.inject.Inject;

public class GuionPresenter {

    private final GuionView guionView;

    @Inject
    public GuionPresenter(GuionView guionView) {
        this.guionView = guionView;
    }

}
