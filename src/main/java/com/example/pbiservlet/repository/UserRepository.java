package com.example.pbiservlet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.pbiservlet.repository.User;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByFullNameContainingIgnoreCaseAndEmailContainingIgnoreCase(String fullName, String email);

}
