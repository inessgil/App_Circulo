package com.example.ines.circulo.tiposfecha;

import android.support.annotation.NonNull;

import com.example.ines.domain.exception.ErrorBundle;
import com.example.ines.domain.interactor.circulo.GetCirculosByDateInteractor;
import com.example.ines.domain.interactor.circulo.GetCirculosInteractor;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

public class TiposFechaPresenter {

    private final TiposFechaView tiposFechaView;
    private final GetCirculosByDateInteractor getCirculosByDateInteractor;

    @Inject
    public TiposFechaPresenter(TiposFechaView tiposFechaView, GetCirculosByDateInteractor getCirculosByDateInteractor) {
        this.tiposFechaView = tiposFechaView;
        this.getCirculosByDateInteractor = getCirculosByDateInteractor;
    }

    public void searchAll(String date, String type) {
        Map<String, String> map = new HashMap<>();
        map.put("date", date);
        map.put("name", type);
        getCirculosByDateInteractor.execute(map, new GetCirculosInteractor.GetCirculosCallback() {
            @Override
            public void onError(ErrorBundle errorBundle) {
                tiposFechaView.showError(1);
            }

            @Override
            public void onSuccess(List<String> returnParam) {
                tiposFechaView.showResults(returnParam);
            }
        });
    }

    public void newCirculo(String date, String type) {
        tiposFechaView.newCirculo(date, type);
    }
}
