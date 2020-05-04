package com.rednet.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Donor {
    private Integer donorId;
    private Date lastDonatedDate;
    private Byte isBusy;
    private Byte systemMute;
    private Person PersonId;

    @Id
    @Column(name = "donor_id", nullable = false)
    public Integer getDonorId() {
        return donorId;
    }

    public void setDonorId(Integer donorId) {
        this.donorId = donorId;
    }

    @Basic
    @Column(name = "last_donated_date", nullable = true)
    public Date getLastDonatedDate() {
        return lastDonatedDate;
    }

    public void setLastDonatedDate(Date lastDonatedDate) {
        this.lastDonatedDate = lastDonatedDate;
    }

    @Basic
    @Column(name = "is_busy", nullable = true)
    public Byte getIsBusy() {
        return isBusy;
    }

    public void setIsBusy(Byte isBusy) {
        this.isBusy = isBusy;
    }

    @Basic
    @Column(name = "system_mute", nullable = true)
    public Byte getSystemMute() {
        return systemMute;
    }

    public void setSystemMute(Byte systemMute) {
        this.systemMute = systemMute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Donor donor = (Donor) o;

        if (!donorId.equals(donor.donorId)) return false;
        if (lastDonatedDate != null ? !lastDonatedDate.equals(donor.lastDonatedDate) : donor.lastDonatedDate != null)
            return false;
        if (isBusy != null ? !isBusy.equals(donor.isBusy) : donor.isBusy != null) return false;
        return systemMute != null ? systemMute.equals(donor.systemMute) : donor.systemMute == null;
    }

    @Override
    public int hashCode() {
        int result = donorId != null ? donorId.hashCode() : 0;
        result = 31 * result + (lastDonatedDate != null ? lastDonatedDate.hashCode() : 0);
        result = 31 * result + (isBusy != null ? isBusy.hashCode() : 0);
        result = 31 * result + (systemMute != null ? systemMute.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = false)
    public Person getPersonId() {
        return PersonId;
    }

    public void setPersonId(Person PersonId) {
        this.PersonId = PersonId;
    }
}
