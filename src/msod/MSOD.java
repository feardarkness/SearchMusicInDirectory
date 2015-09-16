/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import msod.file.music.DirectoryMusicFinder;

/**
 *
 * @author aalvarado
 */
public class MSOD {

    public static void main(String[] args) {
        Path pathToMusic = Paths.get("E:\\Musica");
        DirectoryMusicFinder musicFinder = new DirectoryMusicFinder();
        try {
            Files.walkFileTree(pathToMusic, musicFinder);
            System.out.println("Archivos con extension mp3 hallados: " + musicFinder.musicList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
