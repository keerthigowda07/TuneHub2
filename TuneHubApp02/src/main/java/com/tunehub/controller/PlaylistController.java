package com.tunehub.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunehub.entities.PlayList;
import com.tunehub.entities.Songs;
import com.tunehub.services.PlaylistService;
import com.tunehub.services.SongsServices;

@Controller
public class PlaylistController {
	@Autowired
	SongsServices songsServ;
	
	@Autowired
	PlaylistService plServe;
	
	@GetMapping("/map-create-playlist-admin")
	public String fetchAllSongsToCreatePlaylist(Model model) {
		List<Songs> allsongslist=songsServ.viewAllSongs();
		model.addAttribute("allsongslist", allsongslist);
		return "create_playlist_admin";
	}
	
	@PostMapping("/savePlaylist")
	public String addSongsToPlaylist(@ModelAttribute PlayList playlist, Model model) {
		//verifying playlist name already exist or not
		if(plServe.isExistPlaylist(playlist.getPlaylistName())==false) {
			//adding playlist
			String servResponse= plServe.addSongsToPlaylist(playlist);
			
			//updating song table
			List<Songs> songsListFromPlaylist=playlist.getSongs();
			for(Songs song:songsListFromPlaylist) {
				song.getPlaylist().add(playlist);
				songsServ.updateSongs(song);
			}
			List<Songs> allsongslist=songsServ.viewAllSongs();
			model.addAttribute("allsongslist", allsongslist);
			return servResponse;
		}
		else {
			List<Songs> allsongslist=songsServ.viewAllSongs();
			model.addAttribute("allsongslist", allsongslist);
			return "create_playlist_failed";
		}
		
	}
	
	// view playlist- admin
	@GetMapping("/map-view-playlist-admin")
	public String fetchAllPlaylist(Model model) {
		List<PlayList> playLists=plServe.fetchAllPlaylist();
		model.addAttribute("playlist", playLists);
		return "viewPlaylists";
	}
	
	// view playlist- prime member
	@GetMapping("/map-view-playlist-prime")
	public String fetchAllPlaylistPrime(Model model) {
		List<PlayList> playLists=plServe.fetchAllPlaylist();
		model.addAttribute("playlist", playLists);
		return "prime_viewPlaylists";
	}
	
}
