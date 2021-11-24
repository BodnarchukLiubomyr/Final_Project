package com.company.model.Test;

import com.company.model.dao.UserDAO;
import com.company.model.dao.WalletDAO;
import com.company.model.entity.user.User;
import com.company.model.entity.user.UserDetails;
import com.company.model.entity.wallet.Wallet;

import java.math.BigDecimal;
import java.sql.SQLException;

public class testWallet {
    public static void main(String[] args) throws SQLException {
        Wallet wallet = new Wallet();
        UserDetails details = new UserDetails();
        wallet.setFunds(new BigDecimal(500));
        UserDAO dao1 = new UserDAO();
        wallet.setUserId(1);
        WalletDAO dao = new WalletDAO();
        dao.insertWallet(wallet,1);
        System.out.println(dao.getWallet(1).getFunds());
    }
}
