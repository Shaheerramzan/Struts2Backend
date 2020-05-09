package com.rednet.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.rednet.dao.PersonDAO;
import com.rednet.entities.Person;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.*;
import java.util.Map;

public class LoginAction extends ActionSupport implements SessionAware {
    private String Username;
    private String Password;
    private int Role;
    private SessionMap<String, Object> sessionMap;
    private String JSessionId;
    private Person person = null;


    public void setSession(Map<String, Object> session) {
        sessionMap= (SessionMap<String, Object>) session;
    }

    public String execute() throws SQLException, ClassNotFoundException {
        HttpSession session = ServletActionContext.getRequest().getSession();
        PersonDAO personDAO = new PersonDAO();
        person = personDAO.personAuthentication(Username, Password, Role);
        if(person.getPersonId() != null)
        {
            if(!sessionMap.containsKey("person")) {
                JSessionId = session.getId();
                sessionMap.put("person", person);
                sessionMap.put("Role", Role);
                sessionMap.put("session", session.getId());
            }
            else
            {
                JSessionId = session.getId();
            }
        }
        return SUCCESS;
    }
    public String logout() {
        if(sessionMap != null) {
            sessionMap.invalidate();
            return SUCCESS;
        }
        return ERROR;
    }

    public void setRole(int role) {
        Role = role;
    }

    public int getRole() {
        return Role;
    }

    public String getJSessionId() {
        return JSessionId;
    }

    public void setJSessionId(String JSessionId) {
        this.JSessionId = JSessionId;
    }

    public String getPassword() {
        return Password;
    }

    public String getUsername() {
        return Username;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
