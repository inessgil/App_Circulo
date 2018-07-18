package com.example.ines.circulo.resultado;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ines.circulo.App;
import com.example.ines.circulo.R;
import com.example.ines.circulo.dependencyinjection.activity.ActivityModule;
import com.example.ines.circulo.dependencyinjection.application.ViewModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultadoActivity extends AppCompatActivity implements ResultadoView, ResultViewHolder.OnItemSelectedListener {

    int checked;
    ArrayList<String> lista;

    @Inject
    ResultadoPresenter presenter;
    @BindView(R.id.l_results)
    RecyclerView recyclerView;

    Result_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        ButterKnife.bind(this);
        ((App) getApplication())
                .getComponent()
                .plus(new ActivityModule(this),
                        new ViewModule(this))
                .inject(this);
        checked = -1;
        lista = getIntent().getStringArrayListExtra("list");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<Result_item> items = generateItems(lista);
        adapter = new Result_Adapter(this, items);
        recyclerView.setAdapter(adapter);
    }

    private List<Result_item> generateItems(ArrayList<String> lista) {
        List<Result_item> result = new ArrayList<>();
        for ( String i : lista) {
            Result_item item = new Result_item(i, false);
            result.add(item);
        }
        return result;
    }

    @Override
    public void onItemSelected(Result_item item) {
        List<Result_item> selectedItems = adapter.getSelectedItems();

    }

    @OnClick(R.id.b_accept)
    public void load (View view) {
        if (checked != -1) {
            showError(1);
        }
    }

    @OnClick(R.id.b_return)
    public void back (View view) {

    }

    public void showError (int code) {
        String message = "";
        switch (code) {
            case 1:
                message = "Por favor, elija un circulo";
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
