/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msod.filemetadata;

import java.io.File;
import java.io.IOException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

/**
 *
 * @author aalvarado
 */
public class AudioFileMetadata {

    private final AudioFile audioFile;

    public AudioFileMetadata(File file) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        audioFile = AudioFileIO.read(file);
    }

    public String getArtist() {
        String artist = "";
        Tag tag = audioFile.getTag();
        if (tag != null) {
            artist = tag.getFirst(FieldKey.ARTIST);
        }
        return artist;
    }

    public String getTitle() {
        String title = "";
        Tag tag = audioFile.getTag();
        if (tag != null) {
            title = tag.getFirst(FieldKey.TITLE);
        }
        return title;
    }

    public String getArtistAndTitle() {
        return getArtist() + " - " + getTitle();
    }

}
