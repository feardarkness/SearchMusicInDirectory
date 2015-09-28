/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msod;

import msod.assembler.AssembleData;

/**
 *
 * @author aalvarado
 */
public class MSOD {

    public static void main(String[] args) {
        AssembleData assembler = new AssembleData("E:\\\\Musica", "C:\\Users\\aalvarado\\AppData\\Roaming\\MiniLyrics\\Lyrics");
        assembler.assemble();
    }

}
