package com.ecom.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
@RequiredArgsConstructor
public class UserDto {
	private final String id;
	private final String firstName;
	private final String middleName;
	private final String lastName;
	private final String email;
	private final String phoneNo;
	private final AddressDto adress;
}
