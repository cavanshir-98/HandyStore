package com.onlinestore.onlinestore.repository;

import com.onlinestore.onlinestore.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {


    Optional<City> findByName(Long aLong);
}
