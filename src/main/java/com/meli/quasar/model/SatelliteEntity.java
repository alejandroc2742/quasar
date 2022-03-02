package com.meli.quasar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "satellite")
public class SatelliteEntity {
	@Column
	private @Id @GeneratedValue Long id;
	@Column
	private float distance;
	@Column
	private String name;
	@Column
	private String[] message;

}
