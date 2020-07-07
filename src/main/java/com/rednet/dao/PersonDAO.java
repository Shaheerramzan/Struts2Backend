package com.rednet.dao;

import com.rednet.entities.Person;

import java.sql.*;
import java.util.Map;

public class PersonDAO {
    public Connection connection;

    public void createConnection() throws SQLException, ClassNotFoundException {
        if(connection != null) {
            return ;
        }
        String url = "jdbc:mysql://localhost:3306/rednet";
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, "root", "root");
    }
    public int createPerson( String Username, String FirstName, String LastName, String Password, String Email, String PhoneNumber, String Gender, String City, String Area, String BloodGroup) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO rednet.person(person.username, person.password, person.email, person.first_name, person.last_name, person.gender, person.city, person.area, person.blood_group, person.phone1) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, Username);
        preparedStatement.setString(2, Password);
        preparedStatement.setString(3, Email);
        preparedStatement.setString(4, FirstName);
        preparedStatement.setString(5, LastName);
        preparedStatement.setString(6, Gender);
        preparedStatement.setString(7, City);
        preparedStatement.setString(8, Area);
        preparedStatement.setString(9, BloodGroup);
        preparedStatement.setString(10, PhoneNumber);
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        return resultSet.getInt(1);
    }
    public Person getPerson(int id) throws SQLException, ClassNotFoundException {
        Person person = new Person();
        String sql = "SELECT * FROM person WHERE person_id = ?";
        createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            person.setPersonId(resultSet.getInt("person_id"));
            person.setUsername(resultSet.getString("username"));
            person.setFirstName(resultSet.getString("last_name"));
            person.setLastName(resultSet.getString("first_name"));
            person.setEmail(resultSet.getString("email"));
            person.setGender(resultSet.getString("gender"));
            person.setBloodGroup(resultSet.getString("blood_group"));
            person.setLatitude(resultSet.getDouble("latitude"));
            person.setLongitude(resultSet.getDouble("longitude"));
            person.setPhone1(resultSet.getString("phone1"));
            person.setCity(resultSet.getString("city"));
            person.setArea(resultSet.getString("area"));
        }
        return person;
    }
    public Person
    personAuthentication(String email, String password, int Role) throws SQLException, ClassNotFoundException {
        Person person = new Person();
        String sql;
        switch(Role)
        {
            case 1:
                sql = "SELECT * FROM person p, society_admin s WHERE p.person_id = s.person_id AND p.username = ? AND p.password = ?";
                break;
            case 2:
                sql = "SELECT * FROM person p, rednet.society s WHERE p.person_id = s.head_id AND p.username = ? AND p.password = ?";
                break;
            case 3:
                sql = "SELECT * FROM person p, super_admin s WHERE p.person_id = s.person_id AND p.username = ? AND p.password = ?";
                break;
            default:
                sql = "";
        }
        createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            person.setPersonId(resultSet.getInt("person_id"));
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
        }
        return person;
    }


    public Person
    personMobileAuthentication(String phone, String password, int Role) throws SQLException, ClassNotFoundException {
        Person person = new Person();
        String sql;
        switch(Role)
        {
            case 1:
                sql = "SELECT * FROM person p, donor d WHERE p.person_id = d.person_id AND p.phone1 = ? AND p.password = ?";
                break;
            case 2:
                sql = "SELECT * FROM person p, conveyance_provider cp WHERE p.person_id = cp.person_id AND p.phone1 = ? AND p.password = ?";
                break;
            case 3:
                sql = "SELECT * FROM person p, conveyance_provider cp, donor p, WHERE p.person_id = d.person_id AND " +
                        "p.person_id = cp.person_id AND p.phone1 = ? AND p.password = ?";
                break;
            default:
                sql = "";
        }
        createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, phone);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            person.setPersonId(resultSet.getInt("person_id"));
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
        }
        return person;
    }

}