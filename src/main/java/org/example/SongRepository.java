/*
 *Author: Anuj
 *Date:
 *Created with : IntelliJ idea community Edition
 */


package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SongRepository {
    MusicPlayerService playerService;
    Song song;

    DataBaseService service;
    Connection connection;


    public SongRepository() throws SQLException {
        playerService = new MusicPlayerService();
        service = new DataBaseService();
        connection = service.getConnectionToDatabase();
        song = new Song();

    }

    /**
     * this method is to display all song.
     *
     * @return List<Song>
     */
    public List<Song> displayAllSongList() {
        List<Song> songList = new ArrayList<>();
        String allQuery = "SELECT * FROM `musicdb`.`songs`;";

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(allQuery);
            while (resultSet.next()) {
                int songId = resultSet.getInt("songId");
                String songName = resultSet.getString("songName");
                String artistname = resultSet.getString("artistname");
                String genere = resultSet.getString("genere");
                String album = resultSet.getString("album");
                String duration = resultSet.getString("duration");
                String filepath = resultSet.getString("filepath");
                Song song = new Song(songId, songName, artistname, genere, album, duration, filepath);
                songList.add(song);
            }


        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return songList;
    }

    /**
     * This is method for search song by name.
     *
     * @param name
     * @return Song
     * @throws SongNotFoundException
     */
    public Song searchSongBySongName(String name) throws SongNotFoundException {
        List<Song> songList = new ArrayList<>();
        if (name == null) {
            throw new SongNotFoundException("null value");
        }
        String nameQuery = "select * from `musicdb`.`songs` where `songName` like ? order by songName;";
        try (PreparedStatement statement = connection.prepareStatement(nameQuery)) {

            statement.setString(1, name + "%");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int songId = resultSet.getInt("songId");
                String songName = resultSet.getString("songName");
                String artistname = resultSet.getString("artistname");
                String genere = resultSet.getString("genere");
                String album = resultSet.getString("album");
                String duration = resultSet.getString("duration");
                String filepath = resultSet.getString("filepath");
                Song song = new Song(songId, songName, artistname, genere, album, duration, filepath);
                songList.add(song);


            }

            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(songList);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();

        }
        return song;
    }

    /**
     * this method id to search by song Id
     *
     * @param nameId
     * @return Song
     * @throws SongNotFoundException
     */
    //return song;
    public Song searchSongBySongId(int nameId) throws SongNotFoundException {

        if (nameId == 0) {
            System.out.println(("null value"));
        }
        String nameQuery = "select * from `musicdb`.`songs` where `songId`=?";
        try (PreparedStatement statement = connection.prepareStatement(nameQuery)) {

            statement.setInt(1, nameId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                song.setSongId(resultSet.getInt("songId"));
                song.setSongName(resultSet.getString("songName"));
                song.setArtistName(resultSet.getString("artistname"));
                song.setGenre(resultSet.getString("genere"));
                song.setAlbumName(resultSet.getString("album"));
                song.setDuration(resultSet.getString("duration"));
                song.setSongPath(resultSet.getString("filepath"));

            }

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();

        }

        return song;
    }

    /**
     * This method is for search song by Artist name
     *
     * @param artist
     * @return Song
     * @throws SongNotFoundException
     */

    public Song searchSongBySongArtistName(String artist) throws SongNotFoundException {
        if (artist == null) {
            // throw new SongNotFoundException("null value");
        }
        List<Song> songList = new ArrayList<>();
        String nameQuery = "select * from `musicdb`.`songs` where `artistName` like ? order by artistName;";
        try (PreparedStatement statement = connection.prepareStatement(nameQuery)) {

            statement.setString(1, artist);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int songId = resultSet.getInt("songId");
                String songName = resultSet.getString("songName");
                String artistname = resultSet.getString("artistname");
                String genere = resultSet.getString("genere");
                String album = resultSet.getString("album");
                String duration = resultSet.getString("duration");
                String filepath = resultSet.getString("filepath");
                Song song = new Song(songId, songName, artistname, genere, album, duration, filepath);

                songList.add(song);
            }

            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            songList.forEach(song1 -> System.out.println(song1));
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();

        }
        return song;
    }

    /**
     * this method is for search song by song genre
     *
     * @param songGenre
     * @return Song
     * @throws SongNotFoundException
     */
    public Song searchSongBySongGenre(String songGenre) throws SongNotFoundException {
        if (songGenre == null) {
            System.out.println(("null value"));
        }
        List<Song> songList = new ArrayList<>();
        String nameQuery = "select * from `musicdb`.`songs` where `genere` like ? order by genere;";
        try (PreparedStatement statement = connection.prepareStatement(nameQuery)) {
            statement.setString(1, songGenre);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int songId = resultSet.getInt("songId");
                String songName = resultSet.getString("songName");
                String artistname = resultSet.getString("artistname");
                String genere = resultSet.getString("genere");
                String album = resultSet.getString("album");
                String duration = resultSet.getString("duration");
                String filepath = resultSet.getString("filepath");
                Song song = new Song(songId, songName, artistname, genere, album, duration, filepath);
                songList.add(song);

            }


            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            songList.forEach(song -> System.out.println(song));
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();


        }
        return song;
    }

    /**
     * This method is for search song by album name
     *
     * @param album
     * @return Song
     * //     * @throws SongNotFoundException
     */
    public Song searchSongByAlbumName(String album) {
        if (album == null) {
            System.out.println("null value");
        }
        List<Song> songList = new ArrayList<>();
        String nameQuery = "select * from `musicdb`.`songs` where `album` like ? order by album;";
        try (PreparedStatement statement = connection.prepareStatement(nameQuery)) {

            statement.setString(1, album);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int songId = resultSet.getInt("songId");
                String songName = resultSet.getString("songName");
                String artistname = resultSet.getString("artistname");
                String genere = resultSet.getString("genere");
                String albumName = resultSet.getString("album");
                String duration = resultSet.getString("duration");
                String filepath = resultSet.getString("filepath");
                Song song = new Song(songId, songName, artistname, genere, albumName, duration, filepath);
                songList.add(song);
            }

            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
            songList.forEach(song -> System.out.println(song));
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();

        }

        return song;
    }

    /**
     * This method is for sort song by song name.
     *
     * @param songList
     * @return List<Song>
     * //     * @throws SongNotFoundException
     */
    public List<Song> sortSongBySongName(List<Song> songList) {
        if (!(songList == null)) {
            Collections.sort(songList, (o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(o1.getSongName(), o2.getSongName()));

        } else {
            System.out.println("song list not found");
        }
        return songList;

    }
}
