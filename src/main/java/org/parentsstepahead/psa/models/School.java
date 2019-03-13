package org.parentsstepahead.psa.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "school")
public class School implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSchool")
    private int idSchool;

    @Column(name = "year")
    private String year;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "school",
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Child> childrenList;

    //Constructors
    public School() {
    }

    public School(String year,String name,String city) {
        this.year = year;
        this.name = name;
        this.city = city;
    }

    //Getters and setters
    public int getIdSchool() {
        return idSchool;
    }

    public void setIdSchool(int idSchool) {
        this.idSchool = idSchool;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Child> getChildreList() {
        return childrenList;
    }

    public void setChildreList(List<Child> childreList) {
        this.childrenList = childreList;
    }

    //String to string
    @Override
    public String toString() {
        return "School [idSchool=" + idSchool + ", year=" + year + ", name=" + name + ", city=" + city + "]";
    }

    //add convenience method
    public void add(Child theChild) {
        if (childrenList == null) {
            childrenList = new ArrayList<>();
        }

        childrenList.add(theChild);
    }

}
