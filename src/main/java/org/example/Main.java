package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        SongImpl songImp = new SongImpl();
        PlayListImpl playlistImp = new PlayListImpl();
        DataBaseService dataBaseService = new DataBaseService();


        int userid = -1;
        while (userid == -1) {
            userid = dataBaseService.checkUser1();
        }
        int choice = 0;
        System.out.println(" ");
        System.out.println("===============================================:" + "\u001B[32m WELCOME TO JUKEBOX \u001B[0m" + ":===================================================================");
        System.out.println(" ");
        do {

            System.out.println("\u001B[32m====================================\u001B[0m");
            System.out.println("Press '1' to enter in Song Menu");
            System.out.println(" ");
            System.out.println("Press '2' to enter in Playlist Menu");
            System.out.println(" ");
            System.out.println("Press '3' to display podcast");
            System.out.println(" ");
            System.out.println("Press '0'to Exit");
            System.out.println("\u001B[32m=========: PLEASE CHOOSE :==========\u001B[0m ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    songImp.songImplementation();
                    break;
                case 2:
                    playlistImp.playListImplementation();
                    break;
                case 3:
                    Statement st = dataBaseService.connection.createStatement();
                    ResultSet rs = st.executeQuery("select * from podcast");
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.format("%-8s%-13s%-25s%-33s%-25s%-20s%-50s\n", "Id", "podcastId", "podcast Name", "Episode name", "Author Name", "Episode Duration", "Episode Path");
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    while (rs.next()) {
                        System.out.format("%-8s%-13s%-25s%-33s%-25s%-20s%-50s\n", rs.getInt(1),
                                rs.getInt(2), rs.getString(3),
                                rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(8));
                    }
                    break;

                case 0:

                    System.exit(0);
                    break;

                default:
                    System.err.println("Invalid Output !");
                    break;
            }

        } while (choice > 0);

    }
}
