package com.example.ines.circulo.resultado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ines.circulo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Resultado extends AppCompatActivity {

    int checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        ButterKnife.bind(this);
        checked = -1;
    }

    @OnClick(R.id.b_accept)
    public void load (View view) {
        if (checked != -1) {

        }
    }

    @OnClick(R.id.b_return)
    public void back (View view) {

    }

}
