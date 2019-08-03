package com.auzmor.backendserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auzmor.backendserver.model.PhoneNumber;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long>{
	
	PhoneNumber findByNumber(String number);

}
