package com.tunehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.entities.Users;
import com.tunehub.services.UserServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserServices userServ;
	
	// Registration Logics:
	
	// collecting registration for info from users, validating, is email already registered? And
	// if not registered data will have stored for database.
	@PostMapping("/register")
	public String regesterTuneHub(@ModelAttribute Users user)
	{
		boolean userStatus=userServ.emailExists(user.getEmail());
		if(userStatus==false) {
			String response=userServ.regesterTuneHub(user);
			return response;
		}
		else {
			return "registerfailed";
		}
	}
	
	@PostMapping("/login")
	public String loginTuneHub(@RequestParam String email, 
			                   @RequestParam String password,
			                   HttpSession session) 
	{
		//invoking loginTuneHub() in service class
		if(userServ.loginTuneHub(email, password)==true) {
			session.setAttribute("email", email);
			//checking wheather the user is admin or customer
			String roleResponse=null;
			if(userServ.getRole(email).equals("admin")) {
				roleResponse= "adminhome";
			}
			else if(userServ.getRole(email).equals("customer")) {
				
				if(userServ.getIsPrime(email)==true) {
					roleResponse= "prime_user_home";
				}
				else {
					roleResponse= "customerhome";
				}
			}
			return roleResponse;
			
		}
		else {
			return "loginfailed";
		}
	}
	
	/*
	@GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Get the authentication token
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        return "You have been logged out";
    }
	*/
	
}
