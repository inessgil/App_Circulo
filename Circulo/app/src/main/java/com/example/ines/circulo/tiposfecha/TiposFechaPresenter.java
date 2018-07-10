package com.example.ines.circulo.tiposfecha;

import javax.inject.Inject;

public class TiposFechaPresenter {

    private final TiposFechaView tiposFechaView;

    @Inject
    public TiposFechaPresenter(TiposFechaView tiposFechaView) {
        this.tiposFechaView = tiposFechaView;
    }
}
