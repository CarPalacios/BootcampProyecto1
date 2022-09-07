package com.nttdata.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.model.ProductBd;
import com.nttdata.service.IProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private IProductService service;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<ProductBd>>> findAll(){ 

		Flux<ProductBd> products = service.findAll();
		
		return Mono.just(ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(products));	
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<ProductBd>> findById(@PathVariable("id") Long id){
		return service.findById(id)
				.map(objectFound -> ResponseEntity
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(objectFound))
				.defaultIfEmpty(ResponseEntity
						.noContent()
						.build());
	}
	
	@PostMapping
	public Mono<ResponseEntity<ProductBd>> create(@RequestBody ProductBd product){
		return service.create(product)
				.map(createdObject->{
					return ResponseEntity
							.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(createdObject);
				});
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<ProductBd>> update(@RequestBody ProductBd product, @PathVariable("id") long id){
		Mono<ProductBd> ProductModification = Mono.just(product);
		Mono<ProductBd> ProductDatabase = service.findById(id);
		
		return ProductDatabase
				.zipWith(ProductModification, (a,b)->{
					a.setId(id);
					a.setProductName(product.getProductName());
					a.setProductType(product.getProductType());
//					a.getCondition().setCustomerTypeTarget(product.getCondition().getCustomerTypeTarget());
					a.getCondition().setHasMaintenanceFee(product.getCondition().isHasMaintenanceFee());
					a.getCondition().setMaintenanceFee(product.getCondition().getMaintenanceFee());
					a.getCondition().setHasMonthlyTransactionLimit(product.getCondition().isHasMonthlyTransactionLimit());
					a.getCondition().setMonthlyTransactionLimit(product.getCondition().getMonthlyTransactionLimit());
					a.getCondition().setHasDailyWithdrawalTransactionLimit(product.getCondition().isHasDailyWithdrawalTransactionLimit());
					a.getCondition().setDailyWithdrawalTransactionLimit(product.getCondition().getDailyWithdrawalTransactionLimit());
					a.getCondition().setHasDailyDepositTransactionLimit(product.getCondition().isHasDailyDepositTransactionLimit());
					a.getCondition().setDailyDepositTransactionLimit(product.getCondition().getDailyDepositTransactionLimit());
					a.getCondition().setProductPerPersonLimit(product.getCondition().getProductPerPersonLimit());
					a.getCondition().setProductPerBusinessLimit(product.getCondition().getProductPerBusinessLimit());
					return a;
				})
				.flatMap(service::update)
				.map(objectUpdated->{
					return ResponseEntity
							.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(objectUpdated);
				})
				.defaultIfEmpty(ResponseEntity.noContent().build());
	}
	
	

}
