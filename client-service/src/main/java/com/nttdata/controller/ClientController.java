package com.nttdata.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.model.ClientBd;
import com.nttdata.model.ClientResponse;
import com.nttdata.service.ClientService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class ClientController {
	
	private final AtomicLong counter = new AtomicLong();
	
	private static final String template = "Registro Satisfactorio, %s!";
	
	@Autowired
	private ClientService service;
	
	@GetMapping("/all")
	public Mono<ResponseEntity<Flux<ClientBd>>> findAll() {
		
		Flux<ClientBd> clients = service.findAll();
		log.info("FindAll : ", clients);
		return Mono.just(ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(clients));							
		
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<ClientBd>> findById(@PathVariable("id") long id) {
		
		log.info("getById" + "OK");
		return service.findById(id)
				.map(c -> ResponseEntity
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(c))
				.defaultIfEmpty(new ResponseEntity<ClientBd>(HttpStatus.NOT_FOUND));
		
	}
	
	@PostMapping("/save")
	public Mono<ClientResponse> create(@RequestBody ClientBd client) {

		return service.create(new ClientBd(client.getId(), client.getName(), client.getLastname(), client.getType(),
				client.getIdentityNumber())).map(c -> {
					
					log.info("Cliente registrado" + "OK");
					return new ClientResponse(counter.incrementAndGet(), String.format(template, c.getName()));
				});

	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<ClientBd>> update(@PathVariable long id, @RequestBody ClientBd client) {
		
		Mono<ClientBd> monoDatabase = service.findById(id);
		
		Mono<ClientBd> monoClient = Mono.just(client);
		
		return monoDatabase
				.zipWith(monoClient, (db, cl) -> {
					db.setId(id);
					db.setName(cl.getName());
					db.setType(cl.getType());
					db.setIdentityNumber(cl.getIdentityNumber());					
					return db;
				})
				.flatMap(service::update)
				.map(c -> ResponseEntity
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(c))
				.defaultIfEmpty(new ResponseEntity<ClientBd>(HttpStatus.NOT_FOUND));
				
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id") long id) {
		
		return service.findById(id)
				.flatMap(c -> {
					return service.delete(c.getId())
							.thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
				})
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	
	}

}
