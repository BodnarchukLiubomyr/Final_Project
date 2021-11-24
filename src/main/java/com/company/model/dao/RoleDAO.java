package com.company.model.dao;

import com.company.model.MySQLConnector;
import com.company.model.entity.user.Role;
import static com.company.model.MySQLConnector.*;
import static com.company.model.dao.SQL.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO {
    public Role getRole(int id){
        Role role = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            preparedStatement = connection.prepareStatement(SELECT_ROLE);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                switch (resultSet.getString("role")){
                    case "admin":
                        role = Role.ADMIN;
                        break;
                    case "user":
                        role = Role.USER;
                        break;
                    default:
                        throw new SQLException("Incorrect role");
                }
            }
            else {
                throw new SQLException("Role isn`t found");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return role;
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
