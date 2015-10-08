/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msod.assembler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import msod.filemetadata.AudioFileMetadata;
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
        File archivo = null;
        AudioFileMetadata fileMetadata = null;        
        FileByExtensionVisitor musicVisitorByType = new FileByExtensionVisitor("MP3", "MP4");   // music extensions
        FileByExtensionVisitor lyricVisitorByType = new FileByExtensionVisitor("TXT", "LRC");   // lyric extensions
        try {
            Files.walkFileTree(pathToMusic, musicVisitorByType);
            Files.walkFileTree(pathToLyric, lyricVisitorByType);
            Map<String, Path> lyricMap = lyricVisitorByType.getFileList();
            int cant = 0;
            for (Map.Entry<String, Path> musicFileEntry : musicVisitorByType.getFileList().entrySet()) {
                archivo = new File(musicFileEntry.getValue().toString());
                try {
                    fileMetadata = new AudioFileMetadata(archivo);

                    if (lyricMap.containsKey((fileMetadata.getArtistAndTitle() + ".lrc").toUpperCase())
                            || lyricMap.containsKey((fileMetadata.getArtistAndTitle() + ".txt").toUpperCase())) {                        
                        cant++;
                        // create file 4 lucene
                    }else{
                        System.out.println("Not found match " + fileMetadata.getArtistAndTitle());
                    }
                } catch (Exception e) {
                    System.out.println("Error found!!!");
                }

            }
            System.out.println("total files " + cant);        //851
            System.out.println("Total matches between lyrics and mp3" + cant);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
