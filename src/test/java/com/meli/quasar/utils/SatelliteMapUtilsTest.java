package com.meli.quasar.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.meli.quasar.business.controller.TrackerController;
import com.meli.quasar.model.SatelliteEntity;

class SatelliteMapUtilsTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void entity2DTONullTest() {
		assertNull(SatelliteMapUtils.entity2DTO(null));
	}
	
	@Test
	void entity2DTOEmtpyTest() {
		SatelliteEntity entity = new SatelliteEntity();
		assertNotNull(SatelliteMapUtils.entity2DTO(entity));
	}
	
	@Test
	void entity2DTOWithValuesTest() {
		SatelliteEntity entity = new SatelliteEntity();
		entity.setMessage("test message".split(" "));
		entity.setName("name");
		assertNotNull(SatelliteMapUtils.entity2DTO(entity));
	}

}
