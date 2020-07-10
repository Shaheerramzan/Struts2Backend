package com.rednet.actions;

import com.opensymphony.xwork2.ActionSupport;

import com.rednet.dao.DonorDAO;
import com.rednet.entities.Donor;

import java.util.ArrayList;
import java.util.List;

public class DonorDetailAction extends ActionSupport {

    public Donor donor;
    public Integer Id;

    public String BloodGroup;
    public int NoOfBottles;


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

    public String donors_by_bloodgroup()throws Exception {
        String result = ERROR;
        List<Donor> donorList = new ArrayList<Donor>();

        try{
            DonorDAO donorDAO = new DonorDAO();
            donorList = donorDAO.getDonorsByBloodGroup(BloodGroup);
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

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public int getNoOfBottles() {
        return NoOfBottles;
    }

    public void setNoOfBottles(int noOfBottles) {
        NoOfBottles = noOfBottles;
    }
}
