package com.nttdata.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICRUDService<T, ID> {
	
	Mono<T> create(T o);

	Flux<T> findAll();
	
	Mono<T> findById(ID id);
	
}
