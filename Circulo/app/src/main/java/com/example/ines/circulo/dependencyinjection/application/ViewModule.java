package com.example.ines.circulo.dependencyinjection.application;

import com.example.ines.circulo.guion.GuionView;
import com.example.ines.circulo.portada.PortadaView;
import com.example.ines.circulo.resultado.ResultadoView;
import com.example.ines.circulo.tiposfecha.TiposFechaView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    private GuionView guionView;
    private PortadaView portadaView;
    private ResultadoView resultadoView;
    private TiposFechaView tiposFechaView;

    public ViewModule (GuionView guionView) {
        this.guionView = guionView;
    }
    public ViewModule (PortadaView portadaView){
        this.portadaView = portadaView;
    }
    public ViewModule (ResultadoView resultadoView) {
        this.resultadoView = resultadoView;
    }
    public ViewModule (TiposFechaView tiposFechaView) {
        this.tiposFechaView = tiposFechaView;
    }

    @Provides
    GuionView guionView () {
        return guionView;
    }

    @Provides
    PortadaView portadaView () {
        return portadaView;
    }

    @Provides
    ResultadoView resultadoView () {
        return resultadoView;
    }

    @Provides
    TiposFechaView tiposFechaView () {
        return tiposFechaView;
    }

}
