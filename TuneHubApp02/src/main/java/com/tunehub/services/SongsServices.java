package com.tunehub.services;

import java.util.List;

import com.tunehub.entities.Songs;

public interface SongsServices {
	public String addSongs(Songs song);
	public boolean existSongName(String name);
	public List<Songs> viewAllSongs();
	public void updateSongs(Songs song);
}
