package com.nttdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.model.TransactionBd;
import com.nttdata.service.ITransactionService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private ITransactionService service;

	@GetMapping
	public Mono<ResponseEntity<List<TransactionBd>>> findAll() {

		return service.findAll().collectList().flatMap(list -> {
			return list.size() > 0 ? Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list))
					: Mono.just(ResponseEntity.noContent().build());
		});

	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<TransactionBd>> findById(@PathVariable("id") Long id){
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
	public Mono<ResponseEntity<TransactionBd>> create(@RequestBody TransactionBd transaction) {

		return service.create(transaction)
				.map(c -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(c));

	}

}