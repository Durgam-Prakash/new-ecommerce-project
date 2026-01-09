package com.amazon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.backend.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

	
	
	
}
