package com.rednet.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.rednet.dao.DonorDAO;
import com.rednet.dao.SocietyAdminDAO;
import com.rednet.entities.SocietyAdmin;

public class SocietyAdminDetailAction extends ActionSupport {
    private int Id;
    private SocietyAdmin societyAdmin;
    @Override
    public String execute() throws Exception {
        String result = ERROR;

        try{
            SocietyAdminDAO societyAdminDAO = new SocietyAdminDAO();
            societyAdmin = societyAdminDAO.getSocietyAdmin(Id);
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

    public SocietyAdmin getSocietyAdmin() {
        return societyAdmin;
    }

    public void setSocietyAdmin(SocietyAdmin societyAdmin) {
        this.societyAdmin = societyAdmin;
    }
}
