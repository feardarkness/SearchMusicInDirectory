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
import java.util.stream.Stream;
import msod.config.Config;
import msod.filemetadata.AudioFileMetadata;
import msod.filevisitor.FileByExtensionVisitor;
import msod.indexer.MusicIndexer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

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

    public void assemble() throws IOException {        
        FileByExtensionVisitor musicVisitorByType = new FileByExtensionVisitor("MP3", "MP4");   // music extensions
        FileByExtensionVisitor lyricVisitorByType = new FileByExtensionVisitor("TXT", "LRC");   // lyric extensions
        Files.walkFileTree(pathToMusic, musicVisitorByType);
        Files.walkFileTree(pathToLyric, lyricVisitorByType);
        compareAndIndex(lyricVisitorByType.getFileList(), musicVisitorByType.getFileList());
    }

    public void compareAndIndex(Map<String, Path> lyricMap, Map<String, Path> musicMap) {
        File audioFile = null;
        AudioFileMetadata fileMetadata = null;
        int cant = 0;
        try (MusicIndexer musicIndexer = new MusicIndexer(Paths.get(Config.getInstance().getPathToIndex()))) {
            for (Map.Entry<String, Path> musicFileEntry : musicMap.entrySet()) {
                audioFile = new File(musicFileEntry.getValue().toString());
                try {                    
                    fileMetadata = new AudioFileMetadata(audioFile);
                    if (lyricMap.containsKey((fileMetadata.getArtistAndTitle() + ".lrc").toUpperCase())
                            || lyricMap.containsKey((fileMetadata.getArtistAndTitle() + ".txt").toUpperCase())) {

                        StringBuilder strBuilder = new StringBuilder();

                        try (Stream<String> stream = Files.lines(lyricMap.get((fileMetadata.getArtistAndTitle() + ".lrc").toUpperCase()))) {
                            stream.forEach(strBuilder::append);
                        } catch (Exception err) {
                            try (Stream<String> stream = Files.lines(lyricMap.get((fileMetadata.getArtistAndTitle() + ".txt").toUpperCase()))) {
                                stream.forEach(strBuilder::append);
                            }
                        }
                        Document doc = new Document();
                        doc.add(new StringField("id", fileMetadata.getArtistAndTitle(), Store.YES));        // stringField not analyzed (no tokens)
                        doc.add(new TextField("letra", strBuilder.toString(), Store.YES));                  // textField analized (tokenized)
                        doc.add(new TextField("artista", fileMetadata.getArtist(), Store.YES));
                        doc.add(new TextField("titulo", fileMetadata.getTitle(), Store.YES));
                        musicIndexer.index(doc);
                    } else {
                        System.out.println("Not found match " + fileMetadata.getArtistAndTitle());
                    }
                } catch (CannotReadException | IOException | TagException | ReadOnlyFileException | InvalidAudioFrameException e) {
                    System.out.println("Error found!!!");
                    e.printStackTrace();
                }
            }
            System.out.println("total files " + cant);        //851
            System.out.println("Total matches between lyrics and mp3 " + cant);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
