package com.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunehub.entities.Songs;
import com.tunehub.entities.Users;
import com.tunehub.services.SongsServices;
import com.tunehub.services.UserServices;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class SongsController {
	
	@Autowired
	SongsServices songsServ;
	
	@Autowired
	UserServices userServ;
	
	@PostMapping("/addsongs")
	public String addSongs(@ModelAttribute Songs song) {
		if(songsServ.existSongName(song.getName())==false) {
			return (songsServ.addSongs(song));
		}
		else {
			return "addsongfailed";
		}
	}
	
	// view songs- admin home
	@GetMapping("/map-viewsongs-admin")
	public String viewAllSongs(Model model){
		List<Songs> allsongslist=songsServ.viewAllSongs();
		model.addAttribute("allsongslist", allsongslist);
		return "songslist";
	}
	
	// exploring song as part of user prime subscription
	@GetMapping("/map-exploresongs-customer")
	public String viewAllSongsToCustomer(Model model, String email, HttpSession session){
		String sessionEmail= (String) session.getAttribute("email");
		if(userServ.getIsPrime(sessionEmail)==true) {
			List<Songs> allsongslist=songsServ.viewAllSongs();
			model.addAttribute("allsongslist", allsongslist);
			return "customer_songslist";
		}
		else {
			return "prime_purchase_page";
		}
		/*
		Users user=userServ.getUser(email);
		boolean customerPremiumStatus=user.isPremium();
		if(customerPremiumStatus==true) {
			List<Songs> allsongslist=songsServ.viewAllSongs();
			model.addAttribute("allsongslist", allsongslist);
			return "customer_songslist";
		}
		else {
			return "prime-purchase-page";
		}
		*/
	}
	
	// view songs- Prime member home
	@GetMapping("/map-viewsongs-prime")
	public String viewAllSongsPrime(Model model){
		List<Songs> allsongslist=songsServ.viewAllSongs();
		model.addAttribute("allsongslist", allsongslist);
		return "prime_songslist";
	}
	
}
