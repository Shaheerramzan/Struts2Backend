package com.rednet.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.rednet.dao.SocietyDAO;
import com.rednet.dao.SocietyRequestDAO;
import com.rednet.entities.Society;
import com.rednet.entities.SocietyRequest;

import java.util.ArrayList;

public class SocietyRequestAction extends ActionSupport {

    public ArrayList<SocietyRequest> societyRequests;

    int SocietyRequestId;

    @Override
    public String execute() throws Exception {
        String result = ERROR;

        try{
            SocietyRequestDAO societyRequestDAO = new SocietyRequestDAO();
            societyRequests = societyRequestDAO.getSocietiesRequests();
            result = SUCCESS;
        }
        catch (Exception exception)
        {
            result = ERROR;
        }

        return result;
    }

    public int getSocietyRequestId() {
        return SocietyRequestId;
    }

    public void setSocietyRequestId(int societyRequestId) {
        SocietyRequestId = societyRequestId;
    }



}
