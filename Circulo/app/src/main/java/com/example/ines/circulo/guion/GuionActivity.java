package com.example.ines.circulo.guion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ines.circulo.App;
import com.example.ines.circulo.R;
import com.example.ines.circulo.dependencyinjection.activity.ActivityModule;
import com.example.ines.circulo.dependencyinjection.application.ViewModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuionActivity extends AppCompatActivity implements GuionView{

    String date;
    String type;
    int op;
    @Inject
    GuionPresenter presenter;
    @BindView(R.id.titulo)
    TextView titulo;
    @BindView(R.id.content)
    RecyclerView content;
    @Inject
    GuionAdapter guionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guion);
        ButterKnife.bind(this);
        ((App) getApplication())
                .getComponent()
                .plus(new ActivityModule(this),
                        new ViewModule(this))
                .inject(this);
        date = getIntent().getStringExtra("date");
        type = getIntent().getStringExtra("type");
        op = getIntent().getIntExtra("load", 0);
        titulo.setText("Circulo " + type + " - " + date);
        content.setAdapter(guionAdapter);
        if (op==0) guionAdapter.setData(presenter.createGuion(type));
        else guionAdapter.setData(presenter.loadGuion(date, type));
    }

    @Override
    public void showError(int errorCode) {
        String errorMessage = "";
        switch (errorCode) {
            case 1:
                errorMessage = "Error del sistema";
                break;
            case 2:
                errorMessage = "Circulo guardado correctamente";
                break;
        }
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
}
