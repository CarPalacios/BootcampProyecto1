package com.nttdata.repository;

import org.springframework.data.repository.CrudRepository;

import com.nttdata.model.Condition;

public interface ConditionRepository extends CrudRepository<Condition, String> {

}
