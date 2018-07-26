package com.example.ines.circulo.guion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ines.circulo.App;
import com.example.ines.circulo.R;
import com.example.ines.circulo.dependencyinjection.activity.ActivityModule;
import com.example.ines.circulo.dependencyinjection.application.ViewModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuionActivity extends AppCompatActivity implements GuionView{

    private String date;
    private String type;
    int op;
    @Inject
    GuionPresenter presenter;
    @BindView(R.id.titulo)
    TextView titulo;
    @BindView(R.id.content)
    RecyclerView content;
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
        presenter.setParameters(date, type);
        op = getIntent().getIntExtra("load", 0);
        String title_date = date.substring(0,2) + "/"
                + date.substring(2,4) + "/"
                + date.substring(4,8);
        titulo.setText("Circulo " + type + " - " + title_date);
        content.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        guionAdapter = new GuionAdapter(presenter, this);
        content.setAdapter(guionAdapter);
        if (op==0){ //NEW
            presenter.createNewGuion(type);
        }
        else presenter.loadGuion(date, type);
    }

    @Override
    public void printGuion(List<GuionPart> guion) {
        guionAdapter.setData(guion);
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
