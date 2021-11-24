package com.company.model.dao;

import com.company.model.MySQLConnector;
import com.company.model.entity.payment.Payment;
import com.company.model.entity.tariff.Tariff;
import com.company.model.entity.wallet.Wallet;

import java.sql.*;

import static com.company.model.dao.SQL.*;


public class PaymentDAO {
    public void insertPayment(Payment payment,int walletId ){
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        Tariff tariff = payment.getTariff();
        try {
            connection = MySQLConnector.getConnection();
            connection.setAutoCommit(false);
            pStatement = connection.prepareStatement(INSERT_PAYMENT, Statement.RETURN_GENERATED_KEYS);
            pStatement.setInt(1,walletId);
            pStatement.setInt(2,tariff.getId());
            pStatement.setBigDecimal(3,payment.getPrice());;
            pStatement.executeUpdate();
            resultSet = pStatement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()){
                payment.setId(resultSet.getInt(1));
            }
            connection.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
            if (connection != null){
                try {
                    connection.rollback();
                }catch (SQLException ee){
                    ee.printStackTrace();
                }
            }
        }
        finally {
            close(connection);
            close(pStatement);
        }
    }
    public Payment getUserPayment(int walletId) throws SQLException {
        Payment payment = new Payment();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SELECT_USER_PAYMENT);
            pStatement.setInt(1,walletId);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()){
                mapPayment(payment,resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pStatement);
            close(resultSet);
        }
        return payment;
    }

    private void mapPayment(Payment payment,ResultSet resultSet) throws SQLException {
        payment.setId(resultSet.getInt("id"));
        payment.setWalletId(resultSet.getInt("wallet_id"));
        Tariff tariff = new TariffDAO().selectTariff(resultSet.getInt("tariff_id"));
        payment.setTariff(tariff);
        payment.setPrice(tariff.getPrice());
        payment.setTariffName(tariff.getName_en());
        payment.setTime(tariff.getTime());
    }

    public void close(AutoCloseable closeable){
        if(closeable != null){
            try {
                closeable.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
