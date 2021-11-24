package com.company.model.entity.tariff;

import com.company.model.entity.place.Place;
import com.company.model.entity.service.Service;

import java.math.BigDecimal;

public class Tariff implements Comparable<Tariff> {
    private int id;
    private String name_en;
    private int time;
    private BigDecimal price;
    private String description;
    private Service service;
    private TariffStatus status;
    private Place place;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName_en() {
        return name_en;
    }
    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Service getService() {
        return service;
    }
    public void setService(Service service) {
        this.service = service;
    }

    public TariffStatus getStatus() {
        return status;
    }
    public void setStatus(TariffStatus status) {
        this.status = status;
    }

    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public int compareTo(Tariff tariff) {
        return this.getName_en().compareTo(tariff.getName_en());
    }
}
