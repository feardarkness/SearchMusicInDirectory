/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msod.assembler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import msod.filevisitor.FileByExtensionVisitor;

/**
 *
 * @author aalvarado
 */
public class AssembleData {

    private final Path pathToMusic;
    private final Path pathToLyric;

    public AssembleData(String pathToMusic, String pathToLyric) {
        this.pathToMusic = Paths.get(pathToMusic);
        this.pathToLyric = Paths.get(pathToLyric);
    }

    public void assemble() {
        // Always send parameters on uppercase. FileByExtensionVisitor expects parameters in uppercase
        FileByExtensionVisitor musicVisitorByType = new FileByExtensionVisitor("MP3", "MP4");   // music extensions
        FileByExtensionVisitor lyricVisitorByType = new FileByExtensionVisitor("TXT", "LRC");   // lyric extensions
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
