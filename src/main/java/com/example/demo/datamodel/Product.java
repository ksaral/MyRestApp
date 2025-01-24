package com.example.demo.datamodel;

import java.io.Serializable;

import lombok.Data;

@Data
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7328647526924794586L;
	
	private Integer productId;
	private String productName;

}
