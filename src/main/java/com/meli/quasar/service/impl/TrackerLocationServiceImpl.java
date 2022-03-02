package com.meli.quasar.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meli.quasar.model.Position;
import com.meli.quasar.model.SatelliteEntity;
import com.meli.quasar.model.SatelliteList;
import com.meli.quasar.service.TrackerLocationService;
import com.meli.quasar.service.exceptions.TrackerException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TrackerLocationServiceImpl implements TrackerLocationService {
	// https://www.101computing.net/cell-phone-trilateration-algorithm/
	@Value("${position.kenobi.x}")
	Float kenobiX;
	@Value("${position.kenobi.y}")
	Float kenobiY;
	@Value("${position.sato.x}")
	Float satoX;
	@Value("${position.sato.y}")
	Float satoY;
	@Value("${position.skywalker.x}")
	Float skywalkerX;
	@Value("${position.skywalker.y}")
	Float skywalkerY;
	@Value("${satellites.number}")
	int numSatellites;

	SatelliteEntity kenobi;
	SatelliteEntity sato;
	SatelliteEntity skywalker;

	@Override
	public Position getLocation(SatelliteList satelliteList) throws TrackerException {
		if (isValidLocations(satelliteList)) {
			initSatellites(satelliteList);
			return calculateLocation();
		} else {
			log.error("there is not enough information");
			throw new TrackerException("there is not enough information", new Exception());
		}
	}

	private boolean isValidLocations(SatelliteList satelliteList) {
		boolean isValid = false;
		if (satelliteList != null && satelliteList.getSatellites() != null
				&& satelliteList.getSatellites().size() >= numSatellites) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * Satellite 1 = kenobi Satellite 2 = sato Satellite 3 = skywalker
	 */
	public Position calculateLocation() {
		float A, B, D, E = 0;
		double C, F = 0;
		A = 2 * satoX - 2 * kenobiX;
		B = 2 * satoY - 2 * kenobiY;
		C = Math.pow(kenobi.getDistance(), 2) - Math.pow(sato.getDistance(), 2) - Math.pow(kenobiX, 2)
				+ Math.pow(satoX, 2) - Math.pow(kenobiY, 2) + Math.pow(satoY, 2);
		D = 2 * skywalkerX - 2 * satoX;
		E = 2 * skywalkerY - 2 * satoY;
		F = Math.pow(sato.getDistance(), 2) - Math.pow(skywalker.getDistance(), 2) - Math.pow(satoX, 2)
				+ Math.pow(skywalkerX, 2) - Math.pow(satoY, 2) + Math.pow(skywalkerY, 2);
		double x = (C * E - F * B) / (E * A - B * D);
		double y = (C * D - A * F) / (B * D - A * E);
		return new Position(x, y);
	}

	private void initSatellites(SatelliteList satelliteList) throws TrackerException {
		kenobi = satelliteList.getSatellites().stream()
				.filter(satelliteEntity -> "kenobi".equals(satelliteEntity.getName())).findFirst().orElse(null);
		sato = satelliteList.getSatellites().stream()
				.filter(satelliteEntity -> "sato".equals(satelliteEntity.getName())).findFirst().orElse(null);
		skywalker = satelliteList.getSatellites().stream()
				.filter(satelliteEntity -> "skywalker".equals(satelliteEntity.getName())).findFirst().orElse(null);
		if (kenobi == null) {
			throw new TrackerException("the satellite are not ready : kenobi", new Exception());
		}
		if (sato == null) {
			throw new TrackerException("the satellite are not ready : sato", new Exception());
		}
		if (skywalker == null) {
			throw new TrackerException("the satellite are not ready : skywalker", new Exception());
		}
	}

}
