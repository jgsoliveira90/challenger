package com.challenge.bahamas.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.challenge.bahamas.repository.ClientRepository;
import com.challenge.bahamas.rest.GovRest;
import com.challenge.bahamas.util.Tools;
import com.challenge.bahamas.vo.Client;

class ClientServiceTest {

	private ClientService clientServ;
	
	@Mock
	private ClientRepository clientRepo;
	
	@Mock
	private Tools tools;
	
	@Mock
	private GovRest govrest;
	
	Integer invoiceId = 1234;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		this.clientServ = new ClientService(clientRepo, tools, govrest);
	}
	
	@Test
	void shouldReturnTheClient() {
		
		Client clientSet = clientSet();
		
		Mockito.when(clientRepo.findByInvoiceId(invoiceId)).thenReturn(clientSet);
		
		Client retrievedClient = clientServ.retrieveClient(invoiceId);
		
		
	}
	
	@Test
	void shouldStoreTheClient() {
			
		Map<String, String> params = paramnsSet();
		
		Client clientSet = clientSet();
		
		Mockito.when(clientRepo.findByInvoiceId(invoiceId)).thenReturn(clientSet);
		
		clientServ.storeClient(invoiceId, params);
		
	}
	
	private Map<String, String> paramnsSet(){
		
		Map<String, String> params = new HashMap<>();;
		
		params.put("fiscal_id","999999999");
		params.put("name","Bob");
		params.put("email","bob@bob.com");
		
		return params;
	}
	
	private Client clientSet() {
		
		Client c = new Client();
		c.setEmail("bob@bob.com");
		c.setInvoiceId(invoiceId);
		c.setName("Bob");
		c.setFiscal_id(999999999);
		
		return c;
	}

}
