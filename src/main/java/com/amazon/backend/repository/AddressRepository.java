package com.amazon.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.backend.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

	List<Address> findByUserId(int userId);
	Optional<Address>findByUserIdAndAddressId(int addressId, int userId);
	
	 
	
}
