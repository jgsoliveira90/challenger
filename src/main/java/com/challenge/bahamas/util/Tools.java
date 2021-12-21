package com.challenge.bahamas.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.challenge.bahamas.vo.Client;

@Service
public class Tools {

	@Value("${client.urlGovBahamasPost}")
	private String url;
	
	public String urlAjust(Client c) {
		String newUrl;
		String[] parts = url.split("=");
		newUrl = parts[0] + "=" + c.getInvoiceId().toString() + parts[1] + "=" + c.getFiscal_id().toString() + parts[2] + "=" + c.getEmail();
		return newUrl;
	}
	
}
