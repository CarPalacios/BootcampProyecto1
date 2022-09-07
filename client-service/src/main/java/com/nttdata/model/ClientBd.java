package com.nttdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Client")
public class ClientBd {

	public ClientBd() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String lastname;
	private String type;
	@Column(name = "dni")
	private String identityNumber;

	@Override
	public String toString() {
		return "ClientBd [id=" + id + ", name=" + name + ", lastname=" + lastname + ", type=" + type
				+ ", identityNumber=" + identityNumber + "]";
	}

}
