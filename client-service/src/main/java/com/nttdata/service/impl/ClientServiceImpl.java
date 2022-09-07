package com.nttdata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nttdata.model.ClientBd;
import com.nttdata.repository.ClientRepository;
import com.nttdata.service.ClientService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository repo;

	@Override
	public Flux<ClientBd> findAll() {
		return Flux.fromIterable(repo.findAll());
	}

	@Override
	public Mono<ClientBd> create(ClientBd client) {
		
		log.info(client.toString());

		return Mono.just(repo.findByIdentityNumber(client.getIdentityNumber()).orElse(new ClientBd())).flatMap(c -> {
			if (c.getIdentityNumber() != null) {
				return Mono.error(new Throwable("El DNI indicado ya existe"));
			}
			log.info(client.toString());
			return Mono.just(repo.save(client));
		});

	}

	@Override
	public Mono<ClientBd> update(ClientBd client) {
		return Mono.just(repo.save(client));
	}

	@Override
	public Mono<ClientBd> findById(Long id) {
		return Mono.just(repo.findById(id).get());
	}

	@Override
	public Mono<Void> delete(Long id) {
		repo.deleteById(id);
		return Mono.empty();
	}

}
