package com.nttdata.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nttdata.model.ClientBd;

public interface ClientRepository extends CrudRepository<ClientBd, Long>{
	
	Optional<ClientBd> findByIdentityNumber(String identityNumber);

}
