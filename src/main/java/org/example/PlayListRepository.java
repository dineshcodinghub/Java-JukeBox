/*
 *Author: Anuj
 *Date:
 *Created with : IntelliJ idea community Edition
 */


package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayListRepository {
    MusicPlayerService playerService;
    String songPathValue;
    DataBaseService service;
    Connection connection;

    public PlayListRepository() throws SQLException {
        service = new DataBaseService();
        connection = service.getConnectionToDatabase();
        songPathValue = null;
        playerService = new MusicPlayerService();
    }

    /**
     * This method display all the playlist value
     *
     * @return List<Playlist>
     */
    public List<PlayList> displayAllListFromPlaylist() {
        List<PlayList> list3 = new ArrayList<>();
        String displayQuery = "SELECT * FROM `musicdb`.`playlist`";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(displayQuery);
            while (resultSet.next()) {
                int playListId = resultSet.getInt("playListId");
                String playListName = resultSet.getString("playListName");
                int songId = resultSet.getInt("songId");
                int userid = resultSet.getInt("userId");

                PlayList playList = new PlayList(playListId, playListName, songId, userid);
                list3.add(playList);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list3;
    }

    /**
     * This method is used to create a playlist inside playlist table.
     *
     * @param playlistName is parameter.
     * @return Playlist
     */

    public PlayList createPlayList(String playlistName, int userid) {
        PlayList playList = new PlayList();
        if (!(playList == null)) {
            String insertQuery = "insert into `musicdb`.`playlist` (`playListName`,userid) values (?,?);";
            try {
                PreparedStatement statement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1, playlistName);
                statement.setInt(2, userid);
                int result = statement.executeUpdate();
                if (result > 0) {
                    ResultSet keys = statement.getGeneratedKeys();
                    if (keys.next()) {
                        playList.setPlayListId(keys.getInt(1));
                        playList.setPlayListName(playlistName);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("playlist not added");
        }
        return playList;
    }

    /**
     * This method is used to add songs to the playlist using songIds.
     *
     * @param playlistId is an integer type.
     * @param songIds    is a String type.
     * @return boolean
     * //     * @throws PlaylistNotFoundException
     */
    public boolean addSongToPlaylist(int playlistId, String songIds) {
        int numberOfRowsAffected = 0;
        String updateQuery = "update `musicdb`.`playlist` set `songId` = ? where `playListId` = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, songIds);
            preparedStatement.setInt(2, playlistId);
            numberOfRowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (numberOfRowsAffected < 0) {
            System.out.println("not updated");
        }
        return numberOfRowsAffected > 0;
    }

    /**
     * This method is used to get song list from the playlist using playlistId.
     *
     * @param playlistId is an integer type
     * @return List<Song> is the return type.
     * //     * @throws PlaylistNotFoundException
     */
    public List<Song> getSongsFromPlaylist(int playlistId, int userid) {
        List<Song> list = new ArrayList<>();
        if (!(playlistId < 0)) {
            String query = "select * from `musicdb`.`playlist` where `playListId` = ? and userid = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, playlistId);
                statement.setInt(2, userid);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String songIds = resultSet.getString("songId");
                    String[] split = songIds.split(",");
                    for (String songId : split) {
                        Song song = new SongRepository().searchSongBySongId(Integer.parseInt(songId));
                        list.add(song);
                    }
                }
                if (list.isEmpty()) {
                    System.out.println("song not found");
                }

            } catch (SQLException | SongNotFoundException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("playlist id not found");
        }
        return list;
    }

}


