package com.meli.quasar.model;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackerResponseEntity {
	private String message;
	@Column
	private Position position;
}
