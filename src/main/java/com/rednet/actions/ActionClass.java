package com.rednet.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ActionClass extends ActionSupport {
    private String firstname;
    private String lastname;
    private String Username;
    private String Password;

    public String execute()
    {
        String result = ERROR;
        Connection connection = null;

        try{
            String URL = "jdbc:mysql://localhost:3306/rednet";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "root");
            String sql = "SELECT first_name, last_name FROM person WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                firstname = resultSet.getString(1);
                lastname = resultSet.getString(2);
                result = SUCCESS;
            }
        }
       catch (Exception exception)
       {
           result = ERROR;
       }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception exception) {

                }
                {
                }
            }
        }

        return "success";
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPassword() {
        return Password;
    }

    public String getUsername() {
        return Username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
