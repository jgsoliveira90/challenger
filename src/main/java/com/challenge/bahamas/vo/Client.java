package com.challenge.bahamas.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Client {

	@Id
	private String id;
	
	private Integer invoiceId;
	
	private Integer fiscal_id;
	
	private String name;
	
	private String email;
	
}
