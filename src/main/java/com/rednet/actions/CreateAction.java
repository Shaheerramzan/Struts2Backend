package com.rednet.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.rednet.dao.DonorDAO;
import com.rednet.dao.SocietyAdminDAO;
import com.rednet.dao.SocietyDAO;
import com.rednet.dao.SocietyRequestDAO;
import com.rednet.entities.Donor;
import com.rednet.entities.Person;
import java.sql.Date;
import java.sql.SQLException;

public class CreateAction extends ActionSupport {
    private String Username;
    private String FirstName;
    private String LastName;
    private String Password;
    private String Email;
    private String PhoneNumber;
    private String Gender;
    private String City;
    private String Area;
    private String BloodGroup;
    private Date LastDonatedDate;

    private String SocietyName;
    private String SocietyDescription;

    private int Type;
    private int SocietyId;
    private int donateMonths;


    public String execute() throws SQLException, ClassNotFoundException {
        String result = ERROR;
        Person person = new Person();
        person.setFirstName(FirstName);
        person.setLastName(LastName);
        person.setArea(Area);
        person.setCity(City);
        person.setBloodGroup(BloodGroup);
        person.setCreatedOn(new Date(System.currentTimeMillis()));
        person.setEmail(Email);
        person.setPassword(Password);
        person.setPhone1(PhoneNumber);
        person.setGender(Gender);
        person.setUsername(Username);
        if(Type == 1) //donor
        {
            DonorDAO donorDAO = new DonorDAO();
            donorDAO.createDonor(LastDonatedDate, donateMonths, person, SocietyId);
        }
        else if(Type == 2) //society admin
        {
            SocietyAdminDAO societyAdminDAO = new SocietyAdminDAO();
            societyAdminDAO.createSocietyAdmin(person, SocietyId);
        }
        else if(Type == 3) //society Request
        {
            SocietyRequestDAO societyRequestDAO = new SocietyRequestDAO();
            societyRequestDAO.createSocietyRequest(SocietyName, SocietyDescription, person);
        }
        return SUCCESS;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public Date getLastDonatedDate() {
        return LastDonatedDate;
    }

    public void setLastDonatedDate(Date lastDonatedDate) {
        LastDonatedDate = lastDonatedDate;
    }

    public int getSocietyId() {
        return SocietyId;
    }

    public void setSocietyId(int societyId) {
        SocietyId = societyId;
    }

    public void setDonateMonths(int donateMonths) {
        this.donateMonths = donateMonths;
    }

    public int getDonateMonths() {
        return donateMonths;
    }

    public String getSocietyName() {
        return SocietyName;
    }

    public void setSocietyName(String societyName) {
        SocietyName = societyName;
    }

    public String getSocietyDescription() {
        return SocietyDescription;
    }

    public void setSocietyDescription(String societyDescription) {
        SocietyDescription = societyDescription;
    }
}
