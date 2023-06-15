/*
 *Author: Anuj
 *Date:
 *Created with : IntelliJ idea community Edition
 */


package org.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PlayListImpl {
    public void playListImplementation() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        MusicPlayerService service = new MusicPlayerService();
        SongRepository songRepository = new SongRepository();
        int userid = 1;


        try {
            PlayListRepository repository = new PlayListRepository();
            int choice = 0;
            do {
                System.out.println("\u001B[32m ======= PLAYLIST MENU ======== \u001B[0m");
                System.out.println("Press 1 to create the Playlist by name");
                System.out.println("Press 2 to Add song to the playlist ");
                System.out.println("Press 3 to search song list And Play playlist");
//                System.out.println("Press 4 to search and play by SongId");
                System.out.println("Press 4 to Display");
                System.out.println("Press 0 to Exit");
                System.out.println("======= PLEASE CHOOSE======== ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Enter the new Playlist Name ");
                        String playlistName = scanner.nextLine();
//                        repository.createPlayList(playlistName);
                        System.out.println("playlist Id is: " + repository.createPlayList(playlistName, userid).getPlayListId());
                        break;

                    case 2:
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.format("%-20s%-12s", "PlaylistId", "PlaylistName");
                        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        repository.displayAllListFromPlaylist().forEach(playList -> System.out.println(playList));
                        System.out.println("Enter playlist Id where you want to add");
                        int playlistId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.format("%-10s%-25s%-10s%-20s%-15s%-10s", "songId", "songName", "artistName", "genre", "albumName");
                        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        songRepository.displayAllSongList().forEach(song -> System.out.println(song));
                        System.out.println("Enter song Ids ");
                        String songIds = scanner.nextLine();
                        repository.addSongToPlaylist(playlistId, songIds);
                        break;

                    case 3:
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.format("%-20s%-12s", "PlaylistId", "PlaylistName");
                        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        repository.displayAllListFromPlaylist().forEach(playList -> System.out.println(playList));
                        System.out.println("Enter playlist Id");
                        int playlistId1 = scanner.nextInt();

                        List<Song> songsFromPlaylist = repository.getSongsFromPlaylist(playlistId1, userid);
                        System.out.println("==============================");
                        System.out.println("Press '1' to play all songs\n");
                        System.out.println("Press '2' to play selected song\n");
                        System.out.println("Press '0' to Exit\n");
                        System.out.println("==============================");
                        int ch = scanner.nextInt();
                        scanner.nextLine();

                        switch (ch) {
                            case 1:
                                for (int i = 0; i < songsFromPlaylist.size(); i++) {
                                    Song song = songsFromPlaylist.get(i);
                                    service.play(song.getSongPath());
                                }
                                break;
                            /////////////////////////////////////////
                            case 2:
                                repository.getSongsFromPlaylist(playlistId1, userid).forEach(song -> System.out.println(song));
                                System.out.println("To play song Enter SOngID");
                                int songId = scanner.nextInt();
                                scanner.nextLine();
                                if (!(songId == songRepository.searchSongBySongId(songId).getSongId())) {
                                    System.out.println("Invalid song id!");
                                } else {
                                    Song song = songRepository.searchSongBySongId(songId);
                                    System.out.println(song);

                                    service.play(song.getSongPath());
                                }
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("invalid ");
                                break;
                        }
                        break;

                    case 4:
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.format("%-20s%-12s", "PlaylistId", "PlaylistName");
                        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        List<PlayList> playLists = repository.displayAllListFromPlaylist();
                        for (PlayList list1 : playLists) {
                            System.out.println(list1);
                        }
                        break;
                    case 0:
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("       \u001B[32m exit successFully! \u001B[0m");
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");

                        break;
                    default: {
                        System.err.println("Invalid Option!");
                        break;
                    }
                }
            } while (choice > 0);

        } catch (SQLException | SongNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
