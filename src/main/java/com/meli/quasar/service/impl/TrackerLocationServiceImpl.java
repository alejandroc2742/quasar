package com.meli.quasar.service.impl;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
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

	public Position calculateLocation() {

		double[][] positions = new double[][] { { kenobiX, kenobiY }, { satoX, satoY }, { skywalkerX, skywalkerY } };
		double[] distances = new double[] { kenobi.getDistance(), sato.getDistance(), skywalker.getDistance() };

		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
		Optimum optimum = solver.solve();
		double[] centroid = optimum.getPoint().toArray();
		return new Position(centroid[0], centroid[1]);
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
