package com.company.model.dao;

import com.company.model.MySQLConnector;
import com.company.model.entity.tariff.TariffStatus;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.company.model.MySQLConnector.getConnection;
import static com.company.model.dao.SQL.*;

public class TariffStatusDAO {

    public TariffStatus getStatus(int id){
        TariffStatus status = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            preparedStatement = connection.prepareStatement(SELECT_TARIFF_STATUS_BY_ID);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                switch (resultSet.getString("status_en")){
                    case "Available":
                        status = TariffStatus.AVAILABLE;
                        break;
                    case "Not available":
                        status = TariffStatus.NOT_AVAILABLE;
                        break;
                    default:
                        throw new SQLException("Status not found");
                }
                status.setId(id);
            }
            else {
                throw new SQLException(("Status not found"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
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
