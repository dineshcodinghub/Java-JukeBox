/*
 *Author: Anuj
 *Date:
 *Created with : IntelliJ idea community Edition
 */


package org.example;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SongImpl {
    /**
     * this method is only for implement the songs
     * used switch cases and called all the method so output should be shown
     */
    public void songImplementation() {
        {
            MusicPlayerService service = new MusicPlayerService();
            try {
                SongRepository songRepository = new SongRepository();
                Scanner scanner = new Scanner(System.in);
                int choice = 0;
                do {
                    System.out.println("\u001B[32m ======= SONG MENU ======== \u001B[0m");
                    System.out.println("Press 1 to Display Song List");
                    System.out.println("Press 2 to search and play by Song Name");
                    System.out.println("Press 3 to search and play by Genre");
                    System.out.println("Press 4 to search and play by SongId");
                    System.out.println("Press 5 to search and play by Album Name");
                    System.out.println("Press 6 to search and play by Artist Name");
//                    System.out.println("Press 7 to sort the song list by name");
                    System.out.println("Press 0 to Exit ");
                    System.out.println("======= PLEASE CHOOSE======== ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.format("%-10s%-25s%-10s%-20s%-15s%-10s", "songId", "songName", "artistName", "genre", "album", "File path");
                            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            List<Song> list = songRepository.displayAllSongList();
                            list.forEach(song -> System.out.println(song));
                            System.out.println();
                            int choice1 = 0;
                            System.out.println("Press 1 for play Songs\n Press 2" +
                                    "0 for Exit");
                            choice1 = scanner.nextInt();
                            scanner.nextLine();
                            switch (choice1) {
                                case 1:
                                    System.out.println("To play song Enter SOngID");
                                    int songId = scanner.nextInt();
                                    scanner.nextLine();
                                    if (!(songId == songRepository.searchSongBySongId(songId).getSongId())) {
                                        System.out.println("Invalid song id");
                                    } else {
                                        service.play(songRepository.searchSongBySongId(songId).getSongPath());
                                    }
                                    break;
                                case 0:
                                    break;
                                default: {
                                    System.err.println("invalid");
                                    break;
                                }


                            }
                            break;
                        case 2:
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.format("%-10s%-32s%-35s%-20s%-15s%-10s", "Song Id", "Song Name", "Artist name", "Genere", "Album Name", "File Path");
                            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            List<Song> lists = songRepository.displayAllSongList();
                            lists.forEach(song -> System.out.println(song));
                            System.out.println("Enter the song name");
                            String songName = scanner.nextLine();
                            Song song2 = songRepository.searchSongBySongName(songName);
                            break;
                        case 3:
                            System.out.println("Enter the genere");
                            String songGenre = scanner.nextLine();
                            Song song3 = songRepository.searchSongBySongGenre(songGenre);
                            if (!(songGenre.equals(song3.getGenre()))) {
                                System.out.println("invalid  genere");
                            } else {
                                service.play(song3.getSongPath());
                            }
                            break;
                        case 4:
                            System.out.println("Enter the song Id");
                            int iD = scanner.nextInt();
                            Song song4 = songRepository.searchSongBySongId(iD);

                            if (!(iD == song4.getSongId())) {
                                System.out.println("invalid id");
                            } else {
                                System.out.println(song4);
                                service.play(song4.getSongPath());
                            }
                            break;

                        case 5:
                            System.out.println("Enter the album name");
                            String albumName = scanner.nextLine();
                            Song song5 = songRepository.searchSongByAlbumName(albumName);
                            if (!(albumName.equals(song5.getAlbumName()))) {
                                System.out.println("invalid album name");
                            } else {
                                service.play(song5.getSongPath());
                            }
                            break;

                        case 6:
                            System.out.println("Enter the artist name");
                            String artistName = scanner.nextLine();
                            Song song1 = songRepository.searchSongBySongArtistName(artistName);
                            if (!(artistName.equals(song1.getArtistName()))) {
                                System.out.println("invalid artist name");
                            } else {
                                service.play(song1.getSongPath());
                            }
                            break;
                        case 0:
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("\u001B[32m exit successFully \u001B[0m");
                            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            break;

                        default:
                            System.out.println("invalid");
                    }

                } while (choice > 0);


            } catch (SQLException e) {
                System.out.println(e);
            } catch (SongNotFoundException e) {
                System.out.println(e);
            } catch (InputMismatchException ep) {
                System.out.println(ep + " Enter eny number between 1 to 8");
                songImplementation();

            }


        }
    }
}
