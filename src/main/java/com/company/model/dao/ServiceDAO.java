package com.company.model.dao;

import com.company.model.MySQLConnector;
import com.company.model.entity.service.Service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.company.model.MySQLConnector.getConnection;
import static com.company.model.dao.SQL.*;

public class ServiceDAO {
    public Service getService(int id){
        Service service = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SELECT_SERVICE);
            pStatement.setInt(1,id);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()){
                switch (resultSet.getString("service")){
                    case "Internet":
                        service = Service.INTERNET;
                        break;
                    case "Cable_tv":
                        service = Service.CABLE_TV;
                        break;
                    case "Ip_tv":
                        service = Service.IP_TV;
                        break;
                    case "Phone":
                        service = Service.PHONE;
                        break;
                    default:
                        throw new SQLException("Incorrect service");
                }
            }
            else {
                throw new SQLException("Service isn`t found");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close(pStatement);
            close(resultSet);
        }
        return service;
    }
    public void close(AutoCloseable closeable){
        if (closeable != null){
            try {
                closeable.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
