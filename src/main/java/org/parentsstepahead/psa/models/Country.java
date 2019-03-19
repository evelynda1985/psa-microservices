package org.parentsstepahead.psa.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCountry")
    private Long idCountry;

    @Size(max = 3, min = 2)
    @Column(name = "countryAbb")
    private String countryAbb;

    @Column(name = "countryName")
    private String countryName;

    public Country() {
    }

    public Country(Long idCountry, @Size(max = 3, min = 2) String countryAbb, String countryName) {
        this.countryAbb = countryAbb;
        this.countryName = countryName;
    }

    public Long getIdCountry() {
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

    public void setCountryAbb(String countryAbb) {
        this.countryAbb = countryAbb;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
