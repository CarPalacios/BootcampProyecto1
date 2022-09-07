package com.nttdata.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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
import com.nttdata.model.TransactionResponse;
import com.nttdata.service.ITransactionService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	private final AtomicLong counter = new AtomicLong();
	
	private static final String template = "Transaccion satisfactoria!";

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
		
		log.info("getById" + "OK");
		
		return service.findById(id)
				.map(objectFound -> ResponseEntity
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(objectFound))
				.defaultIfEmpty(ResponseEntity
						.noContent()
						.build());
	}

	@PostMapping("/save")
	public TransactionResponse create(@RequestBody TransactionBd transaction) {
		
		service.create(new TransactionBd(transaction.getId(), transaction.getTransactionType(),
				transaction.getTransactionAmount(), transaction.getCardNumber(), transaction.getDescription(),
				transaction.getTransactionDate()));

		return new TransactionResponse(counter.incrementAndGet(), String.format(template));

	}

}