package com.nttdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ClientBd {
	
	protected ClientBd() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String lastname;
	private String type;
	@Column(name = "dni")
	private String identityNumber;
	
	
	public ClientBd(String name, String lastname, String type, String identityNumber) {
		this.name = name;
		this.lastname = lastname;
		this.type = type;
		this.identityNumber = identityNumber;
	}
	
	
	
}
