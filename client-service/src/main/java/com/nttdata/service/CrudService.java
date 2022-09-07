package com.nttdata.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CrudService<T, ID> {

	Flux<T> findAll();
	
	Mono<T> findById(ID id);
	
	Mono<T> create(T t);
	
	Mono<T> update(T t);
	
	Mono<Void> delete(ID id);
	
}
