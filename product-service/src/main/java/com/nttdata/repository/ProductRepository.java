package com.nttdata.repository;

import org.springframework.data.repository.CrudRepository;

import com.nttdata.model.ProductBd;

public interface ProductRepository extends CrudRepository<ProductBd, Long> {
	
}
