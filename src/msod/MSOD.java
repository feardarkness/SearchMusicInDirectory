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
import msod.filevisitors.AllFilesVisitor;
import msod.filevisitors.FileByExtensionVisitor;

/**
 *
 * @author aalvarado
 */
public class MSOD {

    public static void main(String[] args) {
        Path pathToMusic;
        if (args.length == 1) {
            pathToMusic = Paths.get(args[0]);
        } else {
            String currentDirectory = System.getProperty("user.dir");
            pathToMusic = Paths.get(currentDirectory);
        }

        FileByExtensionVisitor visitorByType = new FileByExtensionVisitor("MP3", "MP4");
        try {
            Files.walkFileTree(pathToMusic, visitorByType);
            System.out.println("Archivos con extension mp3 hallados: " + visitorByType.getFileList());
            System.out.println("antidad de archivos con extension mp3 hallados: " + visitorByType.getFileList().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
