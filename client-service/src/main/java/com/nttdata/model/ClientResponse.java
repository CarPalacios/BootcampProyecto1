package com.nttdata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientResponse {
	
	private final long id;
	
	private final String content;

}
