package com.company.model.dao;

import com.company.model.MySQLConnector;
import com.company.model.entity.user.Role;
import com.company.model.entity.user.User;
import com.company.model.entity.user.UserDetails;

import java.sql.*;

import static com.company.model.dao.SQL.*;

public class UserDAO {
    public void insertUser(User user){
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            connection = MySQLConnector.getConnection();
            connection.setAutoCommit(false);
            pStatement = connection.prepareStatement(INSERT_USER_DETAILS, Statement.RETURN_GENERATED_KEYS);
            UserDetails details = user.getDetails();
            pStatement.setString(1,details.getFirstnameEn());
            pStatement.setString(2,details.getLastnameEn());
            pStatement.setString(3,details.getFirstnameUa());
            pStatement.setString(4,details.getLastnameUa());
            pStatement.setString(5,details.getEmail());
            pStatement.setString(6,details.getPhone());
            pStatement.executeUpdate();
            resultSet = pStatement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()){
                details.setId(resultSet.getInt(1));
            }
            pStatement = connection.prepareStatement(INSERT_USER,Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, user.getLogin());
            pStatement.setString(2, user.getPassword());
            pStatement.setString(3,user.getRole().toString().toLowerCase());
            pStatement.setInt(4,details.getId());
            pStatement.executeUpdate();
            resultSet = pStatement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()){
                user.setId(resultSet.getInt(1));
            }
            connection.commit();
        }
        catch (Exception e){
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
            close(pStatement);
            close(resultSet);
        }
    }

    public User getUser(String login) throws SQLException{
        User user = new User();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SELECT_USER);
            pStatement.setString(1,login);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()){
                mapUser(user,resultSet);
            }
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close(pStatement);
            close(resultSet);
        }
        return null;
    }

    public void mapUser(User user, ResultSet resultSet) throws SQLException{
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        int role_id = resultSet.getInt("role_id");
        Role role = new RoleDAO().getRole(role_id);
        user.setRole(role);
        user.setDetails(getUserDetails(resultSet.getInt("account_details_id")));
    }


    public UserDetails getUserDetails(int id) throws SQLException {
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        UserDetails details = new UserDetails();
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SELECT_USER_DETAILS);
            pStatement.setInt(1,id);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()){
                details.setFirstnameEn(resultSet.getString("first_name_en"));
                details.setLastnameEn(resultSet.getString("last_name_en"));
                details.setFirstnameUa(resultSet.getString("first_name_ua"));
                details.setLastnameUa(resultSet.getString("last_name_ua"));
                details.setEmail(resultSet.getString("email"));
                details.setPhone(resultSet.getString("phone"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close(pStatement);
            close(resultSet);
        }
        return details;
    }

    private void close(AutoCloseable closeable) {
        if (closeable != null){
            try {
                closeable.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
