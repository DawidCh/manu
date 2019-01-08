package com.chojnacki.manufaktura.manuliczek.model;

public enum Layout {
    VERTICAL, HORIZONTAL;

    public boolean isVertical() {
        return this.equals(VERTICAL);
    }
}
