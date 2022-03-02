package com.meli.quasar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.meli.quasar.model.SatelliteDTO;
import com.meli.quasar.model.SatelliteEntity;
import com.meli.quasar.model.SatelliteList;
import com.meli.quasar.model.TrackerResponseEntity;
import com.meli.quasar.repository.SatelliteRepository;
import com.meli.quasar.service.SatelliteService;
import com.meli.quasar.service.TrackerLocationService;
import com.meli.quasar.service.TrackerMessageService;
import com.meli.quasar.service.exceptions.TrackerException;
import com.meli.quasar.utils.SatelliteMapUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class SatelliteServiceImpl implements SatelliteService {

	@Autowired
	private SatelliteRepository satelliteRepository;

	@Autowired
	private TrackerMessageService trackerMessageService;

	@Autowired
	private TrackerLocationService trackerLocationService;
	
	@Override
	public SatelliteDTO save(SatelliteEntity sat, String satelliteName) throws TrackerException {
		sat.setName(satelliteName);
		SatelliteDTO response = null;

			SatelliteEntity entity = satelliteRepository.saveAndFlush(sat);
			if(entity!= null) {
				response = SatelliteMapUtils.entity2DTO(entity);
			}else {
				log.error("impossible to process few connected salitelites");
				throw new TrackerException("impossible to process few connected salitelites",new Exception());
			}
	
			
	
	    
		return response;
	}

	@Override
	public TrackerResponseEntity getMessaseAndPosition() throws TrackerException {
		TrackerResponseEntity response = null;
		SatelliteList listSat = new SatelliteList();
		listSat.setSatellites(satelliteRepository.findAll());
		if (validateSatelites(listSat)) {
			response = new TrackerResponseEntity();
			response.setMessage(trackerMessageService.getmessage(listSat));
			response.setPosition(trackerLocationService.getLocation(listSat));
		}
		return response;
	}

	private boolean validateSatelites(SatelliteList listSat) throws TrackerException {
		boolean response = false;
		if (ObjectUtils.isEmpty(listSat.getSatellites()) 
				|| listSat.getSatellites().size() < 3) {
			throw new TrackerException("impossible to process few connected salitelites", new Exception()) ;
		}
		response = true;
		return response;
	}

}
