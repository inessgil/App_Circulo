package com.example.ines.circulo.portada;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ines.circulo.App;
import com.example.ines.circulo.R;
import com.example.ines.circulo.dependencyinjection.activity.ActivityModule;
import com.example.ines.circulo.dependencyinjection.application.ViewModule;
import com.example.ines.circulo.tiposfecha.TiposFechaActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * First Activity - Launcher
 * Two buttons to choose between:
 * Search an existing Circulo and
 * Create a new Circulo
 */

public class PortadaActivity extends AppCompatActivity implements PortadaView{

    @Inject
    PortadaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);
        ButterKnife.bind(this);
        ((App) getApplication())
                .getComponent()
                .plus(new ActivityModule(this),
                        new ViewModule(this))
                .inject(this);
    }

    /**
     * Types:
     * 0 -> new
     * 1 -> search and load existing
     */
    @OnClick(R.id.b_nuevo)
    public void nuevo_circulo () {
        presenter.redirect(0);
    }

    @OnClick(R.id.b_cargar)
    public void cargar_circulo () {
        presenter.redirect(1);
    }

    @Override
    public void startNewActivity(int type) {
        Intent intent = new Intent(this, TiposFechaActivity.class);
        intent.putExtra("TYPE", type);
        startActivity(intent);
    }
}
