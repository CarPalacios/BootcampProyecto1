package com.nttdata.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Transaction")
public class TransactionBd {
	
	protected TransactionBd() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String transactionType;

	private Double transactionAmount;
	
	private String cardNumber;

	private String description;

	private LocalDateTime transactionDate = LocalDateTime.now();
	
}
