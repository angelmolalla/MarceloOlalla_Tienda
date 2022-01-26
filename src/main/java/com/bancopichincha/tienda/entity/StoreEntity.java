package com.bancopichincha.tienda.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "STORE")
public class StoreEntity {

	@Id
	@Column(name = "STORE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, name = "STORE_CODE")
	private String cod;
	
	@Column(name = "STORE_NAME")
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "STOREPRODUCT", joinColumns = @JoinColumn(name = "STORE_ID"), inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "STORE_ID", "PRODUCT_ID" }) })
	private List<ProductEntity> listProduct;
}
