/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msod.indexing;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

/**
 *
 * @author aalvarado
 */
public class Indexer {
    
    public void index(String nombreCampo, String valor){        
        Document document = new Document();
        document.add(new Field("nombre", "valor", TextField.TYPE_STORED));
    }
}
