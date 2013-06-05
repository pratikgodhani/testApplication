package com.test.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, ObjectId>{
	
}
