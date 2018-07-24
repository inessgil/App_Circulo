package com.example.ines.circulo.guion;

import java.util.List;

public interface GuionView {
    void printGuion(List<GuionPart> guion);
    void showError(int i);
}
