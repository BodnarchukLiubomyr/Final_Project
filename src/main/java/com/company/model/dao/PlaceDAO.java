package com.company.model.dao;

import com.company.model.MySQLConnector;
import com.company.model.entity.place.Place;

import java.io.IOException;
import java.sql.*;

import static com.company.model.dao.SQL.*;
import static com.company.model.MySQLConnector.*;

public class PlaceDAO {
    public Place insertPlace(Place place){
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(INSERT_PLACE,Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, place.getStreet());
            pStatement.setString(2,place.getDistrict());
            pStatement.setString(3,place.getCity());
            pStatement.executeUpdate();
            resultSet = pStatement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()){
                place.setId(resultSet.getInt(1));
            }
            return place;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close(pStatement);
            close(resultSet);
        }
        return null;
    }
    public Place selectPlace(int id){
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        Place place = new Place();
        try (Connection connection = MySQLConnector.getConnection() ){
            pStatement = connection.prepareStatement(SELECT_PLACE_BY_ID);
            pStatement.setInt(1,id);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()){
                mapPlace(place,resultSet);
            }
            else{
                throw new SQLException("Place isn`t found");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close(pStatement);
            close(resultSet);
        }
        return place;
    }

    public void mapPlace(Place place, ResultSet resultSet) throws SQLException, IOException {
        place.setId(resultSet.getInt("id"));
        place.setStreet(resultSet.getString("street"));
        place.setDistrict(resultSet.getString("district"));
        place.setCity(resultSet.getString("city"));
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
