package org.parentsstepahead.psa.reporsitories;

import org.parentsstepahead.psa.models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    List<Country> findAll();

    Optional<Country> findByIdCountry(Long id);

    boolean existsByIdCountry(Long id);

    //save is predetermine by default I don't need to create save here!

    public List<Country> findByCountryName(String countryName);

    public List<Country> findByCountryNameStartingWith(String countryInitialName);
}
