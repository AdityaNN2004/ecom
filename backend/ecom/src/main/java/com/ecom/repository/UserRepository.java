package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByIsActiveTrue();

}
