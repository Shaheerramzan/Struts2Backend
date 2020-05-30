package com.rednet.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.rednet.dao.SocietyDAO;
import com.rednet.entities.Society;

import java.util.ArrayList;

public class SocietiesAction extends ActionSupport {
    public ArrayList<Society> societies;

    @Override
    public String execute() throws Exception {
        String result = ERROR;

        try{
            SocietyDAO societyAdminDAO = new SocietyDAO();
            societies = societyAdminDAO.getSocieties();
            result = SUCCESS;
        }
        catch (Exception exception)
        {
            result = ERROR;
        }

        return result;
    }

    public ArrayList<Society> getSocieties() {
        return societies;
    }

    public void setSocieties(ArrayList<Society> societies) {
        this.societies = societies;
    }
}
