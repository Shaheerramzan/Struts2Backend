package com.rednet.dao;

import com.rednet.entities.Person;
import com.rednet.entities.SocietyAdmin;

import java.sql.*;
import java.util.ArrayList;

public class SocietyAdminDAO {
    public Connection connection;

    public void createConnection() throws SQLException, ClassNotFoundException {
        if(connection != null) {
            return ;
        }
        String url = "jdbc:mysql://localhost:3306/rednet";
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, "root", "root");
    }
    public ArrayList<SocietyAdmin> getSocietySocietyAdmins(int society_id) throws SQLException, ClassNotFoundException {
        ArrayList<SocietyAdmin> societyAdmins = new ArrayList<SocietyAdmin>();
        String sql = "SELECT * FROM society_admin s, person p, society st WHERE s.person_id=p.person_id AND s.society_id=st.society_id AND head_id = ?";
        createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, society_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            SocietyAdmin societyAdmin = new SocietyAdmin();
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
            societyAdmin.setPersonId(person);
            societyAdmin.setSocietyAdminId(resultSet.getInt("society_admin_id"));
            societyAdmins.add(societyAdmin);
        }
        return societyAdmins;
    }
    public SocietyAdmin getSocietyAdmin(int id) throws SQLException, ClassNotFoundException {
        SocietyAdmin societyAdmin = new SocietyAdmin();
        Person person = new Person();
        String sql = "SELECT * FROM society_admin d, person p WHERE d.person_id = p.person_id AND society_admin_id = ?";
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
            societyAdmin.setPersonId(person);
            societyAdmin.setSocietyAdminId(resultSet.getInt("society_admin_id"));
        }
        return societyAdmin;
    }
    public int getSocietyIdByPersonId(int id) throws SQLException, ClassNotFoundException {
        int societyId = 0;
        String sql = "SELECT society_id FROM society_admin d, person p WHERE d.person_id = p.person_id AND p.person_id = ?";
        createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            societyId = resultSet.getInt("society_id");
        }
        return societyId;
    }

    public boolean createSocietyAdmin(Person person, int societyId) throws SQLException, ClassNotFoundException
    {
        PersonDAO personDAO = new PersonDAO();
        int personId = personDAO.createPerson(person.getUsername(), person.getFirstName(), person.getLastName(), person.getPassword(), person.getEmail(), person.getPhone1(), person.getGender(), person.getCity(), person.getArea(), person.getBloodGroup());

        String sql = "INSERT INTO society_admin(person_id, society_id) VALUES (?, ?)";
        createConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, personId);
        ps.setInt(2, societyId);

        if(ps.executeUpdate() >= 1)
        {
            return true;
        }
        return false;
    }

    public boolean deleteSocietyAdmin(int id) throws SQLException {
        String sql = "DELETE FROM society_admin WHERE society_admin_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int resultSet = preparedStatement.executeUpdate();
        return resultSet == 1;
    }

}
