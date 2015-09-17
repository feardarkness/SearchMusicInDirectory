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
import java.util.Map;
import msod.assembler.AssembleData;
import msod.filevisitor.FileByExtensionVisitor;

/**
 *
 * @author aalvarado
 */
public class MSOD {

    public static void main(String[] args) {
        AssembleData assembler = AssembleData("E:\\\\Musica", "C:\\Users\\aalvarado\\AppData\\Roaming\\MiniLyrics\\Lyrics");
        Path pathToMusic = Paths.get("E:\\\\Musica");
        Path pathToLyric = Paths.get("C:\\Users\\aalvarado\\AppData\\Roaming\\MiniLyrics\\Lyrics");

        // Always send parameters on uppercase. FileByExtensionVisitor expects parameters in uppercase
        FileByExtensionVisitor musicVisitorByType = new FileByExtensionVisitor("MP3", "MP4");
        FileByExtensionVisitor lyricVisitorByType = new FileByExtensionVisitor("TXT", "LRC");
        try {
            Files.walkFileTree(pathToMusic, musicVisitorByType);
            Files.walkFileTree(pathToLyric, lyricVisitorByType);
            //buscarIguales(musicVisitorByType.getFileList(), lyricVisitorByType.getFileList());
            System.out.println("Archivos de musica hallados: " + musicVisitorByType.getFileList());
            System.out.println("Archivos de lyric hallados: " + lyricVisitorByType.getFileList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
