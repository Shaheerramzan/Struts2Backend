package com.rednet.actions;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;

import com.rednet.dao.DonorDAO;
import com.rednet.entities.Donor;
import com.rednet.entities.Person;

public class SocietyDonorsAction extends ActionSupport {

    public ArrayList<Donor> donors;
    public Integer Id;

    @Override
    public String execute() throws Exception {
        String result = ERROR;

        try{
            DonorDAO donorDAO = new DonorDAO();
            donors = donorDAO.getSocietyDonors(Id);
            result = SUCCESS;
        }
        catch (Exception exception)
        {
            result = ERROR;
        }

        return result;
    }

    public ArrayList<Donor> getDonors() {
        return donors;
    }

    public void setDonors(ArrayList<Donor> donors) {
        this.donors = donors;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
