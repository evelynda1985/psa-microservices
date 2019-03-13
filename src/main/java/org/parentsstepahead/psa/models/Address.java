package org.parentsstepahead.psa.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAddress")
    private int idAddress;

    @Column(name = "street")
    private String street;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private int zip;

    //Constructors
    public Address() {
    }

    public Address(String address,String state,int zip) {
        this.street = address;
        this.state = state;
        this.zip = zip;
    }

    //getters and setters
    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public String getAddress() {
        return street;
    }

    public void setAddress(String address) {
        this.street = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    //String to string
    @Override
    public String toString() {
        return "Address [idAddress=" + idAddress + ", street=" + street + ", state=" + state + ", zip=" + zip + "]";
    }

}
