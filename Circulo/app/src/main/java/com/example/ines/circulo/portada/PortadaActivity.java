package com.example.ines.circulo.portada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.ines.circulo.R;
import com.example.ines.circulo.tiposfecha.TiposFechaActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * First Activity - Launcher
 * Two buttons to choose between:
 * Search an existing Circulo and
 * Create a new Circulo
 */

public class PortadaActivity extends AppCompatActivity {

    @BindView(R.id.b_nuevo)
    Button nuevo;
    @BindView(R.id.b_cargar)
    Button cargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);
        ButterKnife.bind(this);
    }

    /**
     * Types:
     * 0 -> new
     * 1 -> search and load existing
     */
    @OnClick(R.id.b_nuevo)
    public void nuevo_circulo () {
        Intent intent = new Intent(this, TiposFechaActivity.class);
        intent.putExtra("TYPE", 0);
        startActivity(intent);
    }
    @OnClick(R.id.b_cargar)
    public void cargar_circulo () {
        Intent intent = new Intent(this, TiposFechaActivity.class);
        intent.putExtra("TYPE", 1);
        startActivity(intent);
    }
}
