package com.chojnacki.manufaktura.manuliczek.model;

public enum Place {
    GALLERY, PATIO;

    public boolean isPatio() {
        return this.equals(PATIO);
    }

    public boolean isGallery() {
        return this.equals(GALLERY);
    }
}
