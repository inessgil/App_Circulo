package com.example.ines.circulo.tiposfecha;

import com.example.ines.domain.exception.ErrorBundle;
import com.example.ines.domain.interactor.circulo.GetCirculosByDateInteractor;
import com.example.ines.domain.interactor.circulo.GetCirculosByNameInteractor;
import com.example.ines.domain.interactor.circulo.GetCirculosInteractor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class TiposFechaPresenter {

    private final TiposFechaView tiposFechaView;
    private final GetCirculosByDateInteractor getCirculosByDateInteractor;
    private final GetCirculosInteractor getCirculosInteractor;
    private final GetCirculosByNameInteractor getCirculosByNameInteractor;

    @Inject
    public TiposFechaPresenter(TiposFechaView tiposFechaView,
                               GetCirculosByDateInteractor getCirculosByDateInteractor,
                               GetCirculosByNameInteractor getCirculosByNameInteractor,
                               GetCirculosInteractor getCirculosInteractor) {
        this.tiposFechaView = tiposFechaView;
        this.getCirculosByDateInteractor = getCirculosByDateInteractor;
        this.getCirculosByNameInteractor = getCirculosByNameInteractor;
        this.getCirculosInteractor = getCirculosInteractor;
    }

    public void searchAll(String date, String type) {
        Map<String, String> map = new HashMap<>();
        map.put("date", date);
        map.put("name", type);
        getCirculosInteractor.execute(map, new GetCirculosInteractor.GetCirculosCallback() {
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

    public void searchByName(String type) {
        Map<String, String> map = new HashMap<>();
        map.put("name", type);
        getCirculosByNameInteractor.execute(map, new GetCirculosInteractor.GetCirculosCallback() {
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

    public void searchByDate(String date) {
        Map<String, String> map = new HashMap<>();
        map.put("date", date);
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
