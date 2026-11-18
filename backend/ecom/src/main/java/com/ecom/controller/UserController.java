package com.ecom.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.UserDto;

import com.ecom.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;
	private List<UserDto> userList = new ArrayList<>();
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		userList = userService.fetchAllUsers();
		if(userList==null)
			return  ResponseEntity.notFound().build();
		return new ResponseEntity<>(userList,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
		UserDto dto = userService.fetchUserById(id);
		if(dto==null)
			return  ResponseEntity.notFound().build();
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> editUserDetails(@PathVariable Long id,@RequestBody UserDto userDto) {
		UserDto dto = userService.updateUserDetails(id,userDto);
		if(dto==null)
			return  ResponseEntity.notFound().build();
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
}
