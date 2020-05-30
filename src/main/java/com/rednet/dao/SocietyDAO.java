package com.rednet.dao;

import com.rednet.entities.Person;
import com.rednet.entities.Society;

import java.sql.*;
import java.util.ArrayList;

public class SocietyDAO {
    public Connection connection;

    public void createConnection() throws SQLException, ClassNotFoundException {
        if(connection != null) {
            return ;
        }
        String url = "jdbc:mysql://localhost:3306/rednet";
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, "root", "root");
    }

    private void fillFields(ResultSet resultSet, Society society, Person person) throws SQLException {
        person.setPersonId(resultSet.getInt("p.person_id"));
        person.setUsername(resultSet.getString("username"));
        person.setFirstName(resultSet.getString("first_name"));
        person.setLastName(resultSet.getString("last_name"));
        person.setEmail(resultSet.getString("email"));
        person.setGender(resultSet.getString("gender"));
        person.setBloodGroup(resultSet.getString("blood_group"));
        person.setLatitude(resultSet.getDouble("latitude"));
        person.setLongitude(resultSet.getDouble("longitude"));
        person.setPhone1(resultSet.getString("phone1"));
        person.setCity(resultSet.getString("city"));
        person.setArea(resultSet.getString("area"));
        society.setPersonId(person);
        society.setName(resultSet.getString("name"));
        society.setSocietyId(resultSet.getInt("society_id"));
    }

    public ArrayList<Society> getSocieties() throws SQLException, ClassNotFoundException {
        ArrayList<Society> Societies = new ArrayList<Society>();
        String sql = "SELECT * FROM society s, person p WHERE s.head_id = p.person_id";
        createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            Society Society = new Society();
            Person person = new Person();
            fillFields(resultSet, Society, person);
            Societies.add(Society);
        }
        return Societies;
    }

    public Society getSociety(int id) throws SQLException, ClassNotFoundException {
        Society Society = new Society();
        Person person = new Person();
        String sql = "SELECT * FROM society s, person p WHERE s.head_id = p.person_id AND society_id = ?";
        createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            fillFields(resultSet, Society, person);
        }
        return Society;
    }
}
