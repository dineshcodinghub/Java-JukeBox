/*
 *Author: Anuj
 *Date:
 *Created with : IntelliJ idea community Edition
 */


package org.example;

public class Song {
    private int songId;
    private String songName;
    private String duration;
    private String artistName;
    private String genre;
    private String songPath;
    private String albumName;


    public Song(int songId, String songName, String duration, String artistName, String genre, String songPath, String albumName) {
        this.songId = songId;
        this.songName = songName;
        this.duration = duration;
        this.artistName = artistName;
        this.genre = genre;
        this.songPath = songPath;
        this.albumName = albumName;
    }

    public Song() {
    }


    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }


    @Override

    public String toString() {
        return String.format("%-10s%-25s%-10s%-20s%-15s%-15s", songId, songName, duration, artistName, genre, albumName);

    }
}


