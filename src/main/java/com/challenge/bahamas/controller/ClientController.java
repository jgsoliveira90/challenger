package com.challenge.bahamas.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.bahamas.service.ClientService;
import com.challenge.bahamas.vo.Client;

@RestController
@RequestMapping("/")
public class ClientController {

	@Autowired
	private ClientService clientServ;

	@GetMapping("retrieve-bahamas-client/{invoiceId}")
	public ResponseEntity<?> retrieveClient(@PathVariable Integer invoiceId) {

		try {

			Client retrieveClient = clientServ.retrieveClient(invoiceId);
			return ResponseEntity.status(HttpStatus.OK).body(retrieveClient);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PostMapping("store-bahamas-client/{invoiceId}")
	public ResponseEntity<?> storeClient(@PathVariable Integer invoiceId, @RequestParam Map<String, String> params) {

		try {

			Client storedClient = clientServ.storeClient(invoiceId, params);
			if (storedClient != null) {
				String storedAlready = "User already stored";
				return ResponseEntity.status(HttpStatus.OK).body(storedAlready);
				
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(storedClient);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
