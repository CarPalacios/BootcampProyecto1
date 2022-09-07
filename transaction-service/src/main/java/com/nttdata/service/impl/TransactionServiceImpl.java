package com.nttdata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.model.TransactionBd;
import com.nttdata.repository.TransactionRepository;
import com.nttdata.service.ITransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements ITransactionService {

	@Autowired
	private TransactionRepository repository;

	@Override
	public Mono<TransactionBd> create(TransactionBd transaction) {
		return Mono.just(repository.save(transaction));
	}

	@Override
	public Flux<TransactionBd> findAll() {
		return Flux.fromIterable(repository.findAll());
	}

	@Override
	public Mono<TransactionBd> findById(Long id) {
		return Mono.just(repository.findById(id).get());
	}


}
