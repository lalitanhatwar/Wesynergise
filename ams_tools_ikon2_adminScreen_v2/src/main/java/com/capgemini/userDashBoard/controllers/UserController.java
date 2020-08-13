package com.capgemini.userDashBoard.controllers;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.userDashBoard.entities.MyUser;
import com.capgemini.userDashBoard.repository.UserJpaRepository;
import com.capgemini.userDashBoard.service.UserService;


/**
 * @author lhatwar
 *
 */
@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserJpaRepository userRepo;
	
	org.jboss.logging.Logger logger=LoggerFactory.logger(getClass());
	
	@GetMapping("/users")
	@ResponseBody
	public List<MyUser> getAllUsers(){
		return userRepo.findAll();
	}
	
	@GetMapping("/userRegistration")
	public String userRegistration(Model model) {
		model.addAttribute("user", new MyUser());
		return "userRegistraionForm";
	}

	@PostMapping("/submitUser")
	public String userSubmit(@ModelAttribute("user") @Validated MyUser myuser,BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			logger.info("==Error==");
			return "userRegistraionForm";
		}else {
			logger.info("==Adding User==");
			userService.addUser(myuser);
			model.addAttribute("user", myuser);
			return "result";
		}
	}
	
	@GetMapping("/deleteUser/{id}")
	@ResponseBody
	public void deleteUser(@PathVariable Long id) {
	 userService.deleteById(id);
	 System.out.println("Deleted successfully!!");
	}
	
	@GetMapping("/findById/{id}")
	@ResponseBody
	public Optional<MyUser> findById(@PathVariable Long id) {
		return userService.findById(id);
	}
}
