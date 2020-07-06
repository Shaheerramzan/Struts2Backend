package com.rednet.dao;

import com.rednet.entities.Donor;
import com.rednet.entities.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonorDAO {
    public Connection connection;

    public void createConnection() throws SQLException, ClassNotFoundException {
        if(connection != null) {
            return ;
        }
        String url = "jdbc:mysql://localhost:3306/rednet";
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, "root", "root");
    }
    public Donor createDonor(Date LastDonatedDate, Person person) throws SQLException, ClassNotFoundException {
        PersonDAO personDAO = new PersonDAO();
        personDAO.createPerson(person.getUsername(), person.getFirstName(), person.getLastName(), person.getPassword(), person.getEmail(), person.getPhone1(), person.getGender(), person.getCity(), person.getArea(), person.getBloodGroup());
        Donor donor = new Donor();
        donor.setLastDonatedDate(LastDonatedDate);
        donor.setPersonId(person);
        String sql = "INSERT INTO donor(last_donated_date, is_busy, system_mute, society_id, person_id) VALUES (last_donated_date, 0, )";
        createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return donor;
    }
    public ArrayList<Donor> getSocietyDonors(int society_id) throws SQLException, ClassNotFoundException {
        ArrayList<Donor> donors = new ArrayList<Donor>();
        String sql = "SELECT * FROM donor d, person p WHERE d.person_id=p.person_id AND society_id = ?";
        createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, society_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            Donor donor = new Donor();
            Person person = new Person();
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
            donor.setPersonId(person);
            donor.setDonorId(resultSet.getInt("donor_id"));
            donor.setSystemMute(resultSet.getByte("system_mute"));
            donor.setIsBusy(resultSet.getByte("is_busy"));
            donor.setLastDonatedDate(resultSet.getDate("last_donated_date"));
            donors.add(donor);
        }
        return donors;
    }
    public Donor getDonor(int id) throws SQLException, ClassNotFoundException {
        Donor donor = new Donor();
        Person person = new Person();
        String sql = "SELECT * FROM donor d, person p WHERE d.person_id = p.person_id AND donor_id = ?";
        createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
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
            donor.setPersonId(person);
            donor.setDonorId(resultSet.getInt("donor_id"));
            donor.setSystemMute(resultSet.getByte("system_mute"));
            donor.setIsBusy(resultSet.getByte("is_busy"));
            donor.setLastDonatedDate(resultSet.getDate("last_donated_date"));
        }
        return donor;
    }
}
