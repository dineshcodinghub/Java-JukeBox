/*
 *Author: Anuj
 *Date:
 *Created with : IntelliJ idea community Edition
 */


package org.example;

import java.util.Objects;

public class PlayList {
    private int playListId;
    private String playListName;
    private int songIds;
    private int userId;


    public PlayList() {
    }

    public PlayList(int playListId, String playListName, int songIds, int userId) {
        this.playListId = playListId;
        this.playListName = playListName;
        this.songIds = songIds;
        this.userId = userId;
    }

    public int getPlayListId() {
        return playListId;
    }

    public void setPlayListId(int playListId) {
        this.playListId = playListId;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public int getSongIds() {
        return songIds;
    }

    public void setSongIds(int songIds) {
        this.songIds = songIds;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return String.format("%-20s%-20s", playListId, playListName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayList)) return false;
        PlayList playList = (PlayList) o;
        return getPlayListId() == playList.getPlayListId() && Objects.equals(getPlayListName(), playList.getPlayListName()) && Objects.equals(getSongIds(), playList.getSongIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayListId(), getPlayListName(), getSongIds());
    }
}
