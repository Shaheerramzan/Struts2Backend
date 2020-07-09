package com.rednet.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.rednet.dao.DonorDAO;
import com.rednet.dao.SocietyAdminDAO;
import com.rednet.dao.SocietyDAO;
import com.rednet.dao.SocietyRequestDAO;

import java.sql.SQLException;

public class DeleteAction extends ActionSupport {
    private int Type;
    private int Id;

    public String execute() throws SQLException {
        if(Type == 1)
        {
            DonorDAO donorDAO = new DonorDAO();
            donorDAO.deleteDonor(Id);
        }
        if(Type == 2)
        {
            SocietyAdminDAO societyAdminDAO = new SocietyAdminDAO();
            societyAdminDAO.deleteSocietyAdmin(Id);
        }
        if(Type == 3)
        {
            SocietyDAO societyDAO = new SocietyDAO();
            societyDAO.deleteSociety(Id);
        }
        if(Type == 4)
        {
            SocietyRequestDAO societyRequestDAO = new SocietyRequestDAO();
            societyRequestDAO.deleteSocietyRequest(Id);
        }
        return SUCCESS;
    }

    public int getType() {
        return Type;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setType(int type) {
        Type = type;
    }
}
