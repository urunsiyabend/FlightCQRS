package com.urunsiyabend.flightscqrs.repositories;

import com.urunsiyabend.flightscqrs.entities.PlaneType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface PlaneTypeRepository extends JpaRepository<PlaneType, Long> {
    @Query("SELECT p FROM PlaneType p WHERE p.manufacturer = ?1 AND p.model = ?2")
    Collection<PlaneType> findByManufacturerAndModel(String manufacturer, String model);
}
