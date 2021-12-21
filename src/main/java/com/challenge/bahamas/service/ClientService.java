package com.challenge.bahamas.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.bahamas.repository.ClientRepository;
import com.challenge.bahamas.rest.GovRest;
import com.challenge.bahamas.util.Tools;
import com.challenge.bahamas.vo.Client;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientService {

	private ClientRepository clientRepo;
	
	private Tools tools;
	
	private GovRest govrest;
	
	
	@Autowired
	public ClientService(ClientRepository clientRepo, Tools tools, GovRest govrest) {
		this.clientRepo = clientRepo;
		this.tools = tools;
		this.govrest = govrest;
	}

	public Client retrieveClient(Integer invoiceId) {
		Client client = clientRepo.findByInvoiceId(invoiceId);
		return client;
		
	}

	public Client storeClient(Integer invoiceId, Map<String, String> params) {
				
		try {
			Client existent = clientRepo.findByInvoiceId(invoiceId);
			if (existent ==  null) {
				
				Client c = new Client();
				c.setInvoiceId(invoiceId);
				
				for (Map.Entry<String, String> x : params.entrySet()) {
					if (x.getKey().equals("fiscal_id")) {
						c.setFiscal_id(Integer.parseInt(x.getValue()));
					}
					if (x.getKey().equals("name")) {
						c.setName(x.getValue());
					}
					if (x.getKey().equals("email")) {
						c.setEmail(x.getValue());
					}
				}
				
				clientRepo.save(c);
				
				ajustNSendToGov(c);
				
				return c;
			}
			
			return existent;
			
		} catch (Exception e) {
			log.info("error in storeClient: " + e.toString());
			throw e;
		}
		
		
	}

	private void ajustNSendToGov(Client c) {
		String urlAjust = tools.urlAjust(c);
		govrest.callGovUrl(urlAjust);
	}
	
}
