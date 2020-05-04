package com.rednet.actions;

import com.opensymphony.xwork2.ActionSupport;

import com.rednet.dao.DonorDAO;
import com.rednet.entities.Donor;

public class DonorDetailAction extends ActionSupport {

    public Donor donor;
    public Integer Id;

    @Override
    public String execute() throws Exception {
        String result = ERROR;

        try{
            DonorDAO donorDAO = new DonorDAO();
            donor = donorDAO.getDonor(Id);
            result = SUCCESS;
        }
        catch (Exception exception)
        {
            result = ERROR;
        }

        return result;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
