package com.tunehub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
	
	// mapping to register page from land page
	@GetMapping("/map-register")
	public String mapToRegisterPage()
	{
		return "register";	
	}
	
	// mapping to login page from land page
	@GetMapping("/map-login")
	public String mapToLoginPage()
	{
		return "login";	
	}
	
	//mapping to Add songs page from admin home
	@GetMapping("/map-addsongs-page")
	public String mapAddSongs()
	{
		return "addsongs";	
	}
	
	//mapping to admin home from addsongsuccess.html file
	@GetMapping("/map-adminhome")
	public String mapAdminHome()
	{
		return "adminhome";	
	}
	
	//mapping to Prime Purchase page
	@GetMapping("/map-PrimePurchasePage-customer")
	public String mapPrimePurchasePage()
	{
		return "prime_purchase_page";	
	}
	
	//mapping to Customer home(No premium user)
	@GetMapping("/map-home-customer")
	public String mapCustomerHome()
	{
		return "customerhome";	
	}
	
	//mapping to Customer/prime member home(premium user)
	@GetMapping("/map-prime-member-home")
	public String mapPrimeMemberHome()
	{
		return "prime_user_home";	
	}
}
