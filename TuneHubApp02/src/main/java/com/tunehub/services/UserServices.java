package com.tunehub.services;

import com.tunehub.entities.Users;

public interface UserServices {
	public String regesterTuneHub(Users user);
	public boolean emailExists(String email);
	public boolean loginTuneHub(String email, String password);
	public String getRole(String email);
	public Users getUser(String email);
	public void updateUser(Users user);
	public boolean getIsPrime(String email);
}
