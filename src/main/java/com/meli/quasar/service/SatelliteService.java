package com.meli.quasar.service;

import com.meli.quasar.model.SatelliteDTO;
import com.meli.quasar.model.SatelliteEntity;
import com.meli.quasar.model.TrackerResponseEntity;
import com.meli.quasar.service.exceptions.TrackerException;

public interface SatelliteService {

	SatelliteDTO save(SatelliteEntity entity, String satelliteName) throws TrackerException;

	TrackerResponseEntity getMessaseAndPosition() throws TrackerException;
}
