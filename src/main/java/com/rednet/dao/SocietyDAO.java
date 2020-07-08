package com.rednet.dao;

import com.rednet.entities.Person;
import com.rednet.entities.Society;
import com.rednet.entities.SocietyRequest;

import javax.xml.transform.Result;
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

    public boolean createSocietyRequest(String societyName, String societyDescription, Person person) throws SQLException, ClassNotFoundException {
        PersonDAO personDAO = new PersonDAO();
        int personId = personDAO.createPerson(person.getUsername(), person.getFirstName(), person.getLastName(), person.getPassword(), person.getEmail(), person.getPhone1(), person.getGender(), person.getCity(), person.getArea(), person.getBloodGroup());

        String sql = "INSERT INTO rednet.society_request(name, description, head_id) VALUES (?, ?, ?)";
        createConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, societyName);
        ps.setString(2, societyDescription);
        ps.setInt(3, personId);

        if(ps.executeUpdate() >= 1)
        {
            return true;
        }
        return false;
    }

    public boolean createSociety(String societyName, int personId) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO rednet.society(name, head_id) VALUES (?, ?)";
        createConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, societyName);
        ps.setInt(2, personId);

        if(ps.executeUpdate() >= 1)
        {
            return true;
        }
        return false;
    }


    public SocietyRequest getSocietyRequestById(int Id) throws SQLException, ClassNotFoundException {
        String sql = "Select * from society_request where society_request_id = ?";
        createConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, Id);
        ResultSet rs =  ps.executeQuery();

        SocietyRequest societyRequest = new SocietyRequest();
        Person person = new Person();

        if(rs.next())
        {
            societyRequest.setName(rs.getString("name"));
            societyRequest.setDescription(rs.getString("description"));
            person.setPersonId(rs.getInt("head_id"));
            societyRequest.setPersonByHeadId(person);
            return societyRequest;
        }
       return null;

    }


}
