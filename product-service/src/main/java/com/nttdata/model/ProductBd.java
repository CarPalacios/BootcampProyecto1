package com.nttdata.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Product")
public class ProductBd {

	protected ProductBd() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String productType;
	private String productName;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "conditionId")
	private Condition condition;

	public ProductBd(String productType, String productName, Condition condition) {
		this.productType = productType;
		this.productName = productName;
		this.condition = condition;
	}

}
