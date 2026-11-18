package com.ecom.service;

import java.util.List;

import com.ecom.dto.UserDto;

public interface UserService {

	UserDto fetchUserById(Long id);

	List<UserDto> fetchAllUsers();

	UserDto updateUserDetails(Long id, UserDto userDto);
}
