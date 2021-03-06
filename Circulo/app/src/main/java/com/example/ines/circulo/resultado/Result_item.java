package com.example.ines.circulo.resultado;

public class Result_item {

    String name;
    private boolean isSelected = false;

    public Result_item (String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        Result_item item = (Result_item) obj;
        if(item.getName().equals(this.getName())) return true;
        return false;
    }
}
