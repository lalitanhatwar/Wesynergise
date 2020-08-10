package com.capgemini.userDashBoard.controllers;

import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.capgemini.userDashBoard.entities.MyUser;
import com.capgemini.userDashBoard.service.UserService;


/**
 * @author lhatwar
 *
 */
@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	org.jboss.logging.Logger logger=LoggerFactory.logger(getClass());
	
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
}
