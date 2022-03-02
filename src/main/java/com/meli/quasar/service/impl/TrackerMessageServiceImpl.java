package com.meli.quasar.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.meli.quasar.model.SatelliteEntity;
import com.meli.quasar.model.SatelliteList;
import com.meli.quasar.service.TrackerMessageService;
import com.meli.quasar.service.exceptions.TrackerException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TrackerMessageServiceImpl implements TrackerMessageService {

	@Value("${satellites.number}")
	int numSatellites;

	@Override
	public String getmessage(SatelliteList satelliteList) throws TrackerException {
		String response = "";
		if (isValidMessage(satelliteList)) {
			response = buildMessage(satelliteList.getSatellites());
		} else {
			log.error("message cannot be retrieved");
			throw new TrackerException("message cannot be retrieved", new Exception());

		}
		if (ObjectUtils.isEmpty(response)) {
			throw new TrackerException("there is not enough information.", new Exception());
		}
		return response;
	}

	private boolean isValidMessage(SatelliteList satelliteList) {
		boolean isValid = false;
		if (satelliteList != null && satelliteList.getSatellites() != null
				&& satelliteList.getSatellites().size() >= numSatellites) {
			isValid = true;
		}
		return isValid;
	}

	private String buildMessage(List<SatelliteEntity> SatelliteEnties) throws TrackerException {
		List<String[]> toDigest = new ArrayList<String[]>();
		List<String> list = new ArrayList<>();
		for (SatelliteEntity entity : SatelliteEnties) {
			toDigest.add(entity.getMessage());
		}
		for (int i = 0; i < getMaxMessageLength(toDigest); i++) {
			for (String[] msg : toDigest) {
				if (i < msg.length && !ObjectUtils.isEmpty(msg[i]) && !list.contains(msg[i])) {
					list.add(msg[i]);
				}
			}
		}
		return String.join(" ", list);
	}

	private int getMaxMessageLength(List<String[]> list) throws TrackerException {
		int response = list.stream().mapToInt(i -> i.length).max().getAsInt();
		if (response < 1) {
			throw new TrackerException("message cannot be retrieved", new Exception());
		}
		return response;
	}
}
