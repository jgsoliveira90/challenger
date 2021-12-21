package com.challenge.bahamas.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GovRest {

	
	private RestTemplate rest = new RestTemplate();
	
	public Integer callGovUrl(String url) {
		Integer response = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<HttpHeaders> entity = new HttpEntity<>(httpHeaders);
		try {

			response = rest.exchange(url, HttpMethod.POST, entity, Integer.class).getStatusCodeValue();
			
		} catch (Exception e) {
			log.error("error in callGovUrl:");
			log.error(e.toString());
			throw e;
		}
		return response;
	}
	
}
