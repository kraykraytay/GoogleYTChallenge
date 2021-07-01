package com.google;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** A class used to represent a Playlist */
class VideoPlaylist implements Comparable<VideoPlaylist> {

  private final String name;
  private  HashMap<String, Video> videos;
  private ArrayList<Video> videoList;

  VideoPlaylist(String name) {
    this.name = name;
    videos = new HashMap<>();
    videoList = new ArrayList<>();
  }

  /** Returns the name of the playlist. */
  String getName() {
    return name;
  }

  boolean addVideo(Video v) {
    if (videos.containsKey(v.getVideoId()))
        return false;
    videos.put(v.getVideoId(), v); 
    videoList.add(v);
    return true;
  }

  boolean removeVideo(Video v) {
    if (!videos.containsKey(v.getVideoId()))
        return false;
    videos.remove(v.getVideoId()); 
    videoList.remove(v);
    return true;
  }

  /** Returns list of videos in playlist. */
  List<Video> getVideos() {
    return new ArrayList<>(this.videos.values());
  }

  List<Video> getVideoList(){
      return this.videoList;
  }

  void clear(){
      this.videos = new HashMap<>();
      this.videoList = new ArrayList<>();
  }

@Override
public int compareTo(VideoPlaylist o) {
    return this.name.compareToIgnoreCase(o.getName());
}

}
