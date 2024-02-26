package com.tunehub.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.tunehub.entities.Users;
import com.tunehub.services.UserServices;

import jakarta.servlet.http.HttpSession;


@Controller
public class PaymentController {
	@Autowired
	UserServices userServ;
	
	@PostMapping("/createPaymentOrder")
	@ResponseBody
	public String createPaymentOrder() {
		Order order=null;
		try {
			RazorpayClient razorpay = new RazorpayClient("rzp_test_pi0p2v5yWCa6qC", "eoWOMYkeZKOxlmI2ZzxFW8wM");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount",50000);
			orderRequest.put("currency","INR");
			orderRequest.put("receipt", "receipt#1");
			JSONObject notes = new JSONObject();
			notes.put("notes_key_1","Tea, Earl Grey, Hot");
			orderRequest.put("notes",notes);

		
			order = razorpay.orders.create(orderRequest);
		}
		catch(Exception e){
			System.out.println("Exception while creating payment order");
		}
		return order.toString();
		
	}
	
	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam String orderId, 
			             @RequestParam String paymentId, 
			             @RequestParam String signature) {
		try {
	        // Initialize Razorpay client with your API key and secret
	        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_pi0p2v5yWCa6qC", "eoWOMYkeZKOxlmI2ZzxFW8wM");
	        // Create a signature verification data string
	        String verificationData = orderId + "|" + paymentId;

	        // Use Razorpay's utility function to verify the signature
	        boolean isValidSignature = Utils.verifySignature(verificationData, signature, "eoWOMYkeZKOxlmI2ZzxFW8wM");

	        return isValidSignature;
	    } 
		
		catch (RazorpayException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	//payment-success -> update to premium user
	@GetMapping("payment-success")
	public String paymentSuccess(HttpSession session) {
		String email=(String) session.getAttribute("email");
		Users user=userServ.getUser(email);
		user.setPremium(true);
		userServ.updateUser(user);
		
		return "prime_user_home";
	}
	
	//payment-failure -> redirect to login
	@GetMapping("payment-failure")
	public String paymentFailure() {
		//payment-error page
		return "Prime_failure";
	}
	
	
}
