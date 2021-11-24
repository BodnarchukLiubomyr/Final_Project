package com.company.model.Test;

import com.company.model.dao.*;
import com.company.model.entity.payment.Payment;


import java.math.BigDecimal;
import java.sql.SQLException;

public class testPayment {
    public static void main(String[] args) throws SQLException {
        Payment payment = new Payment();
        WalletDAO dao1 = new WalletDAO();
        payment.setWalletId(1);
        TariffDAO dao2 = new TariffDAO();
        payment.setTariff(dao2.selectTariff(1));
        payment.setPrice(new BigDecimal(150));
        PaymentDAO dao = new PaymentDAO();
        //dao.insertPayment(payment,3);
        System.out.println(dao.getUserPayment(3).getPrice());
    }
}
