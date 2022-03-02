package com.meli.quasar.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.meli.quasar.model.Position;
import com.meli.quasar.model.SatelliteDTO;
import com.meli.quasar.model.SatelliteEntity;
import com.meli.quasar.model.SatelliteList;
import com.meli.quasar.model.TrackerResponseEntity;
import com.meli.quasar.repository.SatelliteRepository;
import com.meli.quasar.service.TrackerLocationService;
import com.meli.quasar.service.TrackerMessageService;
import com.meli.quasar.service.exceptions.TrackerException;

class SatelliteServiceImplTest {
	@InjectMocks
	SatelliteServiceImpl satelliteService;
	@Mock
	SatelliteRepository satelliteRepository;
	@Mock
	TrackerMessageService trackerMessageService;
	@Mock
	TrackerLocationService trackerLocationService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void saveEntityNullTest() throws TrackerException {
		String kenobi = "kenobi";
		SatelliteEntity entity = new SatelliteEntity();
		entity.setName(kenobi);
		when(satelliteRepository.saveAndFlush(entity)).thenReturn(null);
		try {
			satelliteService.save(entity, kenobi);
		} catch (Exception e) {
			assertEquals("impossible to process few connected salitelites", e.getMessage());
		}
	}

	@Test
	void saveEntityOKTest() throws TrackerException {
		SatelliteDTO response = null;
		String kenobi = "kenobi";
		SatelliteEntity entity = new SatelliteEntity();
		entity.setName(kenobi);
		entity.setDistance(1.0f);
		entity.setMessage("este es un mensaje".split(" "));
		when(satelliteRepository.saveAndFlush(entity)).thenReturn(entity);
		response = satelliteService.save(entity, kenobi);
		assertNotNull(response);
	}

	
	@Test
	void saveEntityMapNullTest() throws TrackerException {
		String kenobi = "kenobi";
		SatelliteEntity entity = new SatelliteEntity();
		entity.setName(kenobi);
		entity.setDistance(1.0f);
		entity.setMessage("este es un mensaje".split(" "));
		when(satelliteRepository.saveAndFlush(entity)).thenReturn(null);
		try {
			satelliteService.save(entity, kenobi);
		} catch (Exception e) {
			assertEquals("impossible to process few connected salitelites", e.getMessage());
		}
	}

	@Test
	void getMessaseAndPositionExceptionSatellitesTest() throws TrackerException {
		String kenobi = "kenobi";
		List<SatelliteEntity> entityList = new ArrayList<>();
		SatelliteEntity entity = new SatelliteEntity();
		entity.setName(kenobi);
		entity.setDistance(1.0f);
		entity.setMessage("este es un mensaje".split(" "));
		entityList.add(entity);
		when(satelliteRepository.findAll()).thenReturn(entityList);
		try {
			satelliteService.getMessaseAndPosition();
		} catch (Exception e) {
			assertEquals("impossible to process few connected salitelites", e.getMessage());
		}
	}

	@Test
	void getMessaseAndPosition3SatellitesTest() throws TrackerException {
		TrackerResponseEntity response = null;
		List<SatelliteEntity> entityList = new ArrayList<>();
		SatelliteEntity kenobiEntity = new SatelliteEntity();
		kenobiEntity.setName("kenobi");
		kenobiEntity.setDistance(1.0f);
		kenobiEntity.setMessage("este es un mensaje".split(" "));

		SatelliteEntity satoiEntity = new SatelliteEntity();
		satoiEntity.setName("kenobi");
		satoiEntity.setDistance(1.0f);
		satoiEntity.setMessage("este es un mensaje".split(" "));

		SatelliteEntity skywalkerEntity = new SatelliteEntity();
		skywalkerEntity.setName("kenobi");
		skywalkerEntity.setDistance(1.0f);
		skywalkerEntity.setMessage("este es un mensaje secreto".split(" "));
		entityList.add(kenobiEntity);
		entityList.add(satoiEntity);
		entityList.add(skywalkerEntity);

		SatelliteList list = new SatelliteList();
		list.setSatellites(entityList);
		Position pos = new Position();
		pos.setX(10.0);
		pos.setY(11.0);
		when(satelliteRepository.findAll()).thenReturn(entityList);
		when(trackerMessageService.getmessage(list)).thenReturn("este es un mensaje secreto");
		when(trackerLocationService.getLocation(list)).thenReturn(pos);

		response = satelliteService.getMessaseAndPosition();
		assertNull(response.getMessage());

	}
	
	@Test
	void getMessaseAndPositionSatellitesNullTest() throws TrackerException {
		when(satelliteRepository.findAll()).thenReturn(null);
		try {
			satelliteService.getMessaseAndPosition();
		} catch (Exception e) {
			assertEquals("impossible to process few connected salitelites", e.getMessage());
		}
	}


}
