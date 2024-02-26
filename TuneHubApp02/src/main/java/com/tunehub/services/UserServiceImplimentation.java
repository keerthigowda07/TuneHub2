package com.tunehub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entities.Users;
import com.tunehub.repositories.UserRepositories;

@Service
public class UserServiceImplimentation implements UserServices{
	@Autowired
	UserRepositories userRepo;

	@Override
	public String regesterTuneHub(Users user) {
		userRepo.save(user);
		return "registersuccess";
	}

	@Override
	public boolean emailExists(String email) {
		if(userRepo.findByEmail(email)==null)
		{
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public boolean loginTuneHub(String email, String password) {
		Users user=userRepo.findByEmail(email);
		String dbPassword=user.getPassword();
		if(dbPassword.equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String getRole(String email) {
		return (userRepo.findByEmail(email).getRole());
	}

	@Override
	public Users getUser(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		userRepo.save(user);
	}

	@Override
	public boolean getIsPrime(String email) {
		return userRepo.findByEmail(email).isPremium();
	}
	
	
}
