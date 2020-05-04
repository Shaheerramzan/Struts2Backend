package com.rednet.actions;

import com.opensymphony.xwork2.ActionSupport;

import java.sql.*;

import com.rednet.dao.PersonDAO;
import com.rednet.entities.Person;

public class PersonDetailAction extends ActionSupport {

    public Person person;
    public Integer Id;

    @Override
    public String execute() throws Exception {
        String result = ERROR;

        try{
            PersonDAO personDAO = new PersonDAO();
            person = personDAO.getPerson(Id);
            result = SUCCESS;
        }
        catch (Exception exception)
        {
            result = ERROR;
        }

        return result;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
