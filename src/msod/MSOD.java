/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msod;

import java.io.IOException;
import msod.assembler.AssembleData;
import msod.config.Config;

/**
 *
 * @author aalvarado
 */
public class MSOD {

    public static void main(String[] args) throws IOException {
        AssembleData dataAssembler = new AssembleData(Config.getInstance().getPathToMusic(), Config.getInstance().getPathToLyrics());
        dataAssembler.assemble();
    }

}
