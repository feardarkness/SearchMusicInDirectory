/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msod.config;

/**
 *
 * @author aalvarado
 */
public class Config {

    private static Config configuration;
    private final String pathToIndex = "E:\\Index";
    private final String pathToMusic = "E:\\Musica";
    private final String pathToLyrics = "C:\\Users\\aalvarado\\AppData\\Roaming\\MiniLyrics\\Lyrics";

    private Config() {
        configuration = new Config();
    }

    public static Config getInstance() {
        return configuration;
    }

    public String getPathToIndex() {
        return pathToIndex;
    }

    public String getPathToMusic() {
        return pathToMusic;
    }

    public String getPathToLyrics() {
        return pathToLyrics;
    }

}
