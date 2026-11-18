package com.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.dto.AddressDto;
import com.ecom.dto.UserDto;
import com.ecom.entity.User;
import com.ecom.exception.UserNotFoundException;
import com.ecom.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	@Override
	public UserDto fetchUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User With ID: "+id+" not found"));
		return mapToUserDto(user);
	}

	@Override
	public List<UserDto> fetchAllUsers() {
		List<UserDto> userList = userRepository.findByIsActiveTrue()
									.stream()
									.map(u-> new UserDto(
														String.valueOf(u.getId()),
														u.getFirstName(),
													     u.getMiddleName(),
													     u.getLastName(),
													     u.getEmail(),
													     u.getPhoneNo(),
													     new AddressDto(
													    		 u.getAddress().getHouseNo(),
													    		 u.getAddress().getStreet(),
													    		 u.getAddress().getCity(),
													    		 u.getAddress().getState(),
													    		 u.getAddress().getCountry(),
													    		 u.getAddress().getZipcode())))
									.toList();
		return userList;
	}

	@Override
	public UserDto updateUserDetails(Long id, UserDto userDto) {
		User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User With ID: "+id+" not found"));
		user.setFirstName(userDto.getFirstName());
		user.setMiddleName(userDto.getMiddleName());
		user.setMiddleName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		User updatedUser = userRepository.save(user);
		return mapToUserDto(updatedUser);
	}

	private UserDto mapToUserDto(User user) {
		UserDto dto = new UserDto(String.valueOf(user.getId()),user.getFirstName(),user.getMiddleName(),
				user.getLastName(),user.getEmail(),user.getPhoneNo(),mapToAddressDto(user));
		return dto;
	}

	private AddressDto mapToAddressDto(User u) {
		AddressDto dto =new AddressDto(
	    		 u.getAddress().getHouseNo(),
	    		 u.getAddress().getStreet(),
	    		 u.getAddress().getCity(),
	    		 u.getAddress().getState(),
	    		 u.getAddress().getCountry(),
	    		 u.getAddress().getZipcode());
		return dto;
	}


}
