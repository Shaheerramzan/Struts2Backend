package com.rednet.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.rednet.dao.SocietyDAO;
import com.rednet.entities.Society;
import com.rednet.entities.SocietyRequest;

import java.util.ArrayList;

public class SocietiesAction extends ActionSupport {
    public ArrayList<Society> societies;

    int SocietyRequestId;

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

    public String create_society_from_request() throws Exception {
        String result = ERROR;

        try{
            SocietyDAO societyDAO = new SocietyDAO();
            SocietyRequest societyRequest = societyDAO.getSocietyRequestById(SocietyRequestId);
            societyDAO.createSociety(societyRequest.getName(), societyRequest.getPersonByHeadId().getPersonId());
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

    public int getSocietyRequestId() {
        return SocietyRequestId;
    }

    public void setSocietyRequestId(int societyRequestId) {
        SocietyRequestId = societyRequestId;
    }
}
