/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msod.indexer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author aalvarado
 */
public class MusicIndexer implements AutoCloseable{// implement autocloseable so it can be used in try-with-resources

    Analyzer analyzer;
    // directorio en RAM
    //Directory directory = new RAMDirectory();            
    Directory directory;
    IndexWriterConfig config;
    IndexWriter indexWriter;

    public MusicIndexer(Path pathToIndex) throws IOException {
        analyzer = new StandardAnalyzer();
        //directory = FSDirectory.open(Paths.get("E:\\Index"));
        directory = FSDirectory.open(pathToIndex);
        config = new IndexWriterConfig(analyzer);
        indexWriter = new IndexWriter(directory, config);
    }
    
    public void index(Document documento) throws IOException{
        indexWriter.addDocument(documento);
    }

    @Override
    public void close() throws IOException {
        indexWriter.close();
    }

}
