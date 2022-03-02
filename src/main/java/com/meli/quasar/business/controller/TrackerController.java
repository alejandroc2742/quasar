package com.meli.quasar.business.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meli.quasar.model.SatelliteDTO;
import com.meli.quasar.model.SatelliteEntity;
import com.meli.quasar.model.SatelliteList;
import com.meli.quasar.model.TrackerResponseEntity;
import com.meli.quasar.service.SatelliteService;
import com.meli.quasar.service.TrackerLocationService;
import com.meli.quasar.service.TrackerMessageService;
import com.meli.quasar.service.exceptions.TrackerException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TrackerController {

	@Autowired
	private TrackerMessageService trackerMessageService;

	@Autowired
	private TrackerLocationService trackerLocationService;

	@Autowired
	private SatelliteService satelliteService;

	@PostMapping
	@CrossOrigin
	@RequestMapping(path = "/topsecret")
	public @ResponseBody ResponseEntity<TrackerResponseEntity> getLocation(
			@Valid @NotNull(message = "SATELLITE REQUIRED") @RequestBody SatelliteList listSatellites) {
		log.info("init topsecret--->");
		TrackerResponseEntity response = new TrackerResponseEntity();
		try {
			response.setMessage(trackerMessageService.getmessage(listSatellites));
			response.setPosition(trackerLocationService.getLocation(listSatellites));
		} catch (TrackerException e) {
			log.error(e.getMessage());
			response = new TrackerResponseEntity();
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		log.info("topsecret message recovered--->" + response.getMessage());
		log.info("close topsecret <---");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@CrossOrigin
	@PostMapping(value = "/topsecret_split/{satellite_name}")
	public @ResponseBody ResponseEntity<SatelliteDTO> topSecretSplitSatellite(
			@Valid @NotNull(message = "NAME REQUIRED") @PathVariable("satellite_name") String satelliteName,
			@RequestBody SatelliteEntity satellite) {
		SatelliteDTO response = null;
		try {
			log.info("init topsecret_split post--->");
			response = satelliteService.save(satellite, satelliteName);
			log.info("close topsecret_split post<---");
		} catch (TrackerException e) {
			log.error(e.getMessage());
			response = new SatelliteDTO();
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		if (response == null) {
			response = new SatelliteDTO();
			response.setMessage("there is not enough information");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@CrossOrigin
	@GetMapping(value = "/topsecret_split")
	public @ResponseBody ResponseEntity<TrackerResponseEntity> topSecretSplitGetRequest() {
		TrackerResponseEntity response = null;
		try {
			response = satelliteService.getMessaseAndPosition();
		} catch (TrackerException e) {
			log.error(e.getMessage());
			response = new TrackerResponseEntity();
			response.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
