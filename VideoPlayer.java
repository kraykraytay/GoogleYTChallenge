package com.google;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private PlaylistLibrary playlistLibrary;
  private String playingVideo = "";
  private String pausedVideo = "";

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
    this.playlistLibrary = new PlaylistLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
    List<Video> videoList = videoLibrary.getVideos();
    Collections.sort(videoList);
    System.out.println("Here's a list of all available videos:");
    for (Video v : videoList) {
      v.print();
      System.out.println();
    }
  }

  public void playVideo(String videoId) {
    Video v = videoLibrary.getVideo(videoId);
    if (v == null) {
      System.out.println("Cannot play video: Video does not exist");
      return;
    }

    if (!playingVideo.isEmpty())
       stopVideo();
    playingVideo = v.getTitle(); 
    System.out.println("Playing video: " + playingVideo);
  }

  public void stopVideo() {
    if (playingVideo.isEmpty())
      System.out.println("Cannot stop video: No video is currently playing");
    else {
      System.out.println("Stopping video: " + playingVideo);
      playingVideo = "";
      pausedVideo = "";
    }
  }

  public void playRandomVideo() {
    List<Video> videoList = videoLibrary.getVideos();
    Random random_method = new Random();
    int index = random_method.nextInt(videoList.size());
    if (!playingVideo.isEmpty())
       stopVideo();
    playingVideo = videoList.get(index).getTitle();
    System.out.println("Playing video: " + playingVideo);
  }

  public void pauseVideo() {
    if (playingVideo.isEmpty())
      System.out.println("Cannot pause video: No video is currently playing");
    else if (playingVideo.equals(pausedVideo))
       System.out.println("Video already paused: " + playingVideo);
    else {
      pausedVideo = playingVideo;
      System.out.println("Pausing video: " + playingVideo);
    }
  }

  public void continueVideo() {
    if (playingVideo.isEmpty())
      System.out.println("Cannot continue video: No video is currently playing");
    else if (playingVideo.equals(pausedVideo)) {
      System.out.println("Continuing video: " + pausedVideo);
      pausedVideo = "";
    } else {
      System.out.println("Cannot continue video: Video is not paused");
    }
  }

  public void showPlaying() {
    if (playingVideo.isEmpty())
       System.out.println("No video is currently playing");
    else {
      List<Video> videoList = videoLibrary.getVideos();
      for (Video v : videoList) {
        if (v.getTitle().equals(playingVideo)) {
          System.out.print("Currently playing: ");
          v.print();
          break;
        }
      }
      if (playingVideo.equals(pausedVideo))
         System.out.println(" - PAUSED");
      else
         System.out.println();
      }
    }

  public void createPlaylist(String playlistName) {
    if (!playlistName.matches("\\S+")) //check for whitespace
        return;
    else if (playlistLibrary.addPlaylist(playlistName))
      System.out.println("Successfully created new playlist: " + playlistName);
    else  
      System.out.println("Cannot create playlist: A playlist with the same name already exists");
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    VideoPlaylist playlist = playlistLibrary.getPlaylist(playlistName);
    if (playlist == null) {
      System.out.println("Cannot add video to " + playlistName + ": Playlist does not exist");
      return;
    }
    Video video = videoLibrary.getVideo(videoId);
    if (video == null) {
      System.out.println("Cannot add video to " + playlistName + ": Video does not exist");
      return;
    }
    if(!playlist.addVideo(video))
      System.out.println("Cannot add video to " + playlistName + ": Video already added");
    else   
      System.out.println("Added video to " + playlistName + ": " + video.getTitle());
  }

  public void showAllPlaylists() {
    List<VideoPlaylist> playlistList = playlistLibrary.getAllPlaylists();
    if (playlistList.isEmpty())
       System.out.println("No playlists exist yet");
    else {
       Collections.sort(playlistList);
       System.out.println("Showing all playlists:");
       for (VideoPlaylist p : playlistList) 
          System.out.println(p.getName());
    }
  }

  public void showPlaylist(String playlistName) {
    VideoPlaylist playlist = playlistLibrary.getPlaylist(playlistName);
    if (playlist == null) {
      System.out.println("Cannot show playlist " + playlistName + ": Playlist does not exist");
      return;
    }
    System.out.println("Showing playlist: " + playlistName);
    List<Video> videoList = playlist.getVideoList();
    if (videoList.isEmpty())
       System.out.println("  No videos here yet");
    else {
      for (Video v : videoList) {
        v.print();
        System.out.println();
      }
    }
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    VideoPlaylist playlist = playlistLibrary.getPlaylist(playlistName);
    if (playlist == null) {
      System.out.println("Cannot remove video from " + playlistName + ": Playlist does not exist");
      return;
    }
    Video video = videoLibrary.getVideo(videoId);
    if (video == null) {
      System.out.println("Cannot remove video from " + playlistName + ": Video does not exist");
      return;
    }
    if(!playlist.removeVideo(video))
      System.out.println("Cannot remove video from " + playlistName + ": Video is not in playlist");
    else   
      System.out.println("Removed video from " + playlistName + ": " + video.getTitle());
  }

  public void clearPlaylist(String playlistName) {
    VideoPlaylist playlist = playlistLibrary.getPlaylist(playlistName);
    if (playlist == null) {
      System.out.println("Cannot clear playlist " + playlistName + ": Playlist does not exist");
      return;
    }
    playlist.clear();
    System.out.println("Successfully removed all videos from " + playlistName);
  }

  public void deletePlaylist(String playlistName) {
    if (!playlistLibrary.removePlaylist(playlistName))
      System.out.println("Cannot delete playlist " + playlistName + ": Playlist does not exist");
    else 
      System.out.println("Deleted playlist: " + playlistName);  
  }

  public void searchVideos(String searchTerm) {
    List<Video> searchedVids = new ArrayList<>();
    List<Video> videoList = videoLibrary.getVideos();
  
    for (Video video : videoList) {
      if (video.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) 
        searchedVids.add(video);
    }

    if (searchedVids.isEmpty()) {
        System.out.println("No search results for " + searchTerm);
        return;
    }

    Collections.sort(searchedVids);
    System.out.println("Here are the results for " + searchTerm + ":");
    for (Video video : searchedVids) {
      System.out.print(searchedVids.lastIndexOf(video) + 1 + ") ");
      video.print();
      System.out.println();
    }

    System.out.println("Would you like to play any of the above? If yes, specify the number of the video.");
    System.out.println("If your answer is not a valid number, we will assume it's a no.");

    var scanner = new Scanner(System.in);
    if (scanner.hasNextInt()){
      var index = scanner.nextInt() - 1;
      if ((index >=0) && (index < searchedVids.size()))
         playVideo(searchedVids.get(index).getVideoId());
    }
    scanner.close();
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}