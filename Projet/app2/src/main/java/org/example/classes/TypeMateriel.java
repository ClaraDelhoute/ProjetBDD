package org.example.classes;


public enum TypeMateriel {
    ECRAN("Ecran"),
    CLAVIER("Clavier"),
    SOURIS("Souris"),
    TOUR("Tour"),
    LAPTOP("Laptop"),
    ENCEINTES("Enceinte");

    private String description;

    TypeMateriel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}