package com.rednet.entities;

import javax.persistence.*;

@Entity
public class Society {
    private int societyId;
    private String name;
    private Person personId;

    @Id
    @Column(name = "society_id", nullable = false)
    public int getSocietyId() {
        return societyId;
    }

    public void setSocietyId(int societyId) {
        this.societyId = societyId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Society society = (Society) o;

        if (societyId != society.societyId) return false;
        return name != null ? name.equals(society.name) : society.name == null;
    }

    @Override
    public int hashCode() {
        int result = societyId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "head_id", referencedColumnName = "person_id", nullable = false)
    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }
}
