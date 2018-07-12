package com.example.ines.circulo.portada;

import javax.inject.Inject;

public class PortadaPresenter {

    private final PortadaView portadaView;

    @Inject
    public PortadaPresenter(PortadaView portadaView) {
        this.portadaView = portadaView;
    }

    public void redirect(int i) {
        portadaView.startNewActivity(i);
    }
}
