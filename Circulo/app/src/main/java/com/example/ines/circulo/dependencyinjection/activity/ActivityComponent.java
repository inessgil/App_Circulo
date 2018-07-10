package com.example.ines.circulo.dependencyinjection.activity;

import com.example.ines.circulo.dependencyinjection.application.ViewModule;
import com.example.ines.circulo.dependencyinjection.scope.PerActivity;
import com.example.ines.circulo.guion.GuionActivity;
import com.example.ines.circulo.portada.PortadaActivity;
import com.example.ines.circulo.portada.PortadaView;
import com.example.ines.circulo.resultado.ResultadoActivity;
import com.example.ines.circulo.tiposfecha.TiposFechaView;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {ActivityModule.class, ViewModule.class})
public interface ActivityComponent {

    void inject (GuionActivity activity);
    void inject (PortadaActivity activity);
    void inject (ResultadoActivity activity);
    void inject (TiposFechaView tiposFechaView);
}
