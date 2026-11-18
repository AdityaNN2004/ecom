package com.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {
	public AddressDto() {}
	private  String houseNo;
	private  String street;
	private  String city;
	private  String state;
	private  String country;
	private  String zipcode;
} 
