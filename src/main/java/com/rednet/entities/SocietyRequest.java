package com.rednet.entities;

import javax.persistence.*;

@Entity
@Table(name = "society_request", schema = "rednet", catalog = "")
public class SocietyRequest {
    private int societyRequestId;
    private String name;
    private String description;
    private Person personByHeadId;

    @Id
    @Column(name = "society_request_id", nullable = false)
    public int getSocietyRequestId() {
        return societyRequestId;
    }

    public void setSocietyRequestId(int societyRequestId) {
        this.societyRequestId = societyRequestId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocietyRequest that = (SocietyRequest) o;

        if (societyRequestId != that.societyRequestId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = societyRequestId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "head_id", referencedColumnName = "person_id", nullable = false)
    public Person getPersonByHeadId() {
        return personByHeadId;
    }

    public void setPersonByHeadId(Person personByHeadId) {
        this.personByHeadId = personByHeadId;
    }
}
