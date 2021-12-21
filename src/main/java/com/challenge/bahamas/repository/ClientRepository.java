package com.challenge.bahamas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.challenge.bahamas.vo.Client;

public interface ClientRepository extends MongoRepository<Client, String> {

	Client findByInvoiceId(Integer invoiceId);
	
}
