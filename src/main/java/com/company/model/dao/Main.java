package com.company.model.dao;

import com.company.model.entity.place.Place;

public class Main {
    public static void main(String[] args) {
        Place place = new Place();
        place.setStreet("test");
        place.setDistrict("fgdfg");
        place.setCity("dvdvdv");
        PlaceDAO dao = new PlaceDAO();
        dao.insertPlace(place);
        System.out.println(dao.selectPlace(1).getStreet());
    }
}
