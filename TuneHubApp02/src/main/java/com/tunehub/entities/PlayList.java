package com.tunehub.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class PlayList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String playlistName;
	
	@ManyToMany
	List<Songs> songs;

	public PlayList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlayList(int id, String playlistName, List<Songs> songs) {
		super();
		this.id = id;
		this.playlistName = playlistName;
		this.songs = songs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public List<Songs> getSongs() {
		return songs;
	}

	public void setSongs(List<Songs> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "PlayList [id=" + id + ", playlistName=" + playlistName + ", songs=" + songs + "]";
	}

}
