package com.company.model.entity.payment;

import com.company.model.entity.tariff.Tariff;
import com.company.model.entity.wallet.Wallet;

import java.math.BigDecimal;

public class Payment {

    private int id;
    private Tariff tariff;
    private int walletId;
    private BigDecimal price;
    private String tariffName;
    private int time;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Tariff getTariff() {
        return tariff;
    }
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public int getWalletId() {
        return walletId;
    }
    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTariffName() {
        return tariffName;
    }
    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

}
