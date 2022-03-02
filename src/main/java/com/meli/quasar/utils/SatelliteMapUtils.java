package com.meli.quasar.utils;

import com.meli.quasar.model.SatelliteDTO;
import com.meli.quasar.model.SatelliteEntity;

public class SatelliteMapUtils {

	
	public static SatelliteDTO entity2DTO(SatelliteEntity entity) {
		if (entity == null) {
			return null;
		}
		SatelliteDTO response = new SatelliteDTO();
		response.setDistance(entity.getDistance());
		response.setName(entity.getName() == null ? "" : entity.getName());
		response.setMessage(entity.getMessage() == null ? "" : String.join(" ", entity.getMessage()));
		return response;
	}

}
