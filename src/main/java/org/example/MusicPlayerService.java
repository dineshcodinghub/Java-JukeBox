/*
 *Author: Anuj
 *Date:
 *Created with : IntelliJ idea community Edition
 */


package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MusicPlayerService {
    Clip clip;
    AudioInputStream audioInputStream;

    /**
     * This method is for play songs and playlist
     * it give 6 services as a music player
     *
     * @param songPath
     * @throws
     */
    public void play(String songPath) {
        if (!(songPath == null)) {
            // song file to be played
            Scanner scanner = new Scanner(System.in);
            // create the song file
            File songFile = new File(songPath);
            // create an object of AudioInputStream class
            if (!(songFile.exists())) {
                System.err.println("NO song");


            }
            try {
                audioInputStream = AudioSystem.getAudioInputStream(songFile);

                // get a clip object from the AudioSystem
                clip = AudioSystem.getClip();

                //  load the audio input stream in clip
                clip.open(audioInputStream);
                //choice started
                int choice = 0;
                do {
                    System.out.println("************************************");
                    System.out.println("Press '1' for " + "\u001B[32m PLAY \u001B[0m");
                    System.out.println("************************************");
                    System.out.println("Press '2' for " + "\u001B[32m PAUSE \u001B[0m");
                    System.out.println("************************************");
                    System.out.println("Press '0' for " + "\u001B[32m STOP \u001B[0m");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            clip.start();

                            break;

                        case 2:
                            this.clip.getMicrosecondPosition();

                            clip.stop();
                            break;


                        case 0:

                            clip.stop();
                            clip.close();

                            break;


                        default: {
                            System.out.println("invalid");
                        }
                    }
                } while (choice > 0);


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
                exception.printStackTrace();
            }
        } else {
            System.out.println("Song path Not found");
        }

    }
}
