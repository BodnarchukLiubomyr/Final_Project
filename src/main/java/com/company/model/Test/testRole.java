package com.company.model.Test;

import com.company.model.dao.*;
import com.company.model.entity.place.Place;
import com.company.model.entity.service.Service;
import com.company.model.entity.tariff.Tariff;

import java.math.BigDecimal;
import java.sql.SQLException;

public class testRole {
    public static void main(String[] args) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setId(1);
        tariff.setName_en("Lux tariff");
        tariff.setTime(30);
        tariff.setDescription("The best tariff");
        tariff.setPrice(new BigDecimal(150));
        PlaceDAO dao1 = new PlaceDAO();
        tariff.setPlace(dao1.selectPlace(1));
        TariffStatusDAO dao2 = new TariffStatusDAO();
        tariff.setStatus(dao2.getStatus(1));
        ServiceDAO dao3 = new ServiceDAO();
        tariff.setService(dao3.getService(1));
        TariffDAO dao = new TariffDAO();
        dao.insertTariff(tariff);
        System.out.println(dao.selectTariff(1).getName_en());
    }
}
