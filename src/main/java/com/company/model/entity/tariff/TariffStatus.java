package com.company.model.entity.tariff;

public enum TariffStatus {
    AVAILABLE(1), NOT_AVAILABLE(2);

    int id;

    TariffStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
