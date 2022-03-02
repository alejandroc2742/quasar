package com.meli.quasar.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.test.util.ReflectionTestUtils;

import com.meli.quasar.model.Position;
import com.meli.quasar.model.SatelliteEntity;
import com.meli.quasar.model.SatelliteList;
import com.meli.quasar.service.exceptions.TrackerException;

//@TestPropertySource(properties = {
//	    "position.kenobi.x=500.1",
//	    "position.sato.x=500.0",
//	})
class TrackerLocationServiceImplTest {

	@InjectMocks
	TrackerLocationServiceImpl trackerLocationService;
	
	 @Mock 
	 Environment env;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(trackerLocationService, "kenobiX", -500f);
		ReflectionTestUtils.setField(trackerLocationService, "satoX", 500f);
		ReflectionTestUtils.setField(trackerLocationService, "skywalkerX", 100f);
		ReflectionTestUtils.setField(trackerLocationService, "kenobiY",-200f);
		ReflectionTestUtils.setField(trackerLocationService, "satoY", 100f);
		ReflectionTestUtils.setField(trackerLocationService, "skywalkerY", -100f);
		
	}

	@Test
	void getLocationExceptionTest() throws TrackerException {
		SatelliteList satelliteList = null;
		try {
			trackerLocationService.getLocation(satelliteList);
		} catch (Exception e) {
			assertEquals("there is not enough information", e.getMessage());
		}

	}

	@Test
	void getLocationOkTest() throws TrackerException {
		SatelliteList satelliteList = new SatelliteList();
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
		satelliteList.setSatellites(entityList);
		Position response = trackerLocationService.getLocation(satelliteList);
		assertNotNull(response);

	}

}
