package com.meli.quasar.service;

import com.meli.quasar.model.SatelliteList;
import com.meli.quasar.service.exceptions.TrackerException;

public interface TrackerMessageService {
	
	public String getmessage(SatelliteList satelliteList) throws TrackerException;

}
