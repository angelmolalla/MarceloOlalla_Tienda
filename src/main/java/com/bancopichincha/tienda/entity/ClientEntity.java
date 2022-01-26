package com.bancopichincha.tienda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CLIENT")
public class ClientEntity {

	@Id
	@Column(name = "CLIENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CLIENT_NUMBER_IDENTIFICATION",unique = true)
	private String numberIdentification;
	
	@Column(name = "CLIENT_NAME",nullable = false)
	private String name;
	
	@Column(name = "CLIENT_PHOTO")
	private String photo;
	
	@Column(name = "CLIENT_STATE")
	private Boolean state;
	
}
