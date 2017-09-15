package com.chojnacki.manufaktura.manuregiony.model;

/**
 * Enum for type of applications
 */
public enum ProcessingType {
    LODZKIE("Tylko województwo łódzkie"), WOJEWODZTWA("Województwa"),
    POWIATY("Powiaty");

    private String name;

    ProcessingType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
