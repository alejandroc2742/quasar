package com.meli.quasar.business.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.meli.quasar.model.Position;
import com.meli.quasar.model.SatelliteDTO;
import com.meli.quasar.model.SatelliteEntity;
import com.meli.quasar.model.SatelliteList;
import com.meli.quasar.model.TrackerResponseEntity;
import com.meli.quasar.service.TrackerLocationService;
import com.meli.quasar.service.TrackerMessageService;
import com.meli.quasar.service.exceptions.TrackerException;
import com.meli.quasar.service.impl.SatelliteServiceImpl;

class TrackerControllerTest {

	@InjectMocks
	TrackerController trackerController;

	@Mock
	private TrackerMessageService trackerMessageService;

	@Mock
	private TrackerLocationService trackerLocationService;

	@Mock
	SatelliteServiceImpl satelliteService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	void getLocationOKTest() throws TrackerException {

		List<SatelliteEntity> entityList = new ArrayList<>();
		SatelliteEntity kenobiEntity = new SatelliteEntity();
		kenobiEntity.setName("kenobi");
		kenobiEntity.setDistance(1.0f);
		kenobiEntity.setMessage("este es un mensaje".split(" "));

		SatelliteEntity satoiEntity = new SatelliteEntity();
		satoiEntity.setName("sato");
		satoiEntity.setDistance(1.0f);
		satoiEntity.setMessage("este es un mensaje".split(" "));

		SatelliteEntity skywalkerEntity = new SatelliteEntity();
		skywalkerEntity.setName("skywalker");
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
		when(trackerMessageService.getmessage(list)).thenReturn("este es un mensaje secreto");
		when(trackerLocationService.getLocation(list)).thenReturn(pos);
		trackerController.getLocation(list);
	}
	
	
	@Test
	void getLocationExceptionTest() throws TrackerException {

		List<SatelliteEntity> entityList = new ArrayList<>();
		SatelliteEntity kenobiEntity = new SatelliteEntity();
		kenobiEntity.setName("kenobi");
		kenobiEntity.setDistance(1.0f);
		kenobiEntity.setMessage("este es un mensaje".split(" "));

		SatelliteEntity satoiEntity = new SatelliteEntity();
		satoiEntity.setName("sato");
		satoiEntity.setDistance(1.0f);
		satoiEntity.setMessage("este es un mensaje".split(" "));

		SatelliteEntity skywalkerEntity = new SatelliteEntity();
		skywalkerEntity.setName("skywalker");
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
		when(trackerMessageService.getmessage(list)).thenReturn("este es un mensaje secreto");
		when(trackerLocationService.getLocation(list)).thenThrow(new TrackerException("",new Exception()));
		trackerController.getLocation(list);
	}
	

	@Test
	void topSecretSplitSatelliteTest() throws TrackerException {
		ResponseEntity<SatelliteDTO> response = new ResponseEntity<>(HttpStatus.OK);

		SatelliteEntity satoiEntity = new SatelliteEntity();
		satoiEntity.setName("sato");
		satoiEntity.setDistance(1.0f);
		satoiEntity.setMessage("este es un mensaje".split(" "));

		Position pos = new Position();
		pos.setX(10.0);
		pos.setY(11.0);
		when(satelliteService.save(satoiEntity, "sato")).thenReturn(new SatelliteDTO());
		response = trackerController.topSecretSplitSatellite("sato", satoiEntity);
		assertNotNull(response);
	}

	@Test
	void topSecretSplitSatelliteRespNullTest() throws TrackerException {
		ResponseEntity<SatelliteDTO> response = new ResponseEntity<>(HttpStatus.OK);

		SatelliteEntity satoiEntity = new SatelliteEntity();
		satoiEntity.setName("sato");
		satoiEntity.setDistance(1.0f);
		satoiEntity.setMessage("este es un mensaje".split(" "));

		Position pos = new Position();
		pos.setX(10.0);
		pos.setY(11.0);
		when(satelliteService.save(satoiEntity, "sato")).thenReturn(null);
		response = trackerController.topSecretSplitSatellite("sato", satoiEntity);
		assertNotNull(response);
	}
	

	@Test
	void topSecretSplitSatelliteRespExcepTest() throws TrackerException {
		ResponseEntity<SatelliteDTO> response = new ResponseEntity<>(HttpStatus.OK);

		SatelliteEntity satoiEntity = new SatelliteEntity();
		satoiEntity.setName("sato");
		satoiEntity.setDistance(1.0f);
		satoiEntity.setMessage("este es un mensaje".split(" "));

		Position pos = new Position();
		pos.setX(10.0);
		pos.setY(11.0);
		when(satelliteService.save(satoiEntity, "sato")).thenThrow(new TrackerException("",new Exception()));
		response = trackerController.topSecretSplitSatellite("sato", satoiEntity);
	}

	@Test
	void topSecretSplitGetRequestTest() throws TrackerException {
		 ResponseEntity<TrackerResponseEntity> response = new ResponseEntity<>(HttpStatus.OK);
		 
		SatelliteEntity satoiEntity = new SatelliteEntity();
		satoiEntity.setName("sato");
		satoiEntity.setDistance(1.0f);
		satoiEntity.setMessage("este es un mensaje".split(" "));


		Position pos = new Position();
		pos.setX(10.0);
		pos.setY(11.0);
		when(satelliteService.getMessaseAndPosition()).thenReturn(new TrackerResponseEntity());
		response = trackerController.topSecretSplitGetRequest();
		assertNotNull(response);
	}
	
	
	@Test
	void topSecretSplitGetRequestExceptionTest() throws TrackerException {

		SatelliteEntity satoiEntity = new SatelliteEntity();
		satoiEntity.setName("sato");
		satoiEntity.setDistance(1.0f);
		satoiEntity.setMessage("este es un mensaje".split(" "));


		Position pos = new Position();
		pos.setX(10.0);
		pos.setY(11.0);
		when(satelliteService.getMessaseAndPosition()).thenThrow(new TrackerException("",new Exception()));
		trackerController.topSecretSplitGetRequest();
	}

}
