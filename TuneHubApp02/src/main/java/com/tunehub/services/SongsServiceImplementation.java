package com.tunehub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entities.Songs;
import com.tunehub.repositories.SongsRepository;

@Service
public class SongsServiceImplementation implements SongsServices{

	@Autowired
	SongsRepository songsRepo;
	
	@Override
	public String addSongs(Songs song) {
		songsRepo.save(song);
		return "addsongsuccess";
	}

	@Override
	public boolean existSongName(String name) {
		if(songsRepo.findByName(name)==null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public List<Songs> viewAllSongs() {
		List<Songs> listSongsFromRepo=songsRepo.findAll();
		return listSongsFromRepo;
	}

	@Override
	public void updateSongs(Songs song) {
		songsRepo.save(song);
		
	}
}
