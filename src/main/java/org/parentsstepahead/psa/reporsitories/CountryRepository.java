package org.parentsstepahead.psa.reporsitories;

import org.parentsstepahead.psa.models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    public List<Country> findAll();

    public List<Country> findByCountryName(String countryName);

    public List<Country> findByCountryNameStartingWith(String countryInitialName);

}
