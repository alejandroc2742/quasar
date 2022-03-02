package com.meli.quasar.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.meli.quasar.model.SatelliteEntity;
import com.meli.quasar.model.SatelliteList;
import com.meli.quasar.service.exceptions.TrackerException;

class TrackerMessageServiceImplTest {
	@InjectMocks
	TrackerMessageServiceImpl trackerMessageService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(trackerMessageService, "numSatellites", 3);
	}

	@Test
	void getmessageNullTest() {
		SatelliteList list = null;
		try {
			trackerMessageService.getmessage(list);
		} catch (TrackerException e) {
			assertEquals("message cannot be retrieved", e.getMessage());
		}
	}

	@Test
	void getmessageEmptyTest() {
		SatelliteList list = new SatelliteList();
		try {
			trackerMessageService.getmessage(list);
		} catch (TrackerException e) {
			assertEquals("message cannot be retrieved", e.getMessage());
		}
	}

	@Test
	void getmessageSatellite3Test() throws TrackerException {
		SatelliteList list = new SatelliteList();
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
		list.setSatellites(entityList);

		String response = trackerMessageService.getmessage(list);
		assertNotNull(response);

	}

	@Test
	void getmessageSatellite2Test() throws TrackerException {
		SatelliteList list = new SatelliteList();
		List<SatelliteEntity> entityList = new ArrayList<>();
		SatelliteEntity kenobiEntity = new SatelliteEntity();
		kenobiEntity.setName("kenobi");
		kenobiEntity.setDistance(1.0f);
		kenobiEntity.setMessage(" este".split(" "));

		SatelliteEntity satoiEntity = new SatelliteEntity();
		satoiEntity.setName("sato");
		satoiEntity.setDistance(1.0f);
		satoiEntity.setMessage("este es un mensaje".split(" "));

		entityList.add(kenobiEntity);
		entityList.add(satoiEntity);

		list.setSatellites(entityList);

		try {
			trackerMessageService.getmessage(list);
		} catch (TrackerException e) {
			assertEquals("message cannot be retrieved", e.getMessage());
		}

	}
	
	@Test
	void getmessageEmptyRecepTest() throws TrackerException {
		SatelliteList list = new SatelliteList();
		List<SatelliteEntity> entityList = new ArrayList<>();
		SatelliteEntity kenobiEntity = new SatelliteEntity();
		kenobiEntity.setName("kenobi");
		kenobiEntity.setDistance(1.0f);
		kenobiEntity.setMessage("".split(" "));

		SatelliteEntity satoiEntity = new SatelliteEntity();
		satoiEntity.setName("sato");
		satoiEntity.setDistance(1.0f);
		satoiEntity.setMessage("".split(" "));

		entityList.add(kenobiEntity);
		entityList.add(satoiEntity);

		list.setSatellites(entityList);

		try {
			trackerMessageService.getmessage(list);
		} catch (TrackerException e) {
			assertEquals("message cannot be retrieved", e.getMessage());
		}

	}

}
