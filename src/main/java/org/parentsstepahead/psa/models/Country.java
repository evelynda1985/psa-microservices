package org.parentsstepahead.psa.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCountry")
    private int idCountry;

    @NotBlank(message = "Abbreviation of the country cannot be empty")
    @Size(max = 3, min = 2)
    @Column(name = "countryAbb")
    private String countryAbb;

    @NotBlank(message = "Name of the country cannot be empty")
    @Column(name = "countryName")
    private String countryName;

    public Country() {
    }

    public int getIdCountry() {
        return idCountry;
    }

    public String getCountryAbb() {
        return countryAbb;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public String toString() {
        return "Country [idCountry=" + idCountry + ", countryAbb=" + countryAbb + ", countryName=" + countryName + "]";
    }


}
