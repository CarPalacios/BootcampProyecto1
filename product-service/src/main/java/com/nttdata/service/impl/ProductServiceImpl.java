package com.nttdata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.model.ProductBd;
import com.nttdata.repository.ProductRepository;
import com.nttdata.service.IProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	public Mono<ProductBd> create(ProductBd product) {
		return Mono.just(repository.save(product));
	}

	@Override
	public Flux<ProductBd> findAll() {
		return Flux.fromIterable(repository.findAll());
	}

	@Override
	public Mono<ProductBd> findById(Long id) {
		return Mono.just(repository.findById(id).get());
	}

	@Override
	public Mono<ProductBd> update(ProductBd product) {
		return Mono.just(repository.save(product));
	}

	@Override
	public Mono<Void> delete(Long id) {
		repository.deleteById(id);
		return Mono.empty();
	}
	

	
}
