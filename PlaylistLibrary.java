package com.google;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * A class used to represent a Playlist Library.
 */
class PlaylistLibrary {

  private final HashMap<String, VideoPlaylist> playlists;

  PlaylistLibrary() {
    this.playlists = new HashMap<>();
  }

  boolean addPlaylist(String name){
    if (playlists.containsKey(name.toLowerCase()))
        return false;
    VideoPlaylist newPlaylist = new VideoPlaylist(name);
    playlists.put(name.toLowerCase(), newPlaylist);   
    return true;
  }

  VideoPlaylist getPlaylist(String name) {
   return playlists.get(name.toLowerCase());
  }

  List<VideoPlaylist> getAllPlaylists() {
    return new ArrayList<>(this.playlists.values());
  }

  boolean removePlaylist(String name) {
    VideoPlaylist removed = playlists.remove(name.toLowerCase());
    if (removed == null)
       return false;
    else 
       return true;
  }

  /**
   * Get a video by id. Returns null if the video is not found.
   */
  
}
