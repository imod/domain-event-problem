package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface AggregateRepository extends CrudRepository<Aggregate, Long> {

}