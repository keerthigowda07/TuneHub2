package com.tunehub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entities.PlayList;
import com.tunehub.repositories.PlaylistRepository;

@Service
public class PlaylistServiceImplementation implements PlaylistService{
	@Autowired
	PlaylistRepository playlistRepo;
	
	@Override
	public String addSongsToPlaylist(PlayList playlist) {
		playlistRepo.save(playlist);
		return "create_playlist_success";
	}

	@Override
	public List<PlayList> fetchAllPlaylist() {
		List<PlayList> playLists=playlistRepo.findAll();
		return playLists;
	}

	@Override
	public boolean isExistPlaylist(String playlistName) {
		if(playlistRepo.findByPlaylistName(playlistName)==null)
		{
			return false;
		}
		else {
			return true;
		}
		
	}

}
