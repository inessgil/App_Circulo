package com.example.ines.circulo.tiposfecha;

import java.util.List;

public interface TiposFechaView {
    void showError (int errorCode);
    void showResults (List<String> list);

    void newCirculo(String date, String type);
}
