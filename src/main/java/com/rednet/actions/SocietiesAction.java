package com.rednet.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.rednet.dao.SocietyAdminDAO;
import com.rednet.entities.SocietyAdmin;

import java.util.ArrayList;

public class SocietiesAction extends ActionSupport {
    public ArrayList<SocietyAdmin> societyAdmins;
    public Integer Id;

    @Override
    public String execute() throws Exception {
        String result = ERROR;

        try{
            SocietyAdminDAO societyAdminDAO = new SocietyAdminDAO();
            societyAdmins = societyAdminDAO.getSocietySocietyAdmins(Id);
            result = SUCCESS;
        }
        catch (Exception exception)
        {
            result = ERROR;
        }

        return result;
    }

    public ArrayList<SocietyAdmin> getSocietyAdmins() {
        return societyAdmins;
    }

    public void setSocietyAdmins(ArrayList<SocietyAdmin> societyAdmins) {
        this.societyAdmins = societyAdmins;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
