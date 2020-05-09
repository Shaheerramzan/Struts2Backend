package com.rednet.entities;

import javax.persistence.*;

@Entity
@Table(name = "society_admin", schema = "rednet")
public class SocietyAdmin {
    private int societyAdminId;
    private Person PersonId;

    @Id
    @Column(name = "society_admin_id", nullable = false)
    public int getSocietyAdminId() {
        return societyAdminId;
    }

    public void setSocietyAdminId(int societyAdminId) {
        this.societyAdminId = societyAdminId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocietyAdmin that = (SocietyAdmin) o;

        return societyAdminId == that.societyAdminId;
    }

    @Override
    public int hashCode() {
        return societyAdminId;
    }

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = false)
    public Person getPersonId() {
        return PersonId;
    }

    public void setPersonId(Person personId) {
        PersonId = personId;
    }
}
