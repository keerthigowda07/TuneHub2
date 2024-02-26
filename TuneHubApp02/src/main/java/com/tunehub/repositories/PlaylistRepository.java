package com.tunehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.entities.PlayList;


public interface PlaylistRepository extends JpaRepository<PlayList, Integer>{
	public PlayList findByPlaylistName(String playlistName);
}
