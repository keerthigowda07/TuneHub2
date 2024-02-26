package com.tunehub.services;

import java.util.List;

import com.tunehub.entities.PlayList;

public interface PlaylistService {
	public String addSongsToPlaylist(PlayList playlist);

	public List<PlayList> fetchAllPlaylist();

	public boolean isExistPlaylist(String playlistName);
}
