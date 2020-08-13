package com.capgemini.userDashBoard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.userDashBoard.entities.MyUser;
import com.capgemini.userDashBoard.repository.UserJpaRepository;
/**
 * @author lhatwar
 *
 */
@Service
public class UserService {
	@Autowired
	UserJpaRepository userJpaRepository;
	
	public void addUser(MyUser user) {
		userJpaRepository.save(user);
		System.out.println("User saved successfully");
	}

	public List<MyUser> findAll() {
		System.out.println("Inside Repo");
		return userJpaRepository.findAll();
		
	}
	public Optional<MyUser> findById(Long id) {
		return userJpaRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		userJpaRepository.deleteById(id);
		System.out.println("User deleted successfully");
	}
}

