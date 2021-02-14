package br.com.stoomtest.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stoomtest.address.entity.impl.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
}
