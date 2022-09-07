package com.nttdata.repository;

import org.springframework.data.repository.CrudRepository;

import com.nttdata.model.TransactionBd;

public interface TransactionRepository extends CrudRepository<TransactionBd, Long> {

}
