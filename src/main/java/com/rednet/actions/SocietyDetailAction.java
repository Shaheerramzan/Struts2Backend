package com.rednet.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.rednet.dao.SocietyDAO;
import com.rednet.entities.Society;

public class SocietyDetailAction extends ActionSupport {
    private int Id;
    private Society societyAdmin;
    @Override
    public String execute() throws Exception {
        String result = ERROR;

        try{
            SocietyDAO societyDAO = new SocietyDAO();
            societyAdmin = societyDAO.getSociety(Id);
            result = SUCCESS;
        }
        catch (Exception exception)
        {
            result = ERROR;
        }

        return result;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setSocietyAdmin(Society societyAdmin) {
        this.societyAdmin = societyAdmin;
    }

    public Society getSocietyAdmin() {
        return societyAdmin;
    }
}
