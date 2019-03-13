package org.parentsstepahead.psa.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "child")
public class Child implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idChild")
    private int idChild;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "gender")
    private String gender;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "idSchool")
    private School school;

    //Constructors
    public Child() {
    }

    public Child(String firstName,String gender,School school) {
        this.firstName = firstName;
        this.gender = gender;
        this.school = school;
    }

    //Getters and setters
    public int getIdChild() {
        return idChild;
    }

    public void setIdChild(int idChild) {
        this.idChild = idChild;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    //String to String
    @Override
    public String toString() {
        return "Child [idChild=" + idChild + ", firstName=" + firstName + ", gender=" + gender + ", address=" + "]";
    }

}
