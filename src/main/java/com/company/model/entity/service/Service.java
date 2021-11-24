package com.company.model.entity.service;

public enum Service {
    INTERNET(1),CABLE_TV(2),IP_TV(3),PHONE(4);

    int id;

    Service(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
