package com.meli.quasar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.meli.quasar.model.SatelliteEntity;

@RepositoryRestResource(collectionResourceRel = "satelliteEntity", path = "satelliteEntity")
public interface SatelliteRepository extends JpaRepository<SatelliteEntity, Long> {

}