package com.tunehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.entities.Users;
public interface UserRepositories extends JpaRepository<Users, Integer>
{
	public Users findByEmail(String email);
}
