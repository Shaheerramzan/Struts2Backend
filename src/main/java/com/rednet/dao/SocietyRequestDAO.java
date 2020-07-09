package com.rednet.dao;

import com.rednet.entities.Person;
import com.rednet.entities.Society;
import com.rednet.entities.SocietyRequest;

import java.sql.*;
import java.util.ArrayList;

public class SocietyRequestDAO {
    public Connection connection;

    public void createConnection() throws SQLException, ClassNotFoundException {
        if(connection != null) {
            return ;
        }
        String url = "jdbc:mysql://localhost:3306/rednet";
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, "root", "root");
    }

    private void fillFields(ResultSet resultSet, SocietyRequest societyRequests, Person person) throws SQLException {
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

        societyRequests.setName("name");
        societyRequests.setDescription("description");
        societyRequests.setPersonByHeadId(person);
    }

    public boolean createSocietyRequest(String SocietyName, String SocietyDescription, Person person) throws SQLException, ClassNotFoundException
    {
        String sql = "INSERT INTO rednet.society_request(rednet.society_request.name, rednet.society_request.description, rednet.society_request.head_id) VALUES (?, ?, ?)";
        createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, SocietyName);
        preparedStatement.setString(2, SocietyDescription);
        preparedStatement.setInt(3, person.getPersonId());
        if(preparedStatement.executeUpdate() >= 1)
        {
            return true;
        }
        return false;
    }

    public ArrayList<SocietyRequest> getSocietiesRequests() throws SQLException, ClassNotFoundException {
        ArrayList<SocietyRequest> societyRequests = new ArrayList<SocietyRequest>();
        String sql = "SELECT * FROM rednet.society_request sr, person p WHERE sr.head_id = p.person_id";
        createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            SocietyRequest societyRequest = new SocietyRequest();
            Person person = new Person();
            fillFields(resultSet, societyRequest, person);
            societyRequests.add(societyRequest);
        }
        return societyRequests;
    }


    public SocietyRequest getSocietyRequestById(int Id) throws SQLException, ClassNotFoundException {
        String sql = "Select * from society_request sr, person p where sr.head_id = p.person_id and society_request_id = ?";
        createConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, Id);
        ResultSet rs =  ps.executeQuery();

        SocietyRequest societyRequest = new SocietyRequest();
        Person person = new Person();

        if(rs.next())
        {
            fillFields(rs, societyRequest, person);
            return societyRequest;
        }
       return null;

    }


}
