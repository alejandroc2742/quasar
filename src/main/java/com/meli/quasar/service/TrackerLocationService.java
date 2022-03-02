package com.meli.quasar.service;

import com.meli.quasar.model.Position;
import com.meli.quasar.model.SatelliteList;
import com.meli.quasar.service.exceptions.TrackerException;

public interface TrackerLocationService {

	public Position getLocation(SatelliteList satelliteList) throws TrackerException;

}
